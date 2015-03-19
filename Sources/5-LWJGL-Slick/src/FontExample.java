import java.awt.Font;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
 
public class FontExample 
{ 
	private TrueTypeFont font;
 
	private static final boolean SETTING_ANTI_ALIAS = true;
	private static final boolean SETTING_V_SYNC = true;
	
	private static final int SETTING_WINDOW_WIDTH = 800;
	private static final int SETTING_WINDOW_HEIGHT = 600;
	private static final int SETTING_SYNC_RATE = 100;
	
	private static final String SETTING_OUTPUT_FONT = "Times New Roman";
	
	public static void main(String[] argv) 
	{
		new FontExample().start();
		
	}
 
	public void start() 
	{
		initGL(SETTING_WINDOW_WIDTH, SETTING_WINDOW_HEIGHT);
		init();
 
		while (!Display.isCloseRequested()) 
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
 
			Display.update();
			Display.sync(SETTING_SYNC_RATE);
 
		}
		
		Display.destroy();
		System.exit(0);
	}
 
	private void initGL(int width, int height) 
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(SETTING_V_SYNC);
			
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void init()
	{
		
		// load a default java font
		Font awtFont = new Font(SETTING_OUTPUT_FONT, Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, SETTING_ANTI_ALIAS);
 
		// load font from file
		
	}
 
	public void render() 
	{
 
		font.drawString(10, 10, printTimestamp(), Color.green);

	}
 
	
	
	private String printTimestamp()
    {
    	Calendar calendarObject = Calendar.getInstance();
    	calendarObject.getTime();
    	SimpleDateFormat simpleDateFormatObject = new SimpleDateFormat("HH:mm:ss");
    	return simpleDateFormatObject.format(calendarObject.getTime());
    }
}