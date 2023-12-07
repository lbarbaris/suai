public class Main {
    public static void main(String[] args) {
        //создаём фабрику и производим на ней выбранную машину
        //ВАЖНО!!! писать названия с большой буквы: Truck, Pickup, Passenger
        Client c = new Client(new Nissan(), "Truck");
    }
}