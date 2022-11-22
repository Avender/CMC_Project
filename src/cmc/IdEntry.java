package cmc;

import cmc.ast.*;

public class IdEntry
{
    public int level;
    public String id;
    public Declaration declaration;

    public IdEntry( int level, String id, Declaration declaration )
    {
        this.level = level;
        this.id = id;
        this.declaration = declaration;
    }

    public String toString()
    {
        return level + "," + id;
    }
}
