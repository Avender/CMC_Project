


public class Parser {

    private Scanner scanner;

    private Token currentTerminal;

    public Parser(Scanner scanner)
    {
        this.scanner = scanner;

        currentTerminal = scanner.scan();
    }

    public void parseProgram() {
        parseBlock();

        if (currentTerminal.kind != TokenKind.EOT)
            System.out.println("Tokens found after end of program");
    }

    private void accept(TokenKind tokenKind) {
        if (currentTerminal.kind == tokenKind)
            currentTerminal = scanner.scan();
        else
            System.out.println("Expected token of kind " + tokenKind);
    }

    private void parseBlock() {
        accept(TokenKind.INIT);
        parseDeclarations();
        accept(TokenKind.BEGIN);
        parseStatements();
        accept(TokenKind.END);
    }

    private void parseDeclarations() {
        while (currentTerminal.kind == TokenKind.VAR || currentTerminal.kind == TokenKind.DEF)
            parseOneDeclaration();
    }

    private void parseOneDeclaration() {
        switch (currentTerminal.kind) {
            case VAR:
                accept(TokenKind.VAR);
                accept(TokenKind.IDENTIFIER);
                accept(TokenKind.SEMICOLON);
                break;

            case DEF:
                accept(TokenKind.DEF);
                accept(TokenKind.IDENTIFIER);
                accept(TokenKind.LEFTPARAN);

                if (currentTerminal.kind == TokenKind.IDENTIFIER)
                    parseIdList();

                accept(TokenKind.RIGHTPARAN);
                parseBlock();
                accept(TokenKind.RETURN);
                parseExpression();
                accept(TokenKind.SEMICOLON);
                break;

            default:
                System.out.println("var or func expected");
                break;
        }
    }

    private void parseIdList() {
        accept(TokenKind.IDENTIFIER);

        while (currentTerminal.kind == TokenKind.COMMA) {
            accept(TokenKind.COMMA);
            accept(TokenKind.IDENTIFIER);
        }
    }

    private void parseStatements() {
        while (currentTerminal.kind == TokenKind.IDENTIFIER ||
                currentTerminal.kind ==  TokenKind.OPERATOR ||
                currentTerminal.kind == TokenKind.INTEGERLITERAL ||
                currentTerminal.kind == TokenKind.LEFTPARAN ||
                currentTerminal.kind == TokenKind.IF ||
                currentTerminal.kind == TokenKind.WHILE ||
                currentTerminal.kind == TokenKind.PRINT)
            parseOneStatement();
    }

    private void parseOneStatement() {
        switch (currentTerminal.kind) {
            case IDENTIFIER:
            case INTEGERLITERAL:
            case OPERATOR:
            case LEFTPARAN:
                parseExpression();
                accept(TokenKind.SEMICOLON);
                break;

            case IF:
                accept(TokenKind.IF);
                parseExpression();
                accept(TokenKind.COLON);
                parseStatements();

                if (currentTerminal.kind == TokenKind.ELSE) {
                    accept(TokenKind.ELSE);
                    parseStatements();
                }

                accept(TokenKind.FI);
                accept(TokenKind.SEMICOLON);
                break;

            case PRINT:
                accept(TokenKind.PRINT);
                parseExpression();
                accept(TokenKind.SEMICOLON);
                break;

            default:
                System.out.println("ERROR in statement");
                break;
        }
    }


    private void parseExpression() {
        parsePrimary();
        while (currentTerminal.kind == TokenKind.OPERATOR) {
            accept(TokenKind.OPERATOR);
            parsePrimary();
        }
    }


    private void parsePrimary() {
        switch (currentTerminal.kind) {
            case IDENTIFIER:
                accept(TokenKind.IDENTIFIER);

                if (currentTerminal.kind == TokenKind.LEFTPARAN)
                    accept(TokenKind.LEFTPARAN);

                if (currentTerminal.kind == TokenKind.IDENTIFIER ||
                        currentTerminal.kind == TokenKind.INTEGERLITERAL ||
                        currentTerminal.kind == TokenKind.OPERATOR ||
                        currentTerminal.kind == TokenKind.LEFTPARAN)
                    parseExpressionList();

                accept(TokenKind.RIGHTPARAN);

            case INTEGERLITERAL:
                accept(TokenKind.INTEGERLITERAL);
                break;

            case OPERATOR:
                accept(TokenKind.OPERATOR);
                parsePrimary();
                break;

            case LEFTPARAN:
                accept(TokenKind.LEFTPARAN);
                parseExpression();
                accept(TokenKind.RIGHTPARAN);
                break;

            default:
                System.out.println("ERROR in primary");
                break;
        }
    }

    private void parseExpressionList() {
        parseExpression();
        while (currentTerminal.kind == TokenKind.COMMA) {
            accept(TokenKind.COMMA);
            parseExpression();
        }
    }


}
