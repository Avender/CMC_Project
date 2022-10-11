public class Token {

    public TokenKind kind;
    public String spelling;

    public Token (TokenKind kind, String spelling)
    {
        this.kind = kind;
        this.spelling = spelling;

//        if (kind == IDENTIFIER)
    }


    public boolean isAssignOperator()
    {
        if(kind == TokenKind.OPERATOR)
            return containsOperator( spelling, ASSIGNOPS );
        else
            return false;
    }

    public boolean isAddOperator()
    {
        if( kind == TokenKind.OPERATOR )
            return containsOperator( spelling, ADDOPS );
        else
            return false;
    }

    public boolean isMulOperator()
    {
        if( kind == TokenKind.OPERATOR )
            return containsOperator( spelling, MULOPS );
        else
            return false;
    }


    private boolean containsOperator( String spelling, String OPS[] )
    {
        for( int i = 0; i < OPS.length; ++i )
            if( spelling.equals( OPS[i] ) )
                return true;

        return false;
    }


    private static final TokenKind[] KEYWORDS = { TokenKind.INIT, TokenKind.BEGIN, TokenKind.END, TokenKind.FI, TokenKind.DEF, TokenKind.IF, TokenKind.PRINT, TokenKind.RETURN, TokenKind.INPUT, TokenKind.VAR, TokenKind.WHILE };


    private static final String ASSIGNOPS[] =
            {
                    "=",
            };


    private static final String ADDOPS[] =
            {
                    "+",
                    "-",
            };


    private static final String MULOPS[] =
            {
                    "*",
                    "/",
                    "%",
            };

}
