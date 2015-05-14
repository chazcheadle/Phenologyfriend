package com.catrock.phenologyfriend;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to hold Weather data with getters and setters.
 */
public class WeatherData implements Parcelable {

    String Temperature;
    String Humidity;

    public WeatherData (String temp, String hum) {
        Temperature=temp;
        Humidity=hum;

    }

    public String getTemp() {
        return Temperature;
    }

    public void setTemp(String temp) {
        Temperature=temp;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void  setHumidity(String hum) {
        Humidity=hum;
    }

    // Parcel part
    public WeatherData(Parcel in){
        String[] data= new String[2];

        in.readStringArray(data);
        this.Temperature= data[0];
        this.Humidity= data[1];
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.Temperature,this.Humidity});
    }

    public static final Parcelable.Creator<WeatherData> CREATOR= new Parcelable.Creator<WeatherData>() {

        @Override
        public WeatherData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new WeatherData(source);  //using parcelable constructor
        }

        @Override
        public WeatherData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new WeatherData[size];
        }
    };

}
