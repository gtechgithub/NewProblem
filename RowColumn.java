package com.example.myproject.canvas.models;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Component;

@Component
public class RowColumn{
	private int row;
	private int col;

	public RowColumn() {
	}
	
	public RowColumn(int row, int col) {
		this.row = row;
		this.col =  col;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString(){
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		
		return this.row+ this.col;
	}
	
	@Override
	public boolean equals(Object obj) {
		  if(this == obj) 
	            return true; 
		  
		  if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
		  
	        RowColumn rowColumn = (RowColumn) obj; 
	        return (rowColumn.getRow() == this.row && rowColumn.getCol() == this.col); 

	}
}
