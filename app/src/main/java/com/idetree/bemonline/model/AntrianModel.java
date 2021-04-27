package com.idetree.bemonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AntrianModel {
    @SerializedName("no_antrian")
    @Expose
    private Integer noAntrian;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_stnk")
    @Expose
    private String noStnk;
    @SerializedName("status_raw")
    @Expose
    private Integer statusRaw;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("antrian_by_me")
    @Expose
    private Boolean antrianByMe;

    public Integer getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(Integer noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoStnk() {
        return noStnk;
    }

    public void setNoStnk(String noStnk) {
        this.noStnk = noStnk;
    }

    public Integer getStatusRaw() {
        return statusRaw;
    }

    public void setStatusRaw(Integer statusRaw) {
        this.statusRaw = statusRaw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAntrianByMe() {
        return antrianByMe;
    }

    public void setAntrianByMe(Boolean antrianByMe) {
        this.antrianByMe = antrianByMe;
    }
}
