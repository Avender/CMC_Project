package cmc.ast;

public abstract class AST
{
    public abstract Object visit( Visitor v, Object arg );
}
