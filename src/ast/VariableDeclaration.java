package ast;

public class VariableDeclaration extends Declaration
{
    public Identifier identifier;

    public VariableDeclaration(Identifier identifier)
    {
        this.identifier = identifier;
    }
}
