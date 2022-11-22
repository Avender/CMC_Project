package cmc;

public class ParserTest {
    private static final String EXAMPLES_DIR = "C:\\Users\\mainh\\IdeaProjects\\CMC_Project\\test_cases";

    /* public static void main( String args[] )
    {
        JFileChooser fc = new JFileChooser( EXAMPLES_DIR );

        if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
            cmc.SourceFile in = new cmc.SourceFile( fc.getSelectedFile().getAbsolutePath() );
            cmc.Scanner s = new cmc.Scanner(in);
            cmc.Parser p = new cmc.Parser( s );


            cmc.Token t = s.scan();
            while(t.kind != cmc.TokenKind.EOT) {
                System.out.println(t.kind + " " + t.spelling);

                t = s.scan();
            }

            p.parseProgram();
        }
    } */
}