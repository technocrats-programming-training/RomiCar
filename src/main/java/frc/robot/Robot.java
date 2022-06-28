// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILi.b BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpifirst.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import lib.ProceduralRobot;

import static lib.Romi.driveTime;
import static lib.Romi.turnTime;


public class Robot extends ProceduralRobot {
  @Override
  public void autonomousProcedure() throws InterruptedException {
    // Write your code here:
    Scanner sc = new Scanner(System.in);
    System.out.print("Type: ");
    String romiType = sc.nextLine();
    System.out.print("Feet Travelled Per Volt: ");
    double feetPerVolt = sc.nextDouble();
    System.out.print("Battery Capacity: ");
    double batteryCapacity = sc.nextDouble();
    Romi romi = new Romi(romiType, feetPerVolt, batteryCapacity);
    
    //Buttons call methods
    sleepSeconds(10); 
    System.out.println("In one second, press buttonA to drive");
    sleepSeconds(1);
    if (buttonA.get()) {
        myCar.drive(); 
    }
  }
}
