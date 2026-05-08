public class PickupPointShipment extends ShipmentOrder {
    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment(String orderNumber, String customerName, int distanceKm, double baseFee, boolean insured, String lockerSize, boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    @Override
    protected double calculateBasePrice() {
        return getBaseFee() + getDistanceKm() * 0.75;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = 0;
        if (fragile) {
            fee += 12;
        }
        if (lockerSize.contains("S")) {
            fee += 5;
        }
        else if (lockerSize.contains("M")) {
            fee += 10;
        }
        else{
            fee += 18;
        }
        return fee;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }

    @Override
    protected void validateSpecificRules() {
        super.validateSpecificRules();
        if(!((lockerSize.equals("S") || lockerSize.equals("M") || lockerSize.equals("L"))) ){
            throw new RuntimeException("Invalid locker size");
        }
    }
}
