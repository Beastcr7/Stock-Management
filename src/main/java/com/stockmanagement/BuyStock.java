package com.stockmanagement;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "buy_stock")
public class BuyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long buyId;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "buy_date")
    private LocalDate buyDate;

    @Column(name = "buy_price")
    private double buyPrice;

    @Column(name = "quantity")
    private int quantity;

    // JPA requires default constructor
    public BuyStock() {}

    public BuyStock(Stock stock, LocalDate buyDate, double buyPrice, int quantity) {
        this.stock = stock;
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
    }

    // -------- GETTERS --------
    public Long getBuyId() { return buyId; }
    public Stock getStock() { return stock; }
    public LocalDate getBuyDate() { return buyDate; }
    public double getBuyPrice() { return buyPrice; }
    public int getQuantity() { return quantity; }

    // -------- SETTERS --------
    public void setStock(Stock stock) { this.stock = stock; }
    public void setBuyDate(LocalDate buyDate) { this.buyDate = buyDate; }
    public void setBuyPrice(double buyPrice) { this.buyPrice = buyPrice; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Reduce quantity when selling
    public void reduceQuantity(int soldQty) { this.quantity -= soldQty; }

    @Override
    public String toString() {
        return "BuyStock{" +
                "ID=" + buyId +
                ", Stock=" + stock.getSymbol() +
                ", BuyDate=" + buyDate +
                ", Price=" + buyPrice +
                ", Quantity=" + quantity +
                '}';
    }
}
//done