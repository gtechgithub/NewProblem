package com.example.myproject.canvas.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myproject.canvas.impl.DrawCanvas;

public abstract class CanvasCommands {

	@Autowired
    protected DrawCanvas canvasDecorator;
	
	public final String color = "X";
	public final String fillColor = "O";

	public abstract void draw(int x1, int y1, int x2, int y2);

	public abstract void fill(int x, int y, String fillColor);
}
