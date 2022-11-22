package cmc;

import javax.swing.*;
import cmc.ast.*;

public class CheckerTest {
    private static final String EXAMPLES_DIR = "C:\\Users\\philp\\OneDrive\\Documents\\CMC Docs\\Project\\CMC_Project\\test_cases";

    /*
    public static void main( String args[] )
    {
        JFileChooser fc = new JFileChooser( EXAMPLES_DIR );

        if( fc.showOpenDialog( null ) == fc.APPROVE_OPTION ) {
            SourceFile in = new SourceFile( fc.getSelectedFile().getAbsolutePath() );
            Scanner scanner = new Scanner( in );
            ParserAST p = new ParserAST(scanner);
            Checker checker = new Checker();

            AST ast = (AST) p.parseProgram();
            checker.check( (Program) ast );
        }
    } */
}
