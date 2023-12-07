import java.util.Objects;

public class Client { // это клиент по шаблону, тут мы задаём фабрику, тип машины и создаём машину
    private AbstractPassenger passenger;
    private AbstractTruck truck;
    private AbstractPickup pickup;

    Client(AbstractFactory factory, String car) {
        System.out.println("You choosed: " + factory.getClass().getName());
        if (Objects.equals(car, "Truck")) {
            truck = factory.productTruck();
            truck.createTruck();
        }
        if (Objects.equals(car, "Pickup")){
            pickup = factory.productPickup();
            pickup.createPickup();
        }
        if (Objects.equals(car, "Pickup")){
            passenger = factory.productPassenger();
            passenger.createPassenger();
        }


    }




}
