import javax.swing.*;

public class Main {
    private static final String EXAMPLES_DIR = "C:\\Users\\mainh\\IdeaProjects\\CMC_Project\\test_cases";

    public static void main( String args[] )
    {
        JFileChooser fc = new JFileChooser( EXAMPLES_DIR );

        if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
            SourceFile in = new SourceFile( fc.getSelectedFile().getAbsolutePath() );
            Scanner s = new Scanner(in);
            Parser p = new Parser( s );


            Token t = s.scan();
            while(t.kind != TokenKind.EOT) {
                System.out.println(t.kind + " " + t.spelling);

                t = s.scan();
            }

            p.parseProgram();
        }
    }
}