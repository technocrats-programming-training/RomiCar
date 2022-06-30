// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILi.b BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import lib.ProceduralRobot;

import java.util.Scanner;

import static lib.Romi.*;


public class Robot extends ProceduralRobot {
  @Override
  public void autonomousProcedure() throws InterruptedException {
    Position position = new Position();
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i<4; i++) {
      System.out.print("Please enter direction to move (up, down, left, right): ");
      String direction = sc.nextLine();
      switch (direction) {
        case "up":
          position.driveUp();
        case "down":
          position.driveDown();
        case "left":
          position.driveLeft();
        case "right":
          position.driveRight();
        default:
          System.out.println("Invalid Instruction");
      }
    }
    System.out.println("Returning Home Now...");
    position.returnToHome();

  }
}
