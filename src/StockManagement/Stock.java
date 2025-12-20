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

    public Long getStockId() { return stockId; }
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public Sector getSector() { return sector; }
    public double getCurrentMarketPrice() { return currentMarketPrice; }
}
