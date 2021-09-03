package seng202.group6.Models;
import java.lang.Math;
import java.lang.*;


public class Crime {
    //TODO add block and all other missed classes
    private String caseNumber;
    private Date date;
    private String block;
    private String IUCR;
    private String primaryDescription;
    private String secondaryDescription;
    private String locationDescription;
    private boolean arrest;
    private boolean domestic;
    private int beat;
    private int ward;
    private String FBI; //FBI crime code
    private String latitude;
    private String longitude;

    /**
     * Empty constructor for making a blank crime object
     */
    public Crime() {}

    public Crime(String caseNumber, String date, String block, String IUCR, String primaryDescription, String secondaryDescription, String arrest, String domestic, int beat, int ward, String FBI, String locationDescription, String latitude, String longitude) {
        this.caseNumber = caseNumber;
        this.date = new Date(date);
        this.block = block;
        this.IUCR = IUCR;

        this.primaryDescription = primaryDescription;
        this.secondaryDescription = secondaryDescription;

        this.arrest = arrest.equals("Y");
        this.domestic = domestic.equals("Y");


        this.beat = beat;
        this.ward = ward;
        this.FBI = FBI;
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

    public String getFBI() { return FBI; }

    public String getBlock() { return block; }

    public String getIUCR() { return IUCR; }

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

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setIUCR(String IUCR) {
        this.IUCR = IUCR;
    }

    public void setPrimaryDescription(String primaryDescription) {
        this.primaryDescription = primaryDescription;
    }

    public void setSecondaryDescription(String secondaryDescription) {
        this.secondaryDescription = secondaryDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public void setArrest(boolean arrest) {
        this.arrest = arrest;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public void setFBI(String FBI) {
        this.FBI = FBI;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Double getDistanceBetween(Crime otherCrime){
        /**
         * Uses vector's I have forgotten level 3 math.
         * @return Returns a float in KILOMETERS!!!
         */
        Double x = (Double.parseDouble(this.longitude) - Double.parseDouble(otherCrime.longitude)) * 111; //Don't ask me what is going on I don't know either
        Double y = (Double.parseDouble(this.latitude) - Double.parseDouble(otherCrime.latitude)) * 111;  //But it works. The *111 is to convert to KM's
        return Math.hypot(x, y); //Again return value is in km
    }

//    public Date getTimeDifference(Crime otherCrime){
//        /** Should be noted that it returns a date object
//         *
//         */
//    }
}
