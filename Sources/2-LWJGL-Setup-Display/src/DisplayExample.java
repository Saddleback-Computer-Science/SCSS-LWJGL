/**
 * <h1>
 * 	Display Example Class
 * </h1>
 * <p>
 * 		Display example class is used to demonstrate how to create 
 * 	a windows using LWJGL 2.9.X. It has the structure for a working 
 * 	game loop, and it will be used as a base for our next example.
 * </p>
 *  
 * @see http://legacy.lwjgl.org/javadoc/org/lwjgl/opengl/Display.html
 * @see http://legacy.lwjgl.org/javadoc/org/lwjgl/opengl/DisplayMode.html
 * @see http://legacy.lwjgl.org/javadoc/org/lwjgl/LWJGLException.html
 */

// Importing LWJGL so we can use its lib in our code.
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DisplayExample 
{
	// The height and width that will be used to draw the screen.
	public static int DISPLAY_HEIGHT = 600;
	public static int DISPLAY_WIDTH  = 800;
	
	/**
	 * <h2>
	 * 	Start Function
	 * </h2>
	 * <p>
	 * 	Start method is used to control the execution this class
	 * </p>
	 * @return void
	 */
    public void start() 
    {
    	// Catch any errors during screen creation
        try 
        {
        	// Set/Construct the windows to use to render to our defined size
        	Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT));
        	Display.create();
        } 
        catch (LWJGLException e) 
        {
        	// 	If an error is detection by the Exception class then the execution 
        	// need to be halted. This is a critical error. Usually issues here are
        	// caused by the users system compatibility or natives/OS mismatch
		    e.printStackTrace();
		    // Hard Exit
		    System.exit(1);
        }
 	
        // Game Loop
        // 	Discussed durning the meeting. This is where the magic happens. While 
        // the display window created above has not been closed, continue to 
        // execute the loop.
		while (!Display.isCloseRequested()) 
		{
			//Write Changes to the screen
		    Display.update();
		    System.out.println(this.printTimestamp());
		}
		
		// Destroy the Display created for good practice.
		Display.destroy();
    }
    
    private String printTimestamp()
    {
    	Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return sdf.format(cal.getTime());
    }
}