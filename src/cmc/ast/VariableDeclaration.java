package cmc.ast;

import cmc.Address;

public class VariableDeclaration extends Declaration
{
    public Identifier identifier;
    public Address address;

    public VariableDeclaration(Identifier identifier)
    {
        this.identifier = identifier;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitVariableDeclaration( this, arg );
    }
}
