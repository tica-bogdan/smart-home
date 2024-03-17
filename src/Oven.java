class Oven extends Device{
    private int temperature = 100; // Standard oven temperature
    @Override
    String getCommand(int payload){
        if (payload == 1) {
            increaseTemperature();
            return "Oven temperature increased.";
        } else if (payload == 2){
            decreaseTemperature();
            return "Oven temperature decreased.";
        } else {
            return "Invalid command.";
        }
    }

    public void increaseTemperature() {
        if (temperature < 250) { // increases temperature to maximum 250
            temperature += 10;
        }
    }
    public void decreaseTemperature() {
        if (temperature > 50) { // decreases temperature to minimum 50
            temperature -= 10;
        }
    }

    public int getTemperature(){
        return temperature;
    }
}
