package com.example.myproject.canvas;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.myproject.canvas.command.impl.Fill;
import com.example.myproject.canvas.command.impl.Line;
import com.example.myproject.canvas.command.impl.Rectangle;
import com.example.myproject.canvas.exception.CanvasException;
import com.example.myproject.canvas.impl.DrawCanvas;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	DrawCanvas drawConvas;

	@Autowired
	Rectangle rectangle;

	@Autowired
	Line line;

	@Autowired
	Fill fill;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Override
	public void run(String... args) {

		try(Scanner scan = new Scanner(System.in)){
		String command = new String();
		while (!command.equals("Q")) {
			System.out.println("\nEnter command:");
			command = scan.nextLine();
			draw(command);
		}
		System.out.println("Program Exited!!!!!!");
		} catch(CanvasException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void draw(String command) {
		
		String[] cmd;
		try {
			char ch = command.charAt(0);
			switch (ch) {
			case 'C':
				cmd = command.split(" ");
				drawConvas.drawShape(cmd);
				drawConvas.printValues();
				break;
			case 'L':
				cmd = command.split(" ");
				line.draw(cmd);
				drawConvas.printValues();
				break;
			case 'R':
				cmd = command.split(" ");
				rectangle.draw(cmd);
				drawConvas.printValues();
				break;
			case 'B':
				cmd = command.split(" ");
				fill.fill(cmd);
				drawConvas.printValues();
				break;
			}
		}catch (CanvasException ex) {
			throw ex;
		}
		catch (Exception e) {
			throw new CanvasException("Some Error Occurred : " + e.getMessage() + " Please try again");
		}
	}

}
