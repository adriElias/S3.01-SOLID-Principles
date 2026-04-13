package I._new;

public class Main {
     public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.turnOn();
        airConditioner.heat();
        airConditioner.cool();
        airConditioner.turnOff();

        WashingMachine washingMachine = new WashingMachine();
        washingMachine.turnOn();
        washingMachine.wash();
        washingMachine.turnOff();
    }
}
