package seng202.group6.Models;


public class Crime {
    private String caseNumber;
    private Date date; //TODO update to Date
    private String primaryDescription;
    private String secondaryDescription;
    private boolean arrest;
    private boolean domestic;
    private int beat;
    private int ward;
    private String locationDescription;
    private String latitude;
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
}
