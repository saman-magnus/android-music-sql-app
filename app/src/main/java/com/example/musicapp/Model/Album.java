package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("id")
    @Expose
    private String idAlbum;
    @SerializedName("name")
    @Expose
    private String tenAlbum;
    @SerializedName("description")
    @Expose
    private String tenNS;
    @SerializedName("art")
    @Expose
    private String hinhAlbum;

    public Album(String idAlbum, String tenAlbum, String tenNS, String hinhAlbum) {
        this.idAlbum = idAlbum;
        this.tenAlbum = tenAlbum;
        this.tenNS = tenNS;
        this.hinhAlbum = hinhAlbum;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

}
