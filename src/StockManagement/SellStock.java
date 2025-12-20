package StockManagement;

import java.time.LocalDate;

public class SellStock {

    private Long sellId;
    private BuyStock buyStock;
    private LocalDate sellDate;
    private double sellPrice;
    private int quantitySold;

    public SellStock(Long sellId, BuyStock buyStock,
                     LocalDate sellDate, double sellPrice,
                     int quantitySold) {
        this.sellId = sellId;
        this.buyStock = buyStock;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.quantitySold = quantitySold;
    }

    public BuyStock getBuyStock() { return buyStock; }
    public double getSellAmount() {
        return sellPrice * quantitySold;
    }
}

