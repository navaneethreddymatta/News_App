package com.group20.inclass06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lokeshkanagala on 9/26/16.
 */
public class GetImage extends AsyncTask<String,Void,Bitmap> {
    Bitmap bitmap;
    imageInterface activity;
    String viewId;

    public GetImage(imageInterface activity) {
        this.activity=activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url= null;
        try {
            url = new URL(params[0]);
            viewId=params[1];
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            bitmap= BitmapFactory.decodeStream(con.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    interface imageInterface{
        void setImage(Bitmap bitmap,String viewId );
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.setImage(bitmap,viewId);
    }
}
