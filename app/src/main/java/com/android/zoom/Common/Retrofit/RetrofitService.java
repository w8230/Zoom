package com.android.zoom.Common.Retrofit;

import com.android.zoom.Movie.DTO.MovieResult;
import com.android.zoom.User.DTO.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("{controller}/{function}")
    Call<UserData> getUserData(@Path("controller") String uri1, @Path("function") String uri2);

    @GET("{controller}/{function}")
    Call<UserData> loginCheck
            (
                @Path("controller") String uri1,
                @Path("function") String uri2,
                @Query("user_id") String user_id,
                @Query("user_pwd") String user_pwd
            );

    @GET("searchDailyBoxOfficeList.json")
    Call<MovieResult> getBoxOffice (@Query("key") String key, @Query("targetDt") String targetDt);
}
