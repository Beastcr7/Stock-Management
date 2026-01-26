package com.stockmanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    private Long sectorId;

    @Column(nullable = false , unique = true)
    private String sectorName;

    private BigDecimal currentIndex;

    // 🔹 REQUIRED by Hibernate
    public Sector() {
    }

    // 🔹 Your constructor (for manual object creation)
    public Sector(Long sectorId, String sectorName, BigDecimal currentIndex) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.currentIndex = currentIndex;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public BigDecimal getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(BigDecimal currentIndex) {
        this.currentIndex = currentIndex;
    }
}
 //Doneeee