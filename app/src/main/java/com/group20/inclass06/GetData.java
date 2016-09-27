package com.group20.inclass06;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lokeshkanagala on 9/26/16.
 */
public class GetData extends AsyncTask<String,Void,ArrayList<NewsItem>> {
    DataRetreiver activity;

    @Override
    protected void onPostExecute(ArrayList<NewsItem> newsItems) {
        super.onPostExecute(newsItems);
        activity.setData(newsItems);
    }

    public GetData(DataRetreiver activity) {
        this.activity=activity;

    }

    @Override
    protected ArrayList<NewsItem> doInBackground(String... params) {
            Log.d("demo1"," @getdata1");
        try{
            URL url=new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode=con.getResponseCode();
            Log.d("demo2"," @getdata2");
            if(statuscode==HttpURLConnection.HTTP_OK)
            {
                Log.d("demo1"," @getdata3");
                InputStream inputStream= con.getInputStream();
                return NewsParser.ParseNews(inputStream);
            }






        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface DataRetreiver{
        void setData(ArrayList<NewsItem> newsList);
    }
}
