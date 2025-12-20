package StockManagement;

public class Sector {

    private Long sectorId;
    private String sectorName;
    private double currentIndex;
    private double indexChange;

    public Sector(Long sectorId, String sectorName,
                  double currentIndex, double indexChange) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.currentIndex = currentIndex;
        this.indexChange = indexChange;
    }

    public Long getSectorId() { return sectorId; }
    public String getSectorName() { return sectorName; }
    public double getCurrentIndex() { return currentIndex; }
    public double getIndexChange() { return indexChange; }
}


