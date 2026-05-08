abstract class ShipmentOrder implements SummaryPrintable {
    private String orderNumber;
    private String customerName;
    private int distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, int distanceKm, double baseFee, boolean insured, double lastCalculatedPrice) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
        this.lastCalculatedPrice = lastCalculatedPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public double getLastCalculatedPrice() {
        return lastCalculatedPrice;
    }

    public void setLastCalculatedPrice(double lastCalculatedPrice) {
        this.lastCalculatedPrice = lastCalculatedPrice;
    }

    @Override
    public String buildSummaryLine() {
        return "Order Number:" + orderNumber + " Customer: " + customerName +" Type: " + getShipmentType() +" Last calculated price: " + lastCalculatedPrice;
    }

    private void validateOrder(){
        if(customerName == null || customerName.isBlank()){
            throw new IllegalArgumentException("Customer name must not be blank.");
        }
        if(orderNumber == null || orderNumber.isBlank()){
            throw new IllegalArgumentException("Order number must not be blank.");
        }
        if(distanceKm <= 0){
            throw new IllegalArgumentException("Distance must be greater than 0.");
        }
        if(baseFee < 0){
            throw new IllegalArgumentException("Fee must be positive.");
        }
        if(lastCalculatedPrice < 0){
            throw new IllegalArgumentException("Price must be positive.");
        }
    }
    protected void validateSpecificRules(){}

    private double applyInsurance(double price){
        if(insured){
            return price*1.07;
        }
        return price;
    }

    protected double applyBusinessDiscount(double price){
        return price;
    }
    private void printProcessingResult(){

    }
    protected abstract double calculateBasePrice();

    protected abstract double calculateAdditionalFee();

    public abstract String getShipmentType();

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }
}
