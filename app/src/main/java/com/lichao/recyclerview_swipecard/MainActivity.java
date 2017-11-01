package com.lichao.recyclerview_swipecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import com.lichao.recyclerview_swipecard.adaper.UniversalAdapter;
import com.lichao.recyclerview_swipecard.adaper.ViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UniversalAdapter<SwipeCardBean> adapter;
    private List<SwipeCardBean> mData;
    SwipeCardLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new SwipeCardLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        mData = SwipeCardBean.initDatas();
        adapter = new UniversalAdapter<SwipeCardBean>(this, mData, R.layout.item_swipe_card) {
            @Override
            public void convert(ViewHolder var1, SwipeCardBean var2) {
                var1.setText(R.id.tvName, var2.getName());
                var1.setText(R.id.tvPrecent, var2.getPosition() + "/" + mData.size());
                Picasso.with(MainActivity.this)
                        .load(var2.getUrl())
                        .into((ImageView) var1.getView(R.id.iv));
            }
        };
        recyclerView.setAdapter(adapter);
        CardConfig.initConfig(this);

        SwipeCardCallback callback = new SwipeCardCallback(0, 0, adapter, mData, recyclerView);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

}
