package com.android.zoom.Movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.zoom.Movie.DTO.DailyBoxOfficeList;
import com.android.zoom.R;
import java.util.List;

/**
 * 리사이릌 뷰 어댑터 기본 구조
 * */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    /**
        기본 형태
        1. onCreateViewHolder : 뷰홀더를 생성(레이아웃 생성)
        2. onBindViewHolder : 뷰홀더가 재활용될 때 실행되는 메서드
        3. getItemCount : 아이템 개수를 조회
     * */
    private List<DailyBoxOfficeList> items;

    // this -> 멤버변수 = 인수 변수
    public MovieAdapter(List<DailyBoxOfficeList> items){
        this.items = items;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.movie_item_list , parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyBoxOfficeList item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * 리사이클러뷰 어댑터는 개별 데이터에 대응하는 뷰홀더 클래스를 사용
     * 뷰홀더도 기본 기능이 이미 만들어져 있는 ViewHolder 클래스를 상속받아서 만듬
     * ViewHolder 는 현재 화면에 보이는 아이템 레이아웃 개수만큼 생성되고
     * 새롭게 그려 저야 할 아이템 레이아웃이 있다면(스크롤 동작) 가장 위의 ViewHolder를 재사용해서 데이터만 바꾼다.
     * 즉, 몇 천 개의 데이터가 있다고 했을 때 몇 천 개의 아이템 레이아웃을 생성하면 자원 낭비가 있으므로 ViewHolder 재사용성은 이를 방지하여 앱의 효율을 향상시킴
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rank, movieNm, openDt;

        public ViewHolder(View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            movieNm = itemView.findViewById(R.id.movieNM);
            openDt = itemView.findViewById(R.id.openDt);
        }

        public void setItem(DailyBoxOfficeList item){
            rank.setText(item.getRank());
            movieNm.setText(item.getMovieNm());
            openDt.setText(item.getOpenDt());
        }
    }

}
