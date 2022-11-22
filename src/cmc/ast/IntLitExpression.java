package cmc.ast;

public class IntLitExpression extends Expression
{

    public IntegerLiteral literal;

    public IntLitExpression( IntegerLiteral literal )
    {
        this.literal = literal;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitIntLitExpression( this, arg );
    }
}
