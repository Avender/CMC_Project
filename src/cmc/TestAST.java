package cmc;

import javax.swing.*;
import cmc.ast.*;

public class TestAST
{
    private static final String EXAMPLES_DIR = "C:\\Users\\philp\\OneDrive\\Documents\\CMC Docs\\Project\\CMC_Project\\test_cases";

    public static void main( String args[] )
    {
        JFileChooser fc = new JFileChooser( EXAMPLES_DIR );

        if( fc.showOpenDialog( null ) == fc.APPROVE_OPTION ) {
            SourceFile in = new SourceFile( fc.getSelectedFile().getAbsolutePath() );
            Scanner s = new Scanner( in );
            ParserAST p = new ParserAST( s );

            AST ast = p.parseProgram();

            new ASTViewer( ast );
        }
    }
}
