package com.catrock.phenologyfriend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to hold Specimen data with getters and setters.
 */
public class SpecimenData implements Parcelable {

    // Private variables;
    private String imapId;
    private int invasive;
    private String scientificName;
    private String commonName;
    private String description;

    // Empty constructor.
    public SpecimenData() {

    }
    // Class Constructor.
    public SpecimenData (String imapId, int invasive, String scientificName, String commonName, String description) {
        this.imapId=imapId;
        this.invasive=invasive;
        this.scientificName=scientificName;
        this.commonName=commonName;
        this.description=description;
    }

    // Getters and Setters.
    public String getImapId() {
        return imapId;
    }
    public void setImapId(String imapId) {
        this.imapId=imapId;
    }

    public int getInvasive() {
        return invasive;
    }
    public void setInvasive(int invasive) {
        this.invasive=invasive;
    }

    public String getScientificName() {
        return scientificName;
    }
    public void setScientificName(String scientificName) {
        this.scientificName=scientificName;
    }

    public String getCommonName() {
        return commonName;
    }
    public void setCommonName(String commonName) {
        this.commonName=commonName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description=description;
    }

    // Parcel part
    private SpecimenData(Parcel in){
        // Read back parcel data in the order it was entered.
        this.imapId= in.readString();
        this.invasive= in.readInt();
        this.scientificName= in.readString();
        this.commonName= in.readString();
        this.description= in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write values into parcel in the order they were entered.

        dest.writeString(imapId);
        dest.writeInt(invasive);
        dest.writeString(scientificName);
        dest.writeString(commonName);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<SpecimenData> CREATOR= new Parcelable.Creator<SpecimenData>() {

        @Override
        public SpecimenData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new SpecimenData(source);  //using parcelable constructor
        }

        @Override
        public SpecimenData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new SpecimenData[size];
        }
    };

}
