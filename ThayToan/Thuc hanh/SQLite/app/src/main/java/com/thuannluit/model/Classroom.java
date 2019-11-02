package com.thuannluit.model;

import androidx.annotation.NonNull;

public class Classroom {
    private String malop, tenlop;
    private int siso;

    public Classroom(String malop, String tenlop, int siso) {
        this.malop = malop;
        this.tenlop = tenlop;
        this.siso = siso;
    }

    public Classroom() {
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public int getSiso() {
        return siso;
    }

    public void setSiso(int siso) {
        this.siso = siso;
    }

    @NonNull
    @Override
    public String toString() {
        return malop.toString() +" - "+tenlop.toString()+" - "+String.valueOf(siso);
    }
}
