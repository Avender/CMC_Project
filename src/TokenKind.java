public enum TokenKind {

    IDENTIFIER,
    INTEGERLITERAL,
    OPERATOR,

    INIT("init"),
    BEGIN("begin"),
    END("end"),
    IF("if"),
    FI("fi"),
    WHILE("while"),
    RETURN("return"),
    PRINT("print"),
    VAR("var"),
    INPUT("input"),
    DEF("def"),
    ELSE("else"),

    COMMA(","),
    SEMICOLON(";"),
    COLON(":"),
    LEFTPARAN("("),
    RIGHTPARAN(")"),

    EOT,

    ERROR;


    private String spelling = null;


    private TokenKind()
    {
    }


    private TokenKind( String spelling )
    {
        this.spelling = spelling;
    }


    public String getSpelling()
    {
        return spelling;
    }
    }
