package frc.robot;

import static lib.Romi.driveDistance;
import static lib.Romi.turnDegrees;

public class Position {

    int xPosition = 0;
    int yPosition = 0;
    int positionalPointing = 0;

    public void driveUp() throws InterruptedException {
        turnDegrees(-positionalPointing);
        driveDistance(10);
        yPosition += 10;
    }

    public void driveDown() throws InterruptedException {
        turnDegrees(-positionalPointing);
        driveDistance(-10);
        yPosition -= 10;
    }

    public void driveRight() throws InterruptedException {
        turnDegrees(90 - positionalPointing);
        positionalPointing = 90;
        driveDistance(10);
        xPosition += 10;
    }

    public void driveLeft() throws InterruptedException {
        turnDegrees(-90 - positionalPointing);
        driveDistance(10);
        turnDegrees(-90);
        positionalPointing = -90;
        xPosition -= 10;
    }

    public void returnToHome() {

    }

    public String toString() {
        return "(" + xPosition + ", " + yPosition + ")";
    }
}
