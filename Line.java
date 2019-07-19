package com.example.myproject.canvas.command.impl;

import org.springframework.stereotype.Component;

import com.example.myproject.canvas.command.CanvasCommands;

@Component
public class Line extends CanvasCommands {

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
	
		//horizontal line
		if ( y1 == y2) {
			
			for (int i =x1 ;i <= x2; i++) {
				canvasDecorator.getConvasValues()[y1][i] = color;
			}
		}
		else if (x1 == x2) { //vertical line
			for (int i =y1 ;i <= y2; i++) {
				canvasDecorator.getConvasValues()[i][x1] = color;
			}
		}

	}
	
	@Override
	public void fill(int x, int y, String fillColor) {
		// TODO Auto-generated method stub
		
	}


}
