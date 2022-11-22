package cmc;
import cmc.ast.*;

public class ParserAST
{
    private Scanner scan;


    private Token currentTerminal;


    public ParserAST( Scanner scanner )
    {
        this.scan = scanner;

        currentTerminal = scan.scan();
    }


    public Program parseProgram()
    {
        Block block = parseBlock();

        if(currentTerminal.kind != TokenKind.EOT)
            System.out.println( "Tokens found after end of program" );

        return new Program(block);
    }


    private Block parseBlock()
    {
        accept(TokenKind.INIT);
        Declarations decs = parseDeclarations();
        accept(TokenKind.BEGIN);
        Statements stats = parseStatements();
        accept(TokenKind.END);

        return new Block( decs, stats );
    }


    private Declarations parseDeclarations()
    {
        Declarations decl = new Declarations();

        while(currentTerminal.kind == TokenKind.VAR ||
                currentTerminal.kind == TokenKind.DEF)
            decl.declaration.add( parseOneDeclaration() );

        return decl;
    }


    private Declaration parseOneDeclaration()
    {
        switch(currentTerminal.kind) {
            case VAR:
                accept(TokenKind.VAR);
                Identifier id = parseIdentifier();
                accept( TokenKind.SEMICOLON );

                return new VariableDeclaration( id );

            case DEF:
                accept(TokenKind.DEF);
                Identifier name = parseIdentifier();
                accept(TokenKind.LEFTPARAN);

                Declarations parameters = null;
                if( currentTerminal.kind == TokenKind.IDENTIFIER)
                    parameters = parseIdList();
                else
                    parameters = new Declarations();

                accept(TokenKind.RIGHTPARAN);
                Block block = parseBlock();
                accept(TokenKind.RETURN);
                Expression returnExp = parseExpression();
                accept(TokenKind.SEMICOLON);

                return new FunctionDeclaration( name, parameters, block, returnExp );

            default:
                System.out.println( "var or func expected" );
                return null;
        }
    }


    private Declarations parseIdList()
    {
        Declarations list = new Declarations();

        list.declaration.add( new VariableDeclaration( parseIdentifier() ) );

        while( currentTerminal.kind == TokenKind.COMMA) {
            accept(TokenKind.COMMA);
            list.declaration.add( new VariableDeclaration( parseIdentifier() ) );
        }

        return list;
    }


    private Statements parseStatements()
    {
        Statements statements = new Statements();

        while( currentTerminal.kind == TokenKind.IDENTIFIER ||
                currentTerminal.kind == TokenKind.OPERATOR ||
                currentTerminal.kind == TokenKind.INTEGERLITERAL ||
                currentTerminal.kind == TokenKind.LEFTPARAN ||
                currentTerminal.kind == TokenKind.IF ||
                currentTerminal.kind == TokenKind.WHILE ||
                currentTerminal.kind == TokenKind.PRINT)
            statements.stat.add( parseOneStatement() );

        return statements;
    }


    private Statement parseOneStatement()
    {
        switch( currentTerminal.kind ) {
            case IDENTIFIER:
            case INTEGERLITERAL:
            case OPERATOR:
            case LEFTPARAN:
                Expression expression = parseExpression();
                accept(TokenKind.SEMICOLON);

                return new ExpressionStatement( expression );

            case IF:
                accept(TokenKind.IF);
                Expression ifExp = parseExpression();
                accept(TokenKind.COLON);
                Statements thenPart = parseStatements();

                Statements elsePart = null;
                if( currentTerminal.kind == TokenKind.ELSE) {
                    accept(TokenKind.ELSE);
                    elsePart = parseStatements();
                }

                accept(TokenKind.FI);
                accept(TokenKind.SEMICOLON);

                return new IfStatement( ifExp, thenPart, elsePart );

            case WHILE:
                accept( TokenKind.WHILE);
                Expression whileExp = parseExpression();
                accept(TokenKind.BEGIN);
                Statements statements = parseStatements();
                accept(TokenKind.END);
                accept(TokenKind.SEMICOLON);

                return new WhileStatement( whileExp, statements );

            case PRINT:
                accept(TokenKind.PRINT);
                Expression printExp = parseExpression();
                accept(TokenKind.SEMICOLON);

                return new PrintStatement(printExp);

            default:
                System.out.println( "ERROR in statement" );
                return null;
        }
    }


    private Expression parseExpression()
    {
        Expression res = parsePrimary();
        while( currentTerminal.kind == TokenKind.OPERATOR) {
            Operator operator = parseOperator();
            Expression tmp = parsePrimary();

            res = new BinaryExpression( operator, res, tmp );
        }

        return res;
    }


    private Expression parsePrimary()
    {
        switch( currentTerminal.kind ) {
            case IDENTIFIER:
                Identifier name = parseIdentifier();

                if( currentTerminal.kind == TokenKind.LEFTPARAN) {
                    accept(TokenKind.LEFTPARAN);

                    ExpList args;

                    if( currentTerminal.kind == TokenKind.IDENTIFIER ||
                            currentTerminal.kind == TokenKind.INTEGERLITERAL ||
                            currentTerminal.kind == TokenKind.OPERATOR  ||
                            currentTerminal.kind == TokenKind.LEFTPARAN)

                        args = parseExpressionList();
                    else
                        args = new ExpList();


                    accept(TokenKind.RIGHTPARAN);

                    return new CallExpression( name, args );
                } else
                    return new VarExpression( name );

            case INTEGERLITERAL:
                IntegerLiteral literal = parseIntegerLiteral();
                return new IntLitExpression(literal);

            case OPERATOR:
                Operator op = parseOperator();
                Expression exp = parsePrimary();
                return new UnaryExpression( op, exp );

            case LEFTPARAN:
                accept(TokenKind.LEFTPARAN);
                Expression res = parseExpression();
                accept(TokenKind.RIGHTPARAN);
                return res;

            default:
                System.out.println( "ERROR in primary" );
                return null;
        }
    }


    private ExpList parseExpressionList()
    {
        ExpList expressions = new ExpList();

        expressions.exp.add( parseExpression() );
        while( currentTerminal.kind == TokenKind.COMMA) {
            accept(TokenKind.COMMA);
            expressions.exp.add( parseExpression() );
        }

        return expressions;
    }


    private Identifier parseIdentifier()
    {
        if( currentTerminal.kind == TokenKind.IDENTIFIER) {
            Identifier res = new Identifier( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return res;
        } else {
            System.out.println( "Identifier expected" );

            return new Identifier( "???" );
        }
    }


    private IntegerLiteral parseIntegerLiteral()
    {
        if( currentTerminal.kind == TokenKind.INTEGERLITERAL) {
            IntegerLiteral res = new IntegerLiteral( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return res;
        } else {
            System.out.println( "Integer literal expected" );

            return new IntegerLiteral( "???" );
        }
    }


    private Operator parseOperator()
    {
        if( currentTerminal.kind == TokenKind.OPERATOR) {
            Operator res = new Operator( currentTerminal.spelling );
            currentTerminal = scan.scan();

            return res;
        } else {
            System.out.println( "Operator expected" );

            return new Operator( "???" );
        }
    }


    private void accept( TokenKind expected )
    {
        if( currentTerminal.kind == expected )
            currentTerminal = scan.scan();
        else
            System.out.println( "Expected token of kind " + expected );
    }
}
