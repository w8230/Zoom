package com.android.zoom.Movie.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class MovieResult {
    /**
     * @SerialzedName : JSON으로 serialize 될 때 매칭되는 이름을 명시하는 목적으로 사용
     * @Expose : object 중 해당 값이 null일 경우, json으로 만들 필드를 자동 생략
     * */
    @SerializedName("boxOfficeResult")
    @Expose
    private BoxOfficeResult boxOfficeResult;
}
