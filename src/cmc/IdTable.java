package cmc;

import java.util.Vector;

import cmc.ast.*;

public class IdTable
{
    private Vector<IdEntry> table = new Vector<IdEntry>();
    private int level = 0;

    public IdTable (){}

    public void enter( String id, Declaration attr )
    {
        IdEntry entry = search(id);

        if( entry != null && entry.level == level )
            System.out.println( id + " declared twice" );
        else
            table.add( new IdEntry( level, id, attr ) );
    }

    public Declaration retrieve(String id)
    {
        IdEntry entry = search(id);

        if(entry != null)
            return entry.declaration;
        else
            return null;
    }

    public void openScope()
    {
        ++level;
    }

    public void closeScope()
    {
        int pos = table.size() - 1;
        while( pos >= 0 && table.get(pos).level == level ) {
            table.remove( pos );
            pos--;
        }

        level--;
    }

    private IdEntry search(String id)
    {
        for( int i = table.size() - 1; i >= 0; i-- )
            if( table.get(i).id.equals( id ) )
                return table.get(i);

        return null;
    }
}
