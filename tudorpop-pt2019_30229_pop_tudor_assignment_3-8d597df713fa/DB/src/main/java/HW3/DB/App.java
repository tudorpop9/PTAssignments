package HW3.DB;

import dataAccessLayer.DBConnection;
import presentation.FirstFrame;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        FirstFrame f = new FirstFrame();
        f.perform();
    }
}
