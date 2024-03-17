class Fridge extends Device{
    private int temperature = 4; // Standard fridge temperature
    @Override
    String getCommand(int payload){
        if (payload == 1) {
            increaseTemperature();
            return "Fridge temperature increased.";
        } else if (payload == 2){
            decreaseTemperature();
            return "Fridge temperature decreased.";
        } else {
            return "Invalid command.";
        }
    }

    public void increaseTemperature() {
        if (temperature < 8) { // increases temperature to maximum 8
            temperature++;
        }
    }
    public void decreaseTemperature() {
        if (temperature > 2) { // decreases temperature to minimum 2
            temperature--;
        }
    }

    public int getTemperature(){
        return temperature;
    }
}
