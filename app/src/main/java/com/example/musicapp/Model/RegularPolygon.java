package com.example.musicapp.Model;

public class RegularPolygon {
    private String name;
    private int egdeAmount;
    private int egdeLength;

    public RegularPolygon(String name, int egdeAmount, int egdeLength) {
        this.name = name;
        this.egdeAmount = egdeAmount;
        this.egdeLength = egdeLength;
    }

    public RegularPolygon() {
        this.name = name;
        this.egdeAmount = egdeAmount;
        this.egdeLength = egdeLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEgdeAmount() {
        return egdeAmount;
    }

    public void setEgdeAmount(int egdeAmount) {
        this.egdeAmount = egdeAmount;
    }

    public int getEgdeLength() {
        return egdeLength;
    }

    public void setEgdeLength(int egdeLength) {
        this.egdeLength = egdeLength;
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
                "name='" + name + '\'' +
                ", egdeAmount=" + egdeAmount +
                ", egdeLength=" + egdeLength +
                '}';
    }
}
