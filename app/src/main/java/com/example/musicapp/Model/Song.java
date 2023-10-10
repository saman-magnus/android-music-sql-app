package com.example.musicapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {

    @SerializedName("userid")
    @Expose
    private String idbh;
    @SerializedName("title")
    @Expose
    private String tenBH;
    @SerializedName("description")
    @Expose
    private String tenNS;
    @SerializedName("art")
    @Expose
    private String hinhanhBH;
    @SerializedName("track")
    @Expose
    private String linkBH;

    public Song(String idbh, String tenBH, String tenNS, String hinhanhBH, String linkBH) {
        this.idbh = idbh;
        this.tenBH = tenBH;
        this.tenNS = tenNS;
        this.hinhanhBH = hinhanhBH;
        this.linkBH = linkBH;
    }

    protected Song(Parcel in) {
        idbh = in.readString();
        tenBH = in.readString();
        tenNS = in.readString();
        hinhanhBH = in.readString();
        linkBH = in.readString();
    }


    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getIdbh() {
        return idbh;
    }

    public void setIdbh(String idbh) {
        this.idbh = idbh;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public String getHinhanhBH() {
        return hinhanhBH;
    }

    public void setHinhanhBH(String hinhanhBH) {
        this.hinhanhBH = hinhanhBH;
    }

    public String getLinkBH() {
        return linkBH;
    }

    public void setLinkBH(String linkBH) {this.linkBH = linkBH;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idbh);
        dest.writeString(tenBH);
        dest.writeString(tenNS);
        dest.writeString(hinhanhBH);
        dest.writeString(linkBH);
    }
}