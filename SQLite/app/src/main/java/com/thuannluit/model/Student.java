package com.thuannluit.model;

import androidx.annotation.NonNull;

public class Student {
    private String masv, tensv, malop;

    public Student(String masv, String tensv, String malop) {
        this.masv = masv;
        this.tensv = tensv;
        this.malop = malop;
    }

    public Student(String tensv, String malop) {
        this.tensv = tensv;
        this.malop = malop;
    }

    public Student() {
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ma lop: " + malop.toString() + "\n"
                + "MSSV: " + masv.toString() + "\n"
                + "Ten sinh vien: " + tensv.toString();
    }
}
