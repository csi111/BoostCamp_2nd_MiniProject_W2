package com.example.choijinjoo.miniproject_w2_jinjoo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by choijinjoo on 2017. 7. 13..
 */

@DatabaseTable
public class Restaurant implements Parcelable {
    @DatabaseField(generatedId = true)
    Integer id;
    @DatabaseField
    String name;
    @DatabaseField
    String image;
    @DatabaseField
    String description;
    @DatabaseField
    Double distance;
    @DatabaseField
    Double star;
    @DatabaseField
    Date createdAt;
    @DatabaseField
    Boolean isChecked;

    public Restaurant() {}

    public Restaurant(String name, String image, String description, Double distance, Double star, Date createdAt, Boolean isChecked) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.distance = distance;
        this.star = star;
        this.createdAt = createdAt;
        this.isChecked = isChecked;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isChecked() { return isChecked; }

    public void setChecked(Boolean checked) { isChecked = checked; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeString(this.description);
        dest.writeValue(this.distance);
        dest.writeValue(this.star);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeValue(this.isChecked);
    }

    protected Restaurant(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.image = in.readString();
        this.description = in.readString();
        this.distance = (Double) in.readValue(Double.class.getClassLoader());
        this.star = (Double) in.readValue(Double.class.getClassLoader());
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.isChecked = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}
