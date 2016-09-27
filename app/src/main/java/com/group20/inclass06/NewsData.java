package com.group20.inclass06;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsData extends AppCompatActivity implements GetImage.imageInterface{
    ArrayList<NewsItem> news;
    NewsItem item;
    TextView title, date, story;
    ImageView image;
    String ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_data);
        title = (TextView) findViewById(R.id.title_tv);
        date = (TextView) findViewById(R.id.pubdate_tv);
        story = (TextView) findViewById(R.id.story_tv);
        image = (ImageView) findViewById(R.id.imageView);
        if (getIntent().getExtras() != null) {
            news = (ArrayList<NewsItem>) getIntent().getExtras().get("NEWSFEED");
            String temp = (String) getIntent().getExtras().get("ID");
            int num = Integer.parseInt(temp);
            item = news.get(num);
            title.setText(item.getTitle());
            SimpleDateFormat fromUser = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
            SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM a");
            try {
                ref = myFormat.format(fromUser.parse(item.getPubdate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            date.setText(ref);
            story.setText(item.getDescription());
            String id = "" + image.getId();
            new GetImage(this).execute(item.getImage(), id);
        }
    }


    @Override
    public void setImage(Bitmap bitmap, String viewId) {
        image.setImageBitmap(bitmap);
    }
}
