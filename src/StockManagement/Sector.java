package StockManagement;

import java.math.BigDecimal;

public class Sector {

    private Long sectorId;
    private String sectorName;
    private BigDecimal currentIndex;

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
