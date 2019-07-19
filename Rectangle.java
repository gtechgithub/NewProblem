package com.example.myproject.canvas.command.impl;

import org.springframework.stereotype.Component;

import com.example.myproject.canvas.command.CanvasCommands;

@Component
public class Rectangle extends CanvasCommands {

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		for (int i = y1; i <= y2 ; i++) {
			for (int j = x1; j <= x2; j++ ) {
				if (!(i > y1 && i < y2 && j != x1 && j != x2))
					canvasDecorator.getConvasValues()[i][j] = "X";
			}
		}

	}
	
	@Override
	public void fill(int x, int y, String fillColor) {
		// TODO Auto-generated method stub
		
	}
}
