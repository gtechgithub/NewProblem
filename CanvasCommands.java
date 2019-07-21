package com.example.myproject.canvas.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myproject.canvas.impl.DrawCanvas;

public abstract class CanvasCommands {

	@Autowired
	protected DrawCanvas canvasDecorator;

	public final String color = "X";
	public final String fillColor = "O";

	public abstract void draw (final String [] cmd);

	public abstract void fill (final String[] cmd);

	public void setCanvasDec(final DrawCanvas canvas) {
		canvasDecorator  = canvas;
	}

	public boolean checkEndpointsLessThanBoundary(int p1, int p2) {

		if (p1 < 1 || p2 < 1) {
			System.out.println("coordinates lower than the canvas boundary!!!!");
			return false;
		}

		return true;
	}

	public boolean checkEndpointsLessThanBoundary(int p1, int p2,boolean log) {

		if (p1 < 1 || p2 < 1) {
			if (log) {
				System.out.println("coordinates lower than the canvas boundary!!!!");
			}
			return false;
		}

		return true;
	}
	
	private boolean checkStartAndEndpointsValid(int p1, int p2) {
		if (p1 > p2) {
			System.out.println("Unsupported right now for starting coordinates more than end coordinates");
			return false;

		}

		return true;
	}

	public boolean validateXaxisMoreThanBoundary(int x1) {
		if (x1 > canvasDecorator.getWidth()) {
			System.out.println("coordinates exceeds the canvas boundary!!!!");
			return false;
		}

		return true;
	}

	public boolean validateXaxisMoreThanBoundary(int x1,boolean log) {
		if (x1 > canvasDecorator.getWidth()) {
			if (log) {
				System.out.println("coordinates exceeds the canvas boundary!!!!");
			}
			return false;
		}

		return true;
	}
	
	public boolean validateYaxisMoreThanBoundary(int y1) {

		if (y1 > canvasDecorator.getHeight()) {
			System.out.println("coordinates exceeds the canvas boundary!!!!");
			return false;

		}

		return true;
	}

	public boolean validateYaxisMoreThanBoundary(int y1, boolean log) {

		if (y1 > canvasDecorator.getHeight()) {
			if (log) {
				System.out.println("coordinates exceeds the canvas boundary!!!!");
			}
			return false;

		}

		return true;
	}
	
	public boolean validateXAxis(int x1, int y1, int x2, int y2) {

		if ((!checkEndpointsLessThanBoundary(x1, x2)) || (!validateXaxisMoreThanBoundary(x1))
				|| (!validateXaxisMoreThanBoundary(x2)) || (!checkStartAndEndpointsValid(x1, x2))) {
			return false;
		}

		return true;
	}

	public boolean validateYAxis(int x1, int y1, int x2, int y2) {

		if ((!checkEndpointsLessThanBoundary(y1, y2)) || (!validateYaxisMoreThanBoundary(y1))
				|| (!validateYaxisMoreThanBoundary(y2)) || (!checkStartAndEndpointsValid(y1, y2))) {
			return false;
		}

		return true;
	}

}
