package com.android.zoom.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.zoom.Common.Manager.PreferenceManager;
import com.android.zoom.Common.Manager.ToastManager;
import com.android.zoom.Common.Retrofit.RetrofitBuilder;
import com.android.zoom.Common.Retrofit.RetrofitService;
import com.android.zoom.MainActivity;
import com.android.zoom.R;
import com.android.zoom.User.DTO.UserDTO;
import com.android.zoom.User.DTO.UserData;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText et_id;
    TextInputEditText et_pw;
    Button btn_login;
    InputMethodManager imm;
    Context mCon;
    RetrofitService retrofitService;
    @Deprecated
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCon        = this;
        et_id       = findViewById(R.id.et_id);
        et_pw       = findViewById(R.id.et_pw);
        btn_login   = findViewById(R.id.btn_login);
        et_id.setOnEditorActionListener(actionListener);
        et_pw.setOnEditorActionListener(actionListener);
        btn_login.setOnClickListener(onClickListener);

        if(!PreferenceManager.getString(mCon,"user_name").equals("")) {
            ToastManager.showToastMsg(mCon,"이미 로그인되어있음.");
            Intent intent = new Intent(mCon,MainActivity.class);
            startActivity(intent);
        }
    }

    TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            switch (i) {
                case EditorInfo.IME_ACTION_NEXT :
                    et_pw.requestFocus();
                    break;
                case EditorInfo.IME_ACTION_DONE :
                    loginEvent(mCon);
                    break;
            }
            return false;
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_login :
                    loginEvent(mCon);
                    break;
            }
        }
    };

    public void loginEvent(Context context) {
        // 호출 할 때 마다 getInstance 함수를 호출하여
        // 몸체를 빌드한다.
        progressDialog = new ProgressDialog(mCon);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("로딩중...");
        progressDialog.show();

        String controller = "user";
        String function = "login";
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();

        RetrofitBuilder retrofitBuilder = RetrofitBuilder.getInstance("http://222.100.239.140:8080/");
        retrofitService = retrofitBuilder.getRetrofitService();
        Call<UserData> call = retrofitService.loginCheck(controller,function,id,pw);

        // onResponse : 통신 성공
        // onFailure  : 통신 실패
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.isSuccessful()) {
                    String msg;
                    UserData userData = response.body();
                    ArrayList<UserDTO> userDTO = (ArrayList<UserDTO>) userData.getUserListData();
                    progressDialog.dismiss();

                    // R.string -> int -> getString -> String msg
                    if(userDTO.get(0) == null) {
                        msg = getString(R.string.msg_login_error);
                        ToastManager.showToastMsg(mCon,msg);
                    } else {
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);
                        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String formatDate = sdfNow.format(date);

                        PreferenceManager.setString(mCon,"user_name",userDTO.get(0).getUser_name());
                        PreferenceManager.setString(mCon,"user_id",userDTO.get(0).getUser_id());
                        PreferenceManager.setString(mCon,"log_date",formatDate);

                        Intent intent = new Intent(mCon, MainActivity.class);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("msg", t.getMessage());
            }
        });
    }
}

// 2020-12-23
// Shared arrayData 저장
// 로그아웃, 백버튼 모듈화
// 영화리스트 뽑는거







