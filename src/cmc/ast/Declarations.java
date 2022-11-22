package cmc.ast;

import java.util.Vector;

public class Declarations extends AST
{
    public Vector<Declaration> declaration = new Vector<Declaration>();

    public Object visit( Visitor v, Object arg )
    {
        return v.visitDeclarations( this, arg );
    }
}
