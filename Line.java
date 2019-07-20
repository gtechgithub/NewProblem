package com.example.myproject.canvas.command.impl;

import org.springframework.stereotype.Component;

import com.example.myproject.canvas.command.CanvasCommands;
import com.example.myproject.canvas.exception.CanvasException;

@Component
public class Line extends CanvasCommands {

	private boolean drawLineValidateArguments( final String [] cmd ) {
		
		//expect 2 arguments to be passed 
		if (null == cmd || cmd.length < 5 ) {
			System.out.println("invalid arguments need x1 y1 x2 y2 as arguments");
			return false;
		}
		
		if ( (!canvasDecorator.isNumeric(cmd[1])) || (!canvasDecorator.isNumeric(cmd[2]))  || (!canvasDecorator.isNumeric(cmd[3]))  || (!canvasDecorator.isNumeric(cmd[4])))  {
			System.out.println("invalid numberic argument to build line!!!");
			return false;
		}

		return true;
	}
	
	@Override
	public void draw(final String [] cmd ) {

		try {
			
			//validate the arguments passed
			if (!drawLineValidateArguments(cmd)) {
				return;
			}
			
			int x1 = Integer.parseInt(cmd[1]);
			int y1  = Integer.parseInt(cmd[2]);
			int x2 = Integer.parseInt(cmd[3]);
			int y2  = Integer.parseInt(cmd[4]);
			
			// horizontal line
			if (y1 == y2) {
				
				if (!validateXAxis(x1,0,x2,0)) {
					return;
				}
				
				for (int i = x1; i <= x2 ; i++) {
					canvasDecorator.getConvasValues()[y1][i] = color;
				}
			} else if (x1 == x2) { // vertical line
				
				if (!validateYAxis(0,y1, 0,y2)) {
					return;
				}
				
				for (int i = y1; i <= y2; i++) {
					canvasDecorator.getConvasValues()[i][x1] = color;
				}
			} else {
				System.out.println("could not draw line, at present supports vertical /horizontal line");
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new CanvasException("unbound exception in line draw:" + ex.getMessage());
		} catch (Exception ex) {
			throw new CanvasException("generic exception Recevied in line draw:" + ex.getMessage());
		}

	}

	@Override
	public void fill(String[] cmd) {
		// TODO Auto-generated method stub
		
	}
	

	

}
