package com.example.myproject.canvas.command.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.example.myproject.canvas.command.CanvasCommands;
import com.example.myproject.canvas.exception.CanvasException;
import com.example.myproject.canvas.models.RowColumn;

@Component
public class Fill extends CanvasCommands {

	private Queue<RowColumn> queue = new LinkedList<>();

	private boolean doesitContains(RowColumn rowColumn) {

		Iterator<RowColumn> iter = queue.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(rowColumn)) {
				return true;
			}

		}
		return false;
	}

	private void checkAndAddInQueue(int row, int col) {

		RowColumn rowCol = new RowColumn(row, col);
		if (!doesitContains(rowCol)) {
			queue.add(rowCol);
		}
	}

	private boolean validateForBoundary(int row, int column, String fillColor) {

		// check if x is < canvasDecorator.getWidth() and y < heigth and x >= 1 and y
		// >=1,
		// and also check if canvasDecorator.getConvasValues()[row,column] != "X" if so
		// add in the queue

		if ((!checkEndpointsLessThanBoundary(row, column,false)) || (!validateYaxisMoreThanBoundary(row,false))
				|| (!validateXaxisMoreThanBoundary(column,false))) {
			return false;
		}

		if ((null == canvasDecorator.getConvasValues()[row][column])
				|| (null != canvasDecorator.getConvasValues()[row][column]
						&& !canvasDecorator.getConvasValues()[row][column].equals("X")
						&& !canvasDecorator.getConvasValues()[row][column].equals(fillColor))) {
			return true;
		}

		return false;
	}

	private boolean fillValidateArguments(final String[] cmd) {

		// expect 2 arguments to be passed
		if (null == cmd || cmd.length < 4) {
			System.out.println("invalid arguments need x1 y1 fillchar as arguments");
			return false;
		}

		if ((!canvasDecorator.isNumeric(cmd[1])) || (!canvasDecorator.isNumeric(cmd[2]))
				|| (null == cmd[3] || cmd[3].isEmpty())) {
			System.out.println("invalid numberic argument to do Fill!!!");
			return false;
		}

		return true;
	}

	private void clearFill() {
		
		/*
	    IntStream.range(0, canvasDecorator.getHeight()).forEach( n -> {
	      IntStream.range(0, canvasDecorator.getWidth()).forEach( j -> {

	    	  if (!canvasDecorator.getConvasValues()[n][j].equals(color)) {
	    		  canvasDecorator.getConvasValues()[n][j] = null;
	    	  }
	      });

	    });
	    */
	    
	    for (int i = 1; i <= canvasDecorator.getHeight() ; i++  ) {
	    	
	    	for (int j = 1; j <= canvasDecorator.getWidth() ; j++  ) {
	    		if (null!=canvasDecorator.getConvasValues()[i][j] &&  !canvasDecorator.getConvasValues()[i][j].equals(color)) {
		    		  canvasDecorator.getConvasValues()[i][j] = null;

	    		}
	    	}
	    }
	    
	}
	
	@Override
	// public void fill(int startCol, int startRow, String fillColor) {
	public void fill(final String cmd[]) {
		try {

			if (!fillValidateArguments(cmd)) {
				return;
			}

			int startCol = Integer.parseInt(cmd[1]);
			int startRow = Integer.parseInt(cmd[2]);
			String fillColor = cmd[3];

			//clear the fill
			clearFill();
			
			// add the starting point, validate before adding into the Queue.
			if (!validateForBoundary(startRow, startCol, fillColor)) {
				System.out.println("unable to fill, check for coordinates");
				return;
			}

			checkAndAddInQueue(startRow, startCol);

			while (!queue.isEmpty()) {

				// Remove the element from the queue, which is obj
				RowColumn rowColumn = queue.poll();

				// set canvasDecorator.getConvasValues()[row][column] with O
				canvasDecorator.getConvasValues()[rowColumn.getRow()][rowColumn.getCol()] = fillColor;

				// x-1, y , check if x is < canvasDecorator.getWidth() and y < heigth and x >= 1
				// and y >=1,
				// and also check if canvasDecorator.getConvasValues()[row,column] != "X" if so
				// add in the queue

				if (validateForBoundary((rowColumn.getRow() - 1), rowColumn.getCol(), fillColor)) {
					checkAndAddInQueue(rowColumn.getRow() - 1, rowColumn.getCol());
				}

				// x+1, y, check as above
				if (validateForBoundary(rowColumn.getRow() + 1, rowColumn.getCol(), fillColor)) {
					checkAndAddInQueue(rowColumn.getRow() + 1, rowColumn.getCol());

				}

				// x, y-1, check as above
				if (validateForBoundary(rowColumn.getRow(), rowColumn.getCol() - 1, fillColor)) {
					checkAndAddInQueue(rowColumn.getRow(), rowColumn.getCol() - 1);
				}

				// x, y+1, check as above
				if (validateForBoundary(rowColumn.getRow(), rowColumn.getCol() + 1, fillColor)) {
					checkAndAddInQueue(rowColumn.getRow(), rowColumn.getCol() + 1);
				}
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new CanvasException("unbound exception in fill:" + ex.getMessage());
		} catch (Exception ex) {
			throw new CanvasException("generic exception Recevied in fill:" + ex.getMessage());
		}

	}

	@Override
	public void draw(String[] cmd) {
		// TODO Auto-generated method stub

	}
}
