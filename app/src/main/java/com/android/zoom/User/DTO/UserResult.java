package com.android.zoom.User.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserResult {
    @SerializedName("")
    @Expose
    private UserData userData;
}
