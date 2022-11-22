package cmc.ast;

public class VarExpression extends Expression
{

    public Identifier name;
    public VariableDeclaration declcaration;

    public  VarExpression ( Identifier name )
    {
        this.name = name;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitVarExpression( this, arg );
    }
}
