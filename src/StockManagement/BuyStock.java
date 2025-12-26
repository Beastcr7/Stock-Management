package StockManagement;

import java.time.LocalDate;

public class BuyStock {

    private Long buyId;
    private Stock stock;
    private LocalDate buyDate;
    private double buyPrice;
    private int quantity;

    public BuyStock(Long buyId, Stock stock,
                    LocalDate buyDate, double buyPrice, int quantity) {
        this.buyId = buyId;
        this.stock = stock;
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
    }

    // -------- GETTERS --------
    public Long getBuyId() {
        return buyId;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    // -------- SETTERS --------
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void reduceQuantity(int soldQty) {
        this.quantity -= soldQty;
    }
}
