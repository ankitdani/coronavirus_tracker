package io.spring_project.coronavirus_tracker.model;

public class LocationStats {
    private String stats;
    private String country;
    private long latestTotalCases;
    private long diffFromPrevDay;

    public LocationStats() {
    }

    public LocationStats(String stats, String country, long latestTotalCases, long diffFromPrevDay) {
        this.stats = stats;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(long latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public long getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(long diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }
}
