package lib;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DSControlWord;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTime;
import frc.robot.subsystems.Drivetrain;

public abstract class ProceduralRobot extends RobotBase {

    Thread autoThread;
    Drivetrain drive = new Drivetrain();

    private class AutonomousRunnable implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Starting Autonomous Procedure");
                autonomousProcedure();
            } catch (InterruptedException e) {
                System.out.println("Autonomous Disabled");
            }
        }
    }


    @Override
    public void startCompetition() {
        System.out.println("***** Procedural Robot Program Initialization Finished *****");
        HAL.observeUserProgramStarting(); // Tell the HAL process (which will tell the driver station) that the robot is
            // ready to be enabled. This is what causes the driver station to change from "No Robot Code" to "Teleoperation Disabled"



        DSControlWord controlWord = new DSControlWord(); // Container for Driver Station control data (is enabled?, etc)

        boolean isAlreadyAutonomous = false; // Was the robot in autonomous mode on the last iteration of the while loop

        while (true) {
            controlWord.update(); // Fetch new data from the driver station
            try {
                if (controlWord.isDisabled()) {
                    HAL.observeUserProgramDisabled();
                    if (isAlreadyAutonomous) {
                        autoThread.interrupt();
                        isAlreadyAutonomous = false;
                    }
                    sleep(20);
                } else if (controlWord.isTeleop()) {
                    HAL.observeUserProgramTeleop();
                    if (isAlreadyAutonomous) {
                        autoThread.interrupt();
                        isAlreadyAutonomous = false;
                    }
                    sleep(20);
                } else if (controlWord.isTest()) {
                    HAL.observeUserProgramTest();
                    if (isAlreadyAutonomous) {
                        autoThread.interrupt();
                        isAlreadyAutonomous = false;
                    }
                    sleep(20);
                } else if (controlWord.isAutonomous()) {
                    if (!isAlreadyAutonomous) {
                        autoThread = new Thread(new AutonomousRunnable());
                        autoThread.start();
                    }
                    isAlreadyAutonomous = true;
                }
            } catch (InterruptedException e) {
                DriverStation.reportError("Main Thread Interrupted", e.getStackTrace());
            }


        }

    }

    protected void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public abstract void autonomousProcedure() throws InterruptedException;

    public void endCompetition() {

    }

    public void driveTime(double speed, double time) throws InterruptedException {
        Command driveTime = new DriveTime(speed, time, drive);
        while (!driveTime.isFinished()) {
            driveTime.execute();
            sleep(20);
        }
        driveTime.end(false);
    }
}
