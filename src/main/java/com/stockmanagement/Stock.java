package com.stockmanagement;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id" , nullable = false)
    private Sector sector;

    @Column(name = "current_market_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal currentMarketPrice;

    // JPA requires default constructor
    public Stock() {}

    public Stock(String symbol, String companyName, Sector sector, BigDecimal currentMarketPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.currentMarketPrice = currentMarketPrice;
    }

    // GETTERS
    public Long getStockId() { return stockId; }
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public Sector getSector() { return sector; }
    public BigDecimal getCurrentMarketPrice() { return currentMarketPrice; }

    // SETTERS
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setSector(Sector sector) { this.sector = sector; }
    public void setCurrentMarketPrice(BigDecimal currentMarketPrice) { this.currentMarketPrice = currentMarketPrice; }

    @Override
    public String toString() {
        return "Stock{" +
                "ID=" + stockId +
                ", Symbol='" + symbol + '\'' +
                ", Company='" + companyName + '\'' +
                ", Sector=" + sector.getSectorName() +
                ", Price=" + currentMarketPrice +
                '}';
    }
}

//donee