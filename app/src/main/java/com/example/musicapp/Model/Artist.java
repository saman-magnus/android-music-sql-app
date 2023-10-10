package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("idnghesi")
    @Expose
    private String idnghesi;
    @SerializedName("tenNS")
    @Expose
    private String tenNS;
    @SerializedName("hinhanhNS")
    @Expose
    private String hinhanhNS;

    public Artist(String idnghesi, String tenNS, String hinhanhNS) {
        this.idnghesi = idnghesi;
        this.tenNS = tenNS;
        this.hinhanhNS = hinhanhNS;
    }

    public String getIdnghesi() {
        return idnghesi;
    }

    public void setIdnghesi(String idnghesi) {
        this.idnghesi = idnghesi;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public String getHinhanhNS() {
        return hinhanhNS;
    }

    public void setHinhanhNS(String hinhanhNS) {
        this.hinhanhNS = hinhanhNS;
    }
}
