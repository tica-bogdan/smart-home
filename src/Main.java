import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeviceControl deviceControl = new DeviceControl();
        Map<Integer, Device> devices = new HashMap<>(); // Map that stores devices

        // Menu loop
        while (true) {
            System.out.println("Choose a device:");
            System.out.println("1. Fridge");
            System.out.println("2. Oven");
            System.out.println("3. Alarm");
            System.out.println("4. Lights");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            if (choice == 0){
                break;
            }

            Device currentDevice = devices.getOrDefault(choice, null); // get the device from the map

            switch (choice) {
                case 1:
                    if (currentDevice == null){
                        currentDevice = new Fridge(); // create new device if it doesn't already exist
                        devices.put(choice, currentDevice); // store the device in the map
                    }
                    System.out.println("Actual status is: " + getStatus(currentDevice)); // print the status
                    break;
                case 2:
                    if (currentDevice == null){
                        currentDevice = new Oven(); // create new device if it doesn't already exist
                        devices.put(choice, currentDevice); // store the device in the map
                    }
                    System.out.println("Actual status is: " + getStatus(currentDevice)); // print the status
                    break;
                case 3:
                    if (currentDevice == null){
                        currentDevice = new Alarm(); // create new device if it doesn't already exist
                        devices.put(choice, currentDevice); // store the device in the map
                    }
                    System.out.println("Alarm status is: " + getStatus(currentDevice)); // print the status
                    break;
                case 4:
                    if (currentDevice == null) {
                        currentDevice = new Lights();
                        devices.put(choice, currentDevice);
                    }
                    System.out.println(getStatus(currentDevice));
                    while (true) {
                        System.out.println("Choose a room to toggle lights:");
                        System.out.println("1. Kitchen");
                        System.out.println("2. Living Room");
                        System.out.println("3. Bedroom");
                        System.out.println("0. Back to main menu");

                        int roomChoice = scanner.nextInt();
                        if (roomChoice >= 1 && roomChoice <= 3){
                            String message = deviceControl.control(currentDevice, roomChoice);
                            System.out.println(message);
                            System.out.println("Lights after status change:");
                            System.out.println(((Lights) currentDevice).getLightsStatus());
                        } else if (roomChoice == 0) {
                            break;
                        } else {
                            System.out.println("Invalid room choice.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            // Device control loop
            if (choice <= 3) {
                while (true) {
                    System.out.println("What do you want to do? Choose an action: ");
                    if (choice == 1 || choice == 2) {
                        System.out.println("1. Increase temperature");
                        System.out.println("2. Decrease temperature");
                    } else {
                        System.out.println("1. Activate alarm");
                        System.out.println("2. Deactivate alarm");
                    }
                    System.out.println("0. Choose another device");

                    int actionChoice = scanner.nextInt();

                    switch (actionChoice){
                        case 1:
                        case 2:
                            String message = deviceControl.control(currentDevice, actionChoice);
                            if (message != null) {
                                System.out.println(message);
                                System.out.println("Actual status is: " + getStatus(currentDevice)); // Print the status after the action
                            } else {
                                System.out.println("Invalid command.");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }

                    if (actionChoice == 0) {
                        break; // break out the device control loop to choose another device
                    }
                }
            }
        }
        scanner.close();
    }

    // Method to get the status of the device
    private static String getStatus(Device device) {
        if (device instanceof Fridge) {
            return "Temperature: " + ((Fridge) device).getTemperature();
        } else if (device instanceof Oven) {
            return "Temperature: " + ((Oven) device).getTemperature();
        } else if (device instanceof Alarm) {
            return ((Alarm) device).getActiveStatus();
        } else if (device instanceof Lights){
            return ((Lights) device).getLightsStatus();
        } else {
            return "Status not available";
        }
    }

}
