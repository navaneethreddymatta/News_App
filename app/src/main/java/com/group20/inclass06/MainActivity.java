package com.group20.inclass06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetData.DataRetreiver,GetImage.imageInterface {

    ArrayList<NewsItem> newsItems=new ArrayList<NewsItem>();
    ProgressDialog pd;
    NewsItem ni;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setTitle(R.string.loading_text);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        new GetData(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");

    }

    @Override
    public void setData(ArrayList<NewsItem> newsList) {
        newsItems=newsList;
        pd.dismiss();
        for(int i=0;i<newsList.size();i++){
            ni=newsList.get(i);
            LinearLayout ll=new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setId(1000 + i);
            ImageView iv =new ImageView(this);
            iv.setId(i);
            iv.setMaxHeight(80);
            iv.setMinimumHeight(80);
            iv.setMaxWidth(80);
            iv.setMinimumWidth(80);
            index=""+i;
            new GetImage(this).execute(ni.getThumbnail(),index);
            TextView tv=new TextView(this);
            tv.setText(ni.getTitle());
            ll.addView(iv);
            ll.addView(tv);
            LinearLayout pll=(LinearLayout) findViewById(R.id.parentLayout);
            pll.addView(ll);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = "" + (v.getId() - 1000);
                    Intent i = new Intent(MainActivity.this, NewsData.class);
                    i.putExtra("NEWSFEED", newsItems);
                    i.putExtra("ID", temp);
                    startActivity(i);
                }
            });


        }
        System.out.println(newsItems.toString());
    }

    @Override
    public void setImage(Bitmap bitmap, String viewId) {
        Log.d("image","imageHere");
        int ivId=Integer.parseInt(viewId);
        ImageView iview=(ImageView)findViewById(ivId);
        iview.setImageBitmap(bitmap);

    }
}
