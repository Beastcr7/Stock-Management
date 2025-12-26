package StockManagement;

public class Stock {

    private Long stockId;
    private String symbol;
    private String companyName;
    private Sector sector;
    private double currentMarketPrice;

    public Stock(Long stockId, String symbol, String companyName,
                 Sector sector, double currentMarketPrice) {
        this.stockId = stockId;
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.currentMarketPrice = currentMarketPrice;
    }

    // -------- GETTERS --------
    public Long getStockId() {
        return stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Sector getSector() {
        return sector;
    }

    public double getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    // -------- SETTERS --------
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setCurrentMarketPrice(double currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }
}
