package com.android.zoom.User.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class UserData {
    /**
     * @SerializedName : JSON 으로 serialize 될 때 매칭되는 이름을 명시함.
     * @Expose : object 중 해당 값이 null 일 경우, 필드 생성 자동 생략
     * */

    @SerializedName("product")
    @Expose
    private String pd;

    @SerializedName("code")
    @Expose
    private String cd;

    @Expose
    private String msg;

    @SerializedName("result")
    @Expose
    private List<UserDTO> userListData = null;
}
