package cmc;

import cmc.ast.*;

import java.util.Vector;

public class Checker implements Visitor
{
    private IdTable idTable = new IdTable();


    public void check(Program program)
    {
        program.visit(this, null);
    }


    public Object visitProgram(Program program, Object arg)
    {
        idTable.openScope();

        program.block.visit(this, null);

        idTable.closeScope();

        return null;
    }


    public Object visitBlock(Block block, Object arg)
    {
        block.declarations.visit(this, null);
        block.statements.visit(this, null);

        return null;
    }


    public Object visitDeclarations(Declarations declarations, Object arg)
    {
        for( Declaration decl: declarations.declaration )
            decl.visit( this, null );

        return null;
    }


    public Object visitVariableDeclaration(VariableDeclaration varDec, Object arg)
    {
        String id = (String) varDec.identifier.visit(this, null);

        idTable.enter(id, varDec);

        return null;
    }


    public Object visitFunctionDeclaration(FunctionDeclaration funcDec, Object arg)
    {
        String id = (String) funcDec.name.visit(this, null);

        idTable.enter(id, funcDec);
        idTable.openScope();

        funcDec.params.visit(this, null);
        funcDec.block.visit(this, null);
        funcDec.retExp.visit(this, null);

        idTable.closeScope();

        return null;
    }


    public Object visitStatements(Statements statements, Object arg)
    {
        for(Statement stat: statements.stat)
            stat.visit(this, null);

        return null;
    }


    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg)
    {
        expressionStatement.expression.visit(this, null);

        return null;
    }


    public Object visitIfStatement(IfStatement ifStatement, Object arg)
    {
        ifStatement.exp.visit(this, null);
        ifStatement.thenPart.visit(this, null);
        ifStatement.elsePart.visit(this, null);

        return null;
    }


    public Object visitWhileStatement(WhileStatement whileStatement, Object arg)
    {
        whileStatement.exp.visit(this, null);
        whileStatement.stats.visit(this, null);

        return null;
    }


    public Object visitPrintStatement(PrintStatement printStat, Object arg)
    {
        printStat.exp.visit(this, null);

        return null;
    }


    public Object visitBinaryExpression(BinaryExpression binExp, Object arg)
    {
        Type t1 = (Type) binExp.operand0.visit(this, null);
        Type t2 = (Type) binExp.operand1.visit(this, null);
        String operator = (String) binExp.operator.visit(this, null);

        if(operator.equals( ":=" ) && t1.rvalueOnly)
            System.out.println("Left-hand side of := must be a variable");

        return new Type(true);
    }

    public Object visitVarExpression(VarExpression varExp, Object arg)
    {
        String id = (String) varExp.name.visit(this, null);

        Declaration decl = idTable.retrieve(id);
        if(decl == null)
            System.out.println(id + " is not declared");
        else if(!(decl instanceof VariableDeclaration))
            System.out.println(id + " is not a variable");
        else
            varExp.declcaration = (VariableDeclaration) decl;

        return new Type(false);
    }


    public Object visitCallExpression(CallExpression callExpression, Object arg)
    {
        String id = (String) callExpression.name.visit(this, null);
        Vector<Type> t = (Vector<Type>)(callExpression.args.visit( this, null));

        Declaration d = idTable.retrieve(id);
        if( d == null )
            System.out.println(id + " is not declared");
        else if(!(d instanceof FunctionDeclaration))
            System.out.println(id + " is not a function");
        else {
            FunctionDeclaration f = (FunctionDeclaration) d;
            callExpression.declaration = f;

            if(f.params.declaration.size() != t.size())
                System.out.println("Incorrect number of arguments in call to " + id);
        }

        return new Type(true);
    }


    public Object visitUnaryExpression(UnaryExpression unaryExpression, Object arg)
    {
        unaryExpression.operand.visit(this, null);
        String operator = (String) unaryExpression.operator.visit(this, null);

        if( !operator.equals( "+" ) && !operator.equals( "-" ) )
            System.out.println( "Only + or - is allowed as unary operator" );

        return new Type(true);
    }


    public Object visitIntLitExpression(IntLitExpression intLitExpression, Object arg)
    {
        intLitExpression.literal.visit(this, true);

        return new Type(true);
    }


    public Object visitExpList(ExpList e, Object arg)
    {
        Vector<Type> types = new Vector<Type>();

        for( Expression exp: e.exp )
            types.add((Type) exp.visit( this, null));

        return types;
    }


    public Object visitIdentifier(Identifier i, Object arg)
    {
        return i.spelling;
    }


    public Object visitIntegerLiteral(IntegerLiteral i, Object arg)
    {
        return null;
    }


    public Object visitOperator(Operator o, Object arg)
    {
        return o.spelling;
    }
}
