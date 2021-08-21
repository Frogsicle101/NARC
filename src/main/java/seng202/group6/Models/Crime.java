package seng202.group6.Models;


public class Crime {
    //TODO add block and all other missed classes
    private String caseNumber;
    private Date date;
    private String primaryDescription;
    private String secondaryDescription;
    private boolean arrest;
    private boolean domestic;
    private int beat;
    private int ward;
    private String locationDescription;
    private String latitude; //TODO Make location class
    private String longitude;

    public Crime(String caseNumber, String date, String primaryDescription, String secondaryDescription, String arrest, String domestic, int beat, int ward, String locationDescription, String latitude, String longitude) {
        this.caseNumber = caseNumber;
        this.date = new Date(date);
        this.primaryDescription = primaryDescription;
        this.secondaryDescription = secondaryDescription;
        if (arrest == "Y") {
            this.arrest = true;
        } else {
            this.arrest = false;
        }

        if (domestic == "Y") {
            this.domestic = true;
        } else {
            this.domestic = false;
        }

        this.beat = beat;
        this.ward = ward;
        this.locationDescription = locationDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getPrimaryDescription() {
        return primaryDescription;
    }

    public String getSecondaryDescription() {
        return secondaryDescription;
    }

    public boolean isArrest() {
        return arrest;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public int getBeat() {
        return beat;
    }

    public int getWard() {
        return ward;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
