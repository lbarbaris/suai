public class Nissan extends AbstractFactory {  // это ConcreyeFactory по шаблону
        @Override
        public AbstractTruck productTruck() {
            return new ProductTruck();
        }
    @Override
    public AbstractPickup productPickup() {
        return new ProductPickup();
    }
    @Override
    public AbstractPassenger productPassenger() {
        return new ProductPassenger();
    }
}
