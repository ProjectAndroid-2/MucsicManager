package com.example.doanltdd.Model;

public class NhacSy {
    String MaNS;
    String TenNS;
    private byte[] imgSignature;

    public byte[] getImgSignature() {
        return imgSignature;
    }

    public void setImgSignature(byte[] imgSignature) {
        this.imgSignature = imgSignature;
    }

    public String getMaNS() {
        return MaNS;
    }

    public void setMaNS(String maNS) {
        MaNS = maNS;
    }

    public String getTenNS() {
        return TenNS;
    }

    public void setTenNS(String tenNS) {
        TenNS = tenNS;
    }


}
