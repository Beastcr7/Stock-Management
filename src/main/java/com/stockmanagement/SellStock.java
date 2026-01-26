package com.stockmanagement;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sell_stock")
public class SellStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_id")
    private Long sellId;

    @ManyToOne
    @JoinColumn(name = "buy_id")
    private BuyStock buyStock;

    @Column(name = "sell_date")
    private LocalDate sellDate;

    @Column(name = "sell_price")
    private double sellPrice;

    @Column(name = "quantity")
    private int quantity;

    // JPA requires default constructor
    public SellStock() {}

    public SellStock(BuyStock buyStock, LocalDate sellDate, double sellPrice, int quantity) {
        this.buyStock = buyStock;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    // -------- GETTERS --------
    public Long getSellId() { return sellId; }
    public BuyStock getBuyStock() { return buyStock; }
    public LocalDate getSellDate() { return sellDate; }
    public double getSellPrice() { return sellPrice; }
    public int getQuantity() { return quantity; }

    // -------- BUSINESS METHOD --------
    public double getSellAmount() {
        return sellPrice * quantity;
    }

    // -------- SETTERS --------
    public void setBuyStock(BuyStock buyStock) { this.buyStock = buyStock; }
    public void setSellDate(LocalDate sellDate) { this.sellDate = sellDate; }
    public void setSellPrice(double sellPrice) { this.sellPrice = sellPrice; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "SellStock{" +
                "ID=" + sellId +
                ", Stock=" + buyStock.getStock().getSymbol() +
                ", SellDate=" + sellDate +
                ", SellPrice=" + sellPrice +
                ", Quantity=" + quantity +
                ", TotalAmount=" + getSellAmount() +
                '}';
    }
}
//doneee