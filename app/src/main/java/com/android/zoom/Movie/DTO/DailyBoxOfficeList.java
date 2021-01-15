package com.android.zoom.Movie.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DailyBoxOfficeList {
    @SerializedName("rnum")
    @Expose
    private String rnum;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("rankInten")
    @Expose
    private String rankInten;
    @SerializedName("rankOldAndNew")
    @Expose
    private String rankOldAndNew;
    @SerializedName("movieCd")
    @Expose
    private String movieCd;
    @SerializedName("movieNm")
    @Expose
    private String movieNm;
    @SerializedName("openDt")
    @Expose
    private String openDt;
    @SerializedName("salesAmt")
    @Expose
    private String salesAmt;
    @SerializedName("salesShare")
    @Expose
    private String salesShare;
    @SerializedName("salesInten")
    @Expose
    private String salesInten;
    @SerializedName("salesChange")
    @Expose
    private String salesChange;
    @SerializedName("salesAcc")
    @Expose
    private String salesAcc;
    @SerializedName("audiCnt")
    @Expose
    private String audiCnt;
    @SerializedName("audiInten")
    @Expose
    private String audiInten;
    @SerializedName("audiChange")
    @Expose
    private String audiChange;
    @SerializedName("audiAcc")
    @Expose
    private String audiAcc;
    @SerializedName("scrnCnt")
    @Expose
    private String scrnCnt;
    @SerializedName("showCnt")
    @Expose
    private String showCnt;

}
