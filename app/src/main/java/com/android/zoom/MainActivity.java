package com.android.zoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.zoom.Common.Manager.PreferenceManager;
import com.android.zoom.Common.Manager.ToastManager;
import com.android.zoom.Common.Retrofit.RetrofitBuilder;
import com.android.zoom.Common.Retrofit.RetrofitService;
import com.android.zoom.Movie.DTO.BoxOfficeResult;
import com.android.zoom.Movie.DTO.MovieResult;
import com.android.zoom.Movie.MovieAdapter;
import com.android.zoom.User.LoginActivity;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    View drawerView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RetrofitService retrofitInterface;
    public Context mCon;
    ProgressDialog progressDialog;
    TextView login_user;
    TextView log_date;
    TextView btn_logout;
    Button getMovieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String msg_login_user = getString(R.string.hello_msg_name);
        String msg_log_date = getString(R.string.login_date);
        mCon = this;

        login_user = findViewById(R.id.login_user);
        log_date = findViewById(R.id.log_date);
        getMovieData = findViewById(R.id.getMovieData);
        btn_logout = findViewById(R.id.btn_logout);

        if(PreferenceManager.getString(mCon,"user_name").equals("")) {
            ToastManager.showToastMsg(mCon,"로그인이 되어있지않음!!");
            Intent intent = new Intent(mCon,LoginActivity.class);
            startActivity(intent);
        } else {
            login_user.setText(String.format(msg_login_user, PreferenceManager.getString(mCon, "user_name")));
            log_date.setText(String.format(msg_log_date, PreferenceManager.getString(mCon, "log_date")));
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawerView);
        drawerLayout.setDrawerListener(listener);

        getMovieData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(mCon);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("알림");
                progressDialog.setMessage("데이터를 불러오는 중...");
                progressDialog.show();

                recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mCon, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

                // 인스턴스 생성
                RetrofitBuilder retrofitClient = RetrofitBuilder.getInstance("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/");
                retrofitInterface = retrofitClient.getRetrofitService();
                Call<MovieResult> call = retrofitInterface.getBoxOffice(getString(R.string.api_key), "20201222");

                call.enqueue(new Callback<MovieResult>() {
                    @Override
                    public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                        if(response.isSuccessful()) {
                            Log.d("msg","연결 성공");
                            MovieResult movieResult = response.body();
                            BoxOfficeResult boxOfficeResult = movieResult.getBoxOfficeResult();
                            mAdapter = new MovieAdapter(boxOfficeResult.getDailyBoxOfficeList());
                            recyclerView.setAdapter(mAdapter);
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResult> call, Throwable t) {
                        Log.d("retrofit", t.getMessage());
                    }
                });
            }
        });
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };
}