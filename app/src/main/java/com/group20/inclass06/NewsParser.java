package com.group20.inclass06;

import android.util.Log;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by lokeshkanagala on 9/26/16.
 */
public class NewsParser{

    static ArrayList<NewsItem> ParseNews (InputStream in) throws XmlPullParserException, IOException {
        ArrayList<NewsItem> newsList;
        NewsItem newsitem = null;
        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(in,"UTF-8");
        newsList= new ArrayList<NewsItem>();
        int event=parser.getEventType();
        Log.d("demo1"," @getdata4");
        while(event!=XmlPullParser.END_DOCUMENT)
        {
            Log.d("demo1"," @getdata5");
            switch(event)
            {
                case XmlPullParser.START_TAG:
                    if(parser.getName().equals("item"))
                    {
                        newsitem=new NewsItem();
                    }else  if(parser.getName().equals("title")){
                        if(newsitem!=null) {
                            newsitem.setTitle(parser.nextText().trim());
                        }

                    }else  if(parser.getName().equals("description")){
                        if(newsitem!=null) {
                            newsitem.setDescription(parser.nextText().trim());
                        }

                    }else  if(parser.getName().equals("link")){
                        if(newsitem!=null) {
                            newsitem.setLink(parser.nextText().trim());
                        }

                    }else  if(parser.getName().equals("pubDate")){
                        if(newsitem!=null) {
                            newsitem.setPubdate(parser.nextText().trim());
                        }

                    }else  if(parser.getName().equals("media:content")){
                        if(newsitem.getThumbnail()==null||newsitem.getImage()==null)
                        {
                            if(newsitem.getImage()==null){
                                newsitem.setImage(parser.getAttributeValue(null,"url").trim());

                            }else{
                                newsitem.setThumbnail(parser.getAttributeValue(null,"url").trim());
                            }
                        }


                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("item"))
                    {
                        newsList.add(newsitem);
                    }
                    break;
                default:
                    break;
            }

             event=parser.next();
        }


       return newsList;


    }

}
