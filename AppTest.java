package com.example.myproject.canvas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.annotation.Order;

import com.example.myproject.canvas.command.impl.Fill;
import com.example.myproject.canvas.command.impl.Line;
import com.example.myproject.canvas.command.impl.Rectangle;
import com.example.myproject.canvas.exception.CanvasException;
import com.example.myproject.canvas.impl.DrawCanvas;

/**
 * Unit test for simple App.
 */

@RunWith(PowerMockRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class AppTest 
{
    @InjectMocks
	DrawCanvas drawConvas;

    @InjectMocks
    Line line;

    @InjectMocks
    Rectangle rectangle;
	
    @InjectMocks
    Fill fill;

	private String[] cmd;
	

	@Before
	public void init() {
		line.setCanvasDec(drawConvas);
		rectangle.setCanvasDec(drawConvas);
		fill.setCanvasDec(drawConvas);
	}

    @Rule public TestName name = new TestName();
	
	@Test
	@Order(value = 1)
	public void testCaseCompleteExecution() {
		
		//set the convas command
		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "1", "2", "6", "2" };
		line.draw(cmd);

		cmd   =  new String[] { "L", "6", "3", "6", "4" };
		line.draw(cmd);

		cmd   =  new String[] { "R", "16", "1", "20", "3" };
		rectangle.draw(cmd);
		
		cmd   =  new String[] { "B", "10", "3" ,"O" };
		fill.fill(cmd);
		
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");
		drawConvas.printValues();

	}
	
	@Test
	@Order(value = 2)
	public void testCaseInvalidCanvasBondary( ) {
		cmd   =  new String[] { "C" , "-20" ,"4" };
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		try{
			drawConvas.drawShape(cmd);
		}catch(CanvasException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getMessage(), "invalid numeric argument to build canvas!!!");
		}
		
	}
	
	@Test
	@Order(value = 3)
	public void testCaseInvalidCanvasParam( ) {
		cmd   =  new String[] { "C" , "20" };
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		try{
			drawConvas.drawShape(cmd);
		}catch(CanvasException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getMessage(), "invalid arguments need width and heigth!!");
		}

	}

	@Test
	@Order(value = 4)
	public void testCaseInvalidLineBoundaryLessThanCanvas( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "-1", "2", "6", "2" };
		line.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 5)
	public void testCaseInvalidLineBoundaryMoreThanCanvas( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "22", "2", "6", "2" };
		line.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 6)
	public void testCaseUnsupportedLine( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "22", "2", "6", "21" };
		line.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 7)
	public void testCaseInvalidLineArg( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "22", "2", "6"};
		line.draw(cmd);
		drawConvas.printValues();
	}
	
	@Test
	public void testCaseCorrectLine( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "1", "2", "6" , "2"};
		line.draw(cmd);

		cmd   =  new String[] { "L", "6", "3", "6" , "4"};
		line.draw(cmd);

		
		drawConvas.printValues();
	}
	
	
	@Test
	@Order(value = 8)
	public void testCaseInvalidRectangleArg( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "R", "22", "2", "6"};
		rectangle.draw(cmd);
		drawConvas.printValues();
	}
	
	@Test
	@Order(value = 9)
	public void testCaseRectangleLessThanCanvas( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "R", "-16", "1", "20" , "3"};
		rectangle.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 10)
	public void testCaseRectangleMoreThanCanvas( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "R", "16", "1", "25" , "3"};
		rectangle.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 10)
	public void testCaseCorrectRectangle( ) {

		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");

		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "R", "16", "1", "20" , "3"};
		rectangle.draw(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 11)
	public void testCaseFillBoundarLessCanvas( ) {

		//set the convas command
		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "1", "2", "6", "2" };
		line.draw(cmd);

		cmd   =  new String[] { "L", "6", "3", "6", "4" };
		line.draw(cmd);

		cmd   =  new String[] { "R", "16", "1", "20", "3" };
		rectangle.draw(cmd);
		
		cmd   =  new String[] { "B", "-10", "3" ,"O" };
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");
		fill.fill(cmd);
		drawConvas.printValues();
	}

	@Test
	@Order(value = 11)
	public void testCaseFillBoundarMoreCanvas( ) {

		//set the convas command
		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "1", "2", "6", "2" };
		line.draw(cmd);

		cmd   =  new String[] { "L", "6", "3", "6", "4" };
		line.draw(cmd);

		cmd   =  new String[] { "R", "16", "1", "20", "3" };
		rectangle.draw(cmd);
		
		cmd   =  new String[] { "B", "10", "30" ,"O" };
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");
		fill.fill(cmd);
		drawConvas.printValues();
	}
	
	@Test
	@Order(value = 12)
	public void testCaseFillBoundarRefill( ) {

		//set the convas command
		cmd   =  new String[] { "C" , "20" ,"4" };
		drawConvas.drawShape(cmd);

		cmd   =  new String[] { "L", "1", "2", "6", "2" };
		line.draw(cmd);

		cmd   =  new String[] { "L", "6", "3", "6", "4" };
		line.draw(cmd);

		cmd   =  new String[] { "R", "16", "1", "20", "3" };
		rectangle.draw(cmd);
		
		cmd   =  new String[] { "B", "10", "3" ,"O" };
		System.out.println("\n\n --------" + name.getMethodName()+ "-------------------");
		fill.fill(cmd);
		drawConvas.printValues();
		
		System.out.println("\n \n refilling with -");
		cmd   =  new String[] { "B", "10", "3" ,"-" };
		fill.fill(cmd);
		drawConvas.printValues();
		
	}
}
