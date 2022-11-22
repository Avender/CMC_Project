package cmc.ast;

public class CallExpression extends Expression
{
    public Identifier name;
    public ExpList args;
    public FunctionDeclaration declaration;

    public CallExpression( Identifier name, ExpList args )
    {
        this.name = name;
        this.args = args;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitCallExpression( this, arg );
    }
}
