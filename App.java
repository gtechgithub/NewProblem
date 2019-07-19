package com.example.myproject.canvas;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.myproject.canvas.command.impl.Fill;
import com.example.myproject.canvas.command.impl.Line;
import com.example.myproject.canvas.command.impl.Rectangle;
import com.example.myproject.canvas.impl.DrawCanvas;

@SpringBootApplication
public class App implements CommandLineRunner
{
	
	@Autowired
	DrawCanvas drawConvas;
	
	@Autowired
	Rectangle rectangle;
	
	@Autowired
	Line  line;
	
	@Autowired
	Fill fill;
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
        
        Scanner scan = new Scanner(System.in);
        String command = new String();
        while(!command.equals("Q")) {
            System.out.println("\nEnter command:");
            command = scan.nextLine();
            draw(command);
        }
        System.out.println("Program Exited!!!!!!");
        scan.close();
	}
	
	
	private void draw(String command) throws NumberFormatException {
        char ch = command.charAt(0);
        String[] cmd;
        try {
            switch(ch) {
                case 'C' :
                    cmd = command.split(" ");
                    drawConvas.drawShape(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]));
                    drawConvas.printValues();
                    break;
                case 'L' :
                    cmd = command.split(" ");
                    line.draw(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
                    drawConvas.printValues();
                    break;
                case 'R' :
                    cmd = command.split(" ");
                    rectangle.draw(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
                    drawConvas.printValues();
                    break;
                case 'B' :
                    cmd = command.split(" ");
                    fill.fill(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Character.toString(cmd[3].charAt(0)) );
                    drawConvas.printValues();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nSome Error Occurred !!\n");
        } catch (Exception e) {
            System.out.println("\nSome Error Occurred : " + e.getMessage() + "\n Please try again");
        }
    }
}

