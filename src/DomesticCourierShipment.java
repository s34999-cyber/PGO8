public class DomesticCourierShipment extends ShipmentOrder {
    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName, int distanceKm, double baseFee, boolean insured, double packageWeightKg, boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.weekendDelivery = weekendDelivery;
        this.packageWeightKg = packageWeightKg;
    }

    @Override
    protected double calculateBasePrice() {
        return getBaseFee() + getDistanceKm() * 1.20;
    }

    @Override
    protected double calculateAdditionalFee() {
        if(weekendDelivery) {
            return (packageWeightKg * 4.0)+25;
        }
        return packageWeightKg * 4.0;
    }

    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if(getDistanceKm()>= 300){
            return price*0.95;
        }
        return price;
    }
}
