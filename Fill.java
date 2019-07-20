package com.example.myproject.canvas.command.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.example.myproject.canvas.command.CanvasCommands;
import com.example.myproject.canvas.models.RowColumn;


@Component
public class Fill extends CanvasCommands{

    private Queue<RowColumn> queue = new LinkedList<>(); 
    
	@Override
	public void draw(int x1, int y1, int x2, int y2) {
	}

	private  boolean doesitContains(RowColumn rowColumn) {
		
		Iterator<RowColumn> iter = queue.iterator();
		while(iter.hasNext()) {
			if (iter.next().equals(rowColumn)) {
				return true;
			}
			
		}
		return false;
	}

	private void checkAndAddInQueue(int row, int col) {
		
		RowColumn rowCol = new RowColumn(row, col);
		if (!doesitContains(rowCol) ) {
			queue.add(rowCol);	
		}
	}
	
	private boolean validateForBoundary(int row, int column, String fillColor) {
	
		//check if x is < canvasDecorator.getWidth()  and y < heigth and x >= 1 and y >=1, 
		// and also check if canvasDecorator.getConvasValues()[row,column] != "X" if so add in the queue
    	
	   	if ( (row < canvasDecorator.getHeight() +1) && (column < canvasDecorator.getWidth()+1) && row >= 1 && column >= 1 
    			&& ( (null == canvasDecorator.getConvasValues()[row] [column]) ||
    			(null != canvasDecorator.getConvasValues()[row] [column] &&  !canvasDecorator.getConvasValues()[row] [column].equals("X")
    			&& !canvasDecorator.getConvasValues()[row] [column].equals(fillColor) )) ) {
	   		return true;
	   	}
	   	
	   	return false;
	}
	
	@Override
	public void fill(int startCol, int startRow, String fillColor) {
	
		
        //add the starting point 
        queue.add((new RowColumn(startRow,startCol)));
		
        while (!queue.isEmpty()) {
        	
        	//Remove the element from the queue, which is obj
        	RowColumn rowColumn = queue.poll();
        	
            //set  canvasDecorator.getConvasValues()[row][column] with O
        	canvasDecorator.getConvasValues()[rowColumn.getRow()][rowColumn.getCol()] = fillColor;
        	
            //x-1, y , check if x is < canvasDecorator.getWidth()  and y < heigth and x >= 1 and y >=1,
        	//and also check if canvasDecorator.getConvasValues()[row,column] != "X" if so add in the queue

        	if ( validateForBoundary((rowColumn.getRow() -1), rowColumn.getCol(), fillColor)) {
        		checkAndAddInQueue(rowColumn.getRow() -1, rowColumn.getCol());
        	}
        	
        	// x+1, y, check as above
        	if ( validateForBoundary(rowColumn.getRow() + 1, rowColumn.getCol(),fillColor)) {
        		checkAndAddInQueue(rowColumn.getRow() + 1, rowColumn.getCol());

        	}

        	//x, y-1, check as above
        	if ( validateForBoundary(rowColumn.getRow(), rowColumn.getCol() -1,fillColor)) {
        		checkAndAddInQueue(rowColumn.getRow(), rowColumn.getCol() -1);
        	}

        	//x, y+1, check as above
        	if ( validateForBoundary(rowColumn.getRow(), rowColumn.getCol() +1,fillColor)) {        		
        		checkAndAddInQueue(rowColumn.getRow(), rowColumn.getCol() + 1);
        	}
        }
		
	}

}
