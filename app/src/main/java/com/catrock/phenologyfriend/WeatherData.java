package com.catrock.phenologyfriend;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to hold Weather data with getters and setters.
 */
public class WeatherData implements Parcelable {

    String Weather;
    String Temperature;
    String Humidity;

    public WeatherData (String weather, String temperature, String humidity) {
        Weather=weather;
        Temperature=temperature;
        Humidity=humidity;

    }

    public String getWeather() {
        return Weather;
    }
    public void setWeather(String weather) {
        Weather=weather;
    }

    public String getTemperature() {
        return Temperature;
    }
    public void setTemperature(String temperature) {
        Humidity=temperature;
    }

    public String getHumidity() {
        return Humidity;
    }
    public void setHumidity(String humidity) {
        Humidity=humidity;
    }


/*            "weather": "Partly Cloudy",
            "temperature_string": "66.3 F (19.1 C)",
            "temp_f": 66.3,
            "temp_c": 19.1,
            "relative_humidity": "65%",
            "wind_string": "From the NNW at 22.0 MPH Gusting to 28.0 MPH",
            "wind_dir": "NNW",
            "wind_degrees": 346,
            "wind_mph": 22.0,
            "wind_gust_mph": "28.0",
            "wind_kph": 35.4,
            "wind_gust_kph": "45.1",
            "pressure_mb": "1013",
            "pressure_in": "29.93",
            "pressure_trend": "+",
            "dewpoint_string": "54 F (12 C)",
            "dewpoint_f": 54,
            "dewpoint_c": 12,
    */





    // Parcel part
    public WeatherData(Parcel in){
        String[] data= new String[3];

        in.readStringArray(data);
        this.Weather= data[0];
        this.Temperature= data[1];
        this.Humidity= data[2];
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.Weather,this.Temperature,this.Humidity});
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
