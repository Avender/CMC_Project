package cmc.ast;

import java.util.Vector;

public class ExpList extends AST
{
    public Vector<Expression> exp = new Vector<Expression>();

    public Object visit( Visitor v, Object arg )
    {
        return v.visitExpList( this, arg );
    }
}
