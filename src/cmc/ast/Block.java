package cmc.ast;

public class Block extends AST {

    public Declarations declarations;
    public Statements statements;

    public Block(Declarations declarations, Statements statements)
    {
        this.declarations = declarations;
        this.statements = statements;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitBlock( this, arg );
    }
}
