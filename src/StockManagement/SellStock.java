package StockManagement;

import java.time.LocalDate;

public class SellStock {

    private Long sellId;
    private BuyStock buyStock;
    private LocalDate sellDate;
    private double sellPrice;
    private int quantity;

    public SellStock(Long sellId, BuyStock buyStock,
                     LocalDate sellDate, double sellPrice, int quantity) {
        this.sellId = sellId;
        this.buyStock = buyStock;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    // -------- GETTERS --------
    public Long getSellId() {
        return sellId;
    }

    public BuyStock getBuyStock() {
        return buyStock;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    // -------- BUSINESS METHOD --------
    public double getSellAmount() {
        return sellPrice * quantity;
    }
}
