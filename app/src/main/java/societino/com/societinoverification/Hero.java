package societino.com.societinoverification;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Trena on 07-01-2018.
 */

public class Hero implements Parcelable {
    String socName;
    String socAdd;
    String socCity;
    String socEmail;
    String socPin;
    String socSecret;
    String socState;
    String socUsername;

    public String getSocUsername() {
        return socUsername;
    }

    public void setSocUsername(String socUsername) {
        this.socUsername = socUsername;
    }

    String chName, chEmail, chGen, chUrl, chNum;


    protected Hero(Parcel in) {
        socName = in.readString();
        socAdd = in.readString();
        socCity = in.readString();
        socEmail = in.readString();
        socPin = in.readString();
        socUsername=in.readString();
        socSecret = in.readString();
        socState = in.readString();
        chName = in.readString();
        chEmail = in.readString();
        chGen = in.readString();
        chUrl = in.readString();
        chNum = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public Hero() {

    }

    public String getSocName() {
        return socName;
    }

    public void setSocName(String socName) {
        this.socName = socName;
    }

    public String getSocAdd() {
        return socAdd;
    }

    public void setSocAdd(String socAdd) {
        this.socAdd = socAdd;
    }

    public String getSocCity() {
        return socCity;
    }

    public void setSocCity(String socCity) {
        this.socCity = socCity;
    }

    public String getSocEmail() {
        return socEmail;
    }

    public void setSocEmail(String socEmail) {
        this.socEmail = socEmail;
    }

    public String getSocPin() {
        return socPin;
    }

    public void setSocPin(String socPin) {
        this.socPin = socPin;
    }

    public String getSocSecret() {
        return socSecret;
    }

    public void setSocSecret(String socSecret) {
        this.socSecret = socSecret;
    }

    public String getSocState() {
        return socState;
    }

    public void setSocState(String socState) {
        this.socState = socState;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChEmail() {
        return chEmail;
    }

    public void setChEmail(String chEmail) {
        this.chEmail = chEmail;
    }

    public String getChGen() {
        return chGen;
    }

    public void setChGen(String chGen) {
        this.chGen = chGen;
    }

    public String getChUrl() {
        return chUrl;
    }

    public void setChUrl(String chUrl) {
        this.chUrl = chUrl;
    }

    public String getChNum() {
        return chNum;
    }

    public void setChNum(String chNum) {
        this.chNum = chNum;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(socName);
        parcel.writeString(socAdd);
        parcel.writeString(socCity);
        parcel.writeString(socEmail);
        parcel.writeString(socPin);
        parcel.writeString(socSecret);
        parcel.writeString(socState);
        parcel.writeString(chName);
        parcel.writeString(socUsername);
        parcel.writeString(chEmail);
        parcel.writeString(chGen);
        parcel.writeString(chUrl);
        parcel.writeString(chNum);
    }
}
