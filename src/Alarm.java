public class Alarm extends Device {
    private int activeState = 0;
    @Override
    String getCommand(int payload) {
        if (payload == 1) {
            activateAlarm();
            return "Alarm activated. You can leave the house without worries!";
        } else if (payload == 2) {
            deactivateAlarm();
            return "Alarm deactivated. If you are not at home, take measures!";
        } else {
            return "Invalid command";
        }
    }

    public void activateAlarm() {
        if (activeState == 0){
            activeState = 1;
        }
    }
    public void deactivateAlarm() {
        if (activeState == 1){
            activeState = 0;
        }
    }

    public String getActiveStatus() {
        if (activeState == 1) {
            return "activated.";
        } else {
            return "deactivated.";
        }
    }
}
