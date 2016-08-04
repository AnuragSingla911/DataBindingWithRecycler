package flights.android.com.flightslist.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jade on 3/8/16.
 */

public class Flight implements Parcelable{

    private String originalCode;

    private String destinationCode;

    private String takeOffTime;

    private String landingTime;

    private String price;

    private String airlineCode;

    private String classType;

    private String duration;

    public Flight(){}

    protected Flight(Parcel in) {
        originalCode = in.readString();
        destinationCode = in.readString();
        takeOffTime = in.readString();
        landingTime = in.readString();
        price = in.readString();
        airlineCode = in.readString();
        classType = in.readString();
        duration = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalCode);
        dest.writeString(destinationCode);
        dest.writeString(takeOffTime);
        dest.writeString(landingTime);
        dest.writeString(price);
        dest.writeString(airlineCode);
        dest.writeString(classType);
        dest.writeString(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    public String getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(String takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
