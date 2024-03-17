public class DeviceControl {
    // Method to control the device
    String control(Device device, int payload) {
        return device.getCommand(payload);
    }
}
