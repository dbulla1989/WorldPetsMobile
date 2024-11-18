package com.example.worldpetsmobile.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Pet implements Parcelable {

    @SerializedName("personId")
    private int personId;
    @SerializedName("name")
    private String name;
    @SerializedName("race")
    private String breed;
    @SerializedName("age")
    private int age;
    @SerializedName("encodedImage")
    private String encodedImage;

    public Pet() {}

    protected Pet(Parcel in) {
        personId = in.readInt();
        name = in.readString();
        breed = in.readString();
        age = in.readInt();
        encodedImage = in.readString();
    }

    public static final Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(personId);
        dest.writeString(name);
        dest.writeString(breed);
        dest.writeInt(age);
        dest.writeString(encodedImage);
    }
}

