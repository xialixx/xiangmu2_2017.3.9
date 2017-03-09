package com.example.administrator.myapplication_class_227.user;

/**
 * Created by Administrator on 2017/3/8.
 */
public class shoucangUser {
    private int ischeak;
    private String C2;
    private String C3;
    private String C1;
    private String C4T1;
    private String C4R;
    private String C4T2;
    private String C52Link;
    @Override
    public String toString() {
        return "shoucangUser{" +
                "ischeak='" + ischeak + '\'' +
                ", C2='" + C2 + '\'' +
                ", C3='" + C3 + '\'' +
                ", C1='" + C1 + '\'' +
                ", C4T1='" + C4T1 + '\'' +
                ", C4R='" + C4R + '\'' +
                ", C4T2='" + C4T2 + '\'' +
                '}';
    }

    public int getIscheak() {
        return ischeak;
    }

    public void setIscheak(int ischeak) {
        this.ischeak = ischeak;
    }

    public shoucangUser(int ischeak,String c2, String c3, String c1, String c4T1, String c4R, String c4T2, String c52Link) {
        this.ischeak = ischeak;
        C2 = c2;
        C3 = c3;
        C1 = c1;
        C4T1 = c4T1;
        C4R = c4R;
        C4T2 = c4T2;
        C52Link=c52Link;
    }

    public String getC52Link() {
        return C52Link;
    }

    public void setC52Link(String c52Link) {
        C52Link = c52Link;
    }

    public shoucangUser() {
    }


    public String getC2() {
        return C2;
    }

    public void setC2(String c2) {
        C2 = c2;
    }

    public String getC3() {
        return C3;
    }

    public void setC3(String c3) {
        C3 = c3;
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String c1) {
        C1 = c1;
    }

    public String getC4T1() {
        return C4T1;
    }

    public void setC4T1(String c4T1) {
        C4T1 = c4T1;
    }

    public String getC4R() {
        return C4R;
    }

    public void setC4R(String c4R) {
        C4R = c4R;
    }

    public String getC4T2() {
        return C4T2;
    }

    public void setC4T2(String c4T2) {
        C4T2 = c4T2;
    }
}
