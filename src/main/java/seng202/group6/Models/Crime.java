package seng202.group6.Models;
import java.lang.Math;
import java.time.LocalDateTime;

//TODO put analytical stuff into services as static methods
/**
 * Crime is a model class, used to model each class that is fed into the database.
 * It stores all attributes of the crime it represents
 *
 */

public class Crime {
    private String caseNumber;
    private LocalDateTime date ;
    private String block ;
    private String IUCR;
    private String primaryDescription;
    private String secondaryDescription;
    private String locationDescription;
    private boolean arrest;
    private boolean domestic;
    private int beat;
    private int ward;
    private String FBI; //FBI crime code
    private double latitude;
    private double longitude;

    /**
     * Empty constructor for making a blank crime object
     */
    public Crime() {}

    /**
     * Bug fixing time, will be delete/used later
     */
     public Crime(String case_id, String occurrence_date, String block, String iucr, String primary_description, String secondary_description,
                  boolean arrest, boolean domestic, int beat, int ward, String fbi, String location, double latitude, double longitude) {
         this.caseNumber = case_id;
         this.date = parseDateString(occurrence_date);
         this.block = block;
         this.IUCR = iucr;
         this.primaryDescription = primary_description;
         this.secondaryDescription = secondary_description;
         this.arrest = arrest;
         this.domestic = domestic;
         this.beat = beat;
         this.ward = ward;
         this.FBI = fbi;
         this.locationDescription = location;
         this.latitude = latitude;
         this.longitude = longitude;
     }





    /**
     * A constructor for type crime that gets fed a series of strings representing the various variables within it,
     * all parameters are fed in as strings
     * @param caseNumber A string representing the case number of the crime, two Letters followed by 6 digits of form AB123456
     * @param date A string representing the date of the crime in format MM/DD/YYYY Hour/Minute/Second AM/PM
     * @param block A string representing the block the crime occurred at, presented as a zip code and streets with the last two digits anonymized, an
     *              example is 073XX S SOUTH SHORE DR
     * @param IUCR A string representing the Illinois Uniform Crime Reporting code
     * @param primaryDescription A string representing the textual description of the crime i.e. THEFT/ MURDER
     * @param secondaryDescription A string representing further detail of the nature of the crime, i.e. if the primary is
     *                             THEFT the secondary might be OVER $500
     * @param arrest A string of form "Y" or "N" representing whether the perpetrator of the crime was arrested
     * @param domestic A string of form "Y" or "N" representing whether the crime was domestic or not
     * @param beat A string representing the number of the police district the crime occurred in
     * @param ward A string of a number representing the election district the crime occurred in
     * @param FBI A string representing the FBI code of the crime
     * @param locationDescription A string giving further detail about the location of the crime
     * @param latitude A string representing the latitudinal coordinates of the crime
     * @param longitude A string representing the longitudinal coordinates of the crime
     */
    public Crime(String caseNumber, String date, String block, String IUCR, String primaryDescription,
                 String secondaryDescription, String locationDescription, String arrest, String domestic,
                 int beat, int ward, String FBI, String latitude, String longitude) {
        this.caseNumber = caseNumber;
        this.date = parseDateString(date);
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
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    /**
     * This method takes a string representing a date of format MM/DD/YYYY Hour/Minute/Second AM/PM and converts it
     * into an object of type LocalDateTime to represent it
     * @param date A string representing a date of format MM/DD/YYYY Hour/Minute/Second AM/PM
     * @return Returns a LocalDateTime object representing the date-time passed to it as a string.
     */
    private LocalDateTime parseDateString(String date) {
        int second = Integer.parseInt(date.substring(17, 19));
        int minute = Integer.parseInt(date.substring(14, 16));
        int hour = Integer.parseInt(date.substring(11, 13));
        int day = Integer.parseInt(date.substring(3, 5));
        int month = Integer.parseInt(date.substring(0, 2));
        int year = Integer.parseInt(date.substring(6, 10));
        if (date.endsWith("PM")){
            hour += 12;
            if(hour == 24){
                hour = 0;
            }
        }

        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);


        return dateTime;
    }

    /**
     * An equality checker for a Crime and an object
     * @param other an object to be compared to
     * @return A boolean value of if the objects have the same variables
     */

    @Override public boolean equals(Object other) {
        boolean equal = false;
        if (other instanceof Crime) {
            equal = (this.arrest == ((Crime) other).getArrest() && this.caseNumber == ((Crime) other).getCaseNumber()
            && this.domestic == ((Crime) other).getDomestic() && this.date.equals(((Crime) other).getDate()) &&
                    this.longitude == ((Crime) other).getLongitude() && this.beat == ((Crime) other).getBeat() &&
                    this.block == ((Crime) other).getBlock() && this.FBI == ((Crime) other).getFBI() &&
                    this.IUCR == ((Crime) other).getIUCR() && this.latitude == ((Crime) other).getLatitude() &&
                    this.locationDescription == ((Crime) other).getLocationDescription() && this.primaryDescription
            == ((Crime) other).getPrimaryDescription() && this.secondaryDescription == ((Crime) other).getSecondaryDescription()
            && this.ward == ((Crime) other).getWard());
        }
        return equal;
    }

    public boolean getArrest() { return this.arrest;}

    public boolean getDomestic() {return this.domestic;}

    public String getCaseNumber() {
        return caseNumber;
    }

    public LocalDateTime getDate() {
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public void setDate(LocalDateTime date) {
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
