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
import org.lwjgl.opengl.GL11;

public class QuadExample 
{
	public static final int DRAW_DISTANCE_LENGTH_100 = 50;
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
    	int x = 100;
    	int y = 100;
    	float rotation = 0;
    	DisplayInitilizer();
	    
        // Game Loop
        // 	Discussed during the meeting. This is where the magic happens. While 
        // the display window created above has not been closed, continue to 
        // execute the loop.
		while (!Display.isCloseRequested()) 
		{
			
			// Clear The Screen And The Depth Buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	  
	        // R,G,B,A Set The Color To Blue One Time Only
	        GL11.glColor3f(0.5f, 0.5f, 1.0f);
	  
	        // 		glPushMatrix pushes the current matrix stack down 
			//  by one, duplicating the current matrix. That is, after 
			//  glPushMatrix call, the matrix	on top of the stack is
			//  identical to the one below it.
			//
			//  glPopMatrix pops the current matrix stack, replacing the
			//  current matrix with the one below it on the stack.
	        GL11.glPushMatrix();

	        	//		glTranslate produces a translation by	(x,y,z). The current
				//  matrix (see glMatrixMode) is multiplied by this translation matrix, 
				//  with the product replacing	the current matrix, as if glMultMatrix
				//  were called with the following matrix	for its	argument:
	            GL11.glTranslatef(x, y, 0);
	            
	            // set rotation
	            rotation += 0.05f ;
	            
				//    glRotate produces a rotation of angle	degrees	around the
				//  vector (x,y,z).  The current matrix (see glMatrixMode) is
				//  multiplied by	a rotation matrix with the product replacing
				//  the current matrix, as if glMultMatrix were called with the
				//  following matrix as its argument:
				//  NOTES
				//  This rotation	follows	the right-hand rule, so	if the vector
				//  (x,y,z) points toward	the user, the rotation will be
				//  counterclockwise.
	            GL11.glRotatef(rotation, 0f, 0f, 1f);
	            
	            GL11.glTranslatef(-x, -y, 0);

				//    glBegin and glEnd delimit the	vertices that define a
				//  primitive or a group of like primitives.  glBegin accepts a
				//  single argument that specifies in which of ten ways the
				//  vertices are interpreted.  Taking n as an integer count
				//  starting at one, and N as the	total number of	vertices
				//  specified, the interpretations are as	follows:
	            GL11.glBegin(GL11.GL_QUADS);
					//    glVertex commands are	used within glBegin/glEnd pairs	to
					//  specify point, line, and polygon vertices.  The current
					//  color, normal, and texture coordinates are associated	with
					//  the vertex when glVertex is called.
					//
					//  When only x and y are	specified, z defaults to 0 and w
					//  defaults to 1.  When x, y, and z are specified, w defaults
					//  to 1.
		            GL11.glVertex2f(DRAW_DISTANCE_LENGTH_100     ,DRAW_DISTANCE_LENGTH_100	  );
			        GL11.glVertex2f(DRAW_DISTANCE_LENGTH_100 * 3 ,DRAW_DISTANCE_LENGTH_100	  );
			        GL11.glVertex2f(DRAW_DISTANCE_LENGTH_100 * 3 ,DRAW_DISTANCE_LENGTH_100 * 3);
			        GL11.glVertex2f(DRAW_DISTANCE_LENGTH_100     ,DRAW_DISTANCE_LENGTH_100 * 3);
	            GL11.glEnd();
	            
	        GL11.glPopMatrix();
	        
			//Write Changes to the screen
		    Display.update();
		    System.out.println(this.printTimestamp());
		}
		
		// Destroy the Display created for good practice.
		Display.destroy();
    }
    
    private void DisplayInitilizer()
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
 	
        // init OpenGL
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0, 800, 0, 600, 1, -1);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
    private String printTimestamp()
    {
    	Calendar calendarObject = Calendar.getInstance();
    	calendarObject.getTime();
    	SimpleDateFormat simpleDateFormatObject = new SimpleDateFormat("HH:mm:ss");
    	return simpleDateFormatObject.format(calendarObject.getTime());
    }
}

