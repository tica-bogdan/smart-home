public class Lights extends Device {
    private boolean kitchenActiveState = false;
    private boolean livingRoomActiveState = false;
    private boolean bedroomActiveState = false;

    @Override
    String getCommand(int payload) {
        toggleLights(payload);
        return "";
    }

    public void toggleLights(int room){
        // Toggle the active state of the lights in the specified room
        switch (room) {
            case 1:
                kitchenActiveState = !kitchenActiveState;
                break;
            case 2:
                livingRoomActiveState = !livingRoomActiveState;
                break;
            case 3:
                bedroomActiveState = !bedroomActiveState;
                break;
            default:
                break; // Handle invalid room
        }
    }

    public String getLightsStatus() {
        return "Kitchen lights: " + (kitchenActiveState ? "on  " : "off  ") + "\n" +
                "Living Room lights: " + (livingRoomActiveState ? "on  " : "off  ") + "\n" +
                "Bedroom lights: " + (bedroomActiveState ? "on  " : "off  ");
    }
}