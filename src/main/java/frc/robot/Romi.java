import java.util.Scanner;

public class Romi {
  String romiType;
  double feetPerVolt;
  double batteryCapacity;
  double currentBatteryAmount;

  public Romi(String romiType, double feetPerVolt, double batteryCapacity) {
    romiType = this.romiType;
    feetPerVolt = this.feetPerVolt;
    batteryCapacity = this.batteryCapacity;
    currentBatteryAmount = 0;

  }

  public static void main(String[] args) {
    // Create a new Romi object, asking the user (using the scanner class) for the
    // type, batteryCapacity, and feetPerVolt
    Scanner sc = new Scanner(System.in);
    System.out.print("Type: ");
    String romiType = sc.nextLine();
    System.out.print("Feet Travelled Per Volt: ");
    double feetPerVolt = sc.nextDouble();
    System.out.print("Battery Capacity: ");
    double batteryCapacity = sc.nextDouble();
    Romi romi = new Romi(romiType, feetPerVolt, batteryCapacity);

    // Refuel the Romi to its maximum battery capacity

    romi.refuel(batteryCapacity);

    // Print out Romi information
    System.out.println(romi);

    // Ask the user how many feet they want the Romi to drive, then drive that distance, and
    // then report back the Romi's remaining Battery Capacity and range after the drive.
    System.out.print("How far do you want to drive? ");
    double feet = sc.nextDouble();
    romi.drive(feet);
    System.out.println("Remaining Battery Levels: " + romi.getBatteryRemaining() + ", Remaining Range: " + romi.getRange());

    // Ask the user how much voltage they want to add, and then refuel the Romi that amount
    System.out.print("How much battery do you want to add? ");
    double battery = sc.nextDouble();
    romi.refuel(battery);

    // Ask the user how many feet they want the Romi to drive, then drive that distance, and then report back the Romi's remaining battery and range after the drive.
    System.out.print("How far do you want to drive? ");
    feet = sc.nextDouble();
    romi.drive(feet);
    System.out.println("Remaining Battery: " + romi.getBatteryRemaining() + ", Remaining Range: " + romi.getRange());
  }

  public void drive(double distance) {
    double batteryUsed = distance / feetPerVolt;
    currentBatteryAmount -= batteryUsed;

    // drive for distance (in feet), and update battery level accordingly (using feetPerVolt)

  }

  public void refuel(double batteryAmount) {
    // Add batteryAmount of battery energy to the battery
    currentBatteryAmount += batteryAmount;
  }

  public double getBatteryRemaining() {
    // Return the number of volts currently in the battery 
    return currentBatteryAmount;

  }

  public double getRange() {
    // Return the estimated amount of feet the romi can drive based on current battery levels
    // remaining and feetPerVolt (provided to constructor)
    double estimatedDistance = feetPerVolt * currentBatteryAmount;
    return estimatedDistance;

  }

  public String toString() {
    // Return a string representation of the Romi's current state
    // Be sure to include range, type, and battery level.
    return "This romi has a range of " + getRange() + ", it's model is " + romiType + ", and has a battery level of " + batteryCapacity;
  }
}