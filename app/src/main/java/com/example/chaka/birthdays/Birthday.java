package com.example.chaka.birthdays;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
public class Birthday implements Parcelable {

    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private Boolean closeFriend;
    private String gender;
    private String picture;
    private String pictureOrig;
    private Boolean cardSent;
    private Boolean significantOther;
    private String weight;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Birthday() {
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The closeFriend
     */
    public Boolean getCloseFriend() {
        return closeFriend;
    }

    /**
     *
     * @param closeFriend
     * The close_friend
     */
    public void setCloseFriend(Boolean closeFriend) {
        this.closeFriend = closeFriend;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     * The pictureOrig
     */
    public String getPictureOrig() {
        return pictureOrig;
    }

    /**
     *
     * @param pictureOrig
     * The picture_orig
     */
    public void setPictureOrig(String pictureOrig) {
        this.pictureOrig = pictureOrig;
    }

    /**
     *
     * @return
     * The cardSent
     */
    public Boolean getCardSent() {
        return cardSent;
    }

    /**
     *
     * @param cardSent
     * The card_sent
     */
    public void setCardSent(Boolean cardSent) {
        this.cardSent = cardSent;
    }

    /**
     *
     * @return
     * The significantOther
     */
    public Boolean getSignificantOther() {
        return significantOther;
    }

    /**
     *
     * @param significantOther
     * The significant_other
     */
    public void setSignificantOther(Boolean significantOther) {
        this.significantOther = significantOther;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(String.valueOf(closeFriend));
        dest.writeString(gender);
        dest.writeString(picture);
        dest.writeString(pictureOrig);
        dest.writeString(String.valueOf(cardSent));
        dest.writeString(String.valueOf(significantOther));
        dest.writeString(weight);



    }

    /*
     * Retrieving Employee data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     */
    public Birthday(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.closeFriend = Boolean.parseBoolean(in.readString());
        this.gender = in.readString();
        this.picture = in.readString();
        this.pictureOrig = in.readString();
        this.cardSent = Boolean.parseBoolean(in.readString());
        this.significantOther = Boolean.parseBoolean(in.readString());
        this.weight = in.readString();
        //this.image = in.readString();
    }

    public static final Parcelable.Creator<Birthday> CREATOR = new Parcelable.Creator<Birthday>() {

        @Override
        public Birthday createFromParcel(Parcel source) {
            return new Birthday(source);
        }

        @Override
        public Birthday[] newArray(int size) {
            return new Birthday[size];
        }
    };

}
