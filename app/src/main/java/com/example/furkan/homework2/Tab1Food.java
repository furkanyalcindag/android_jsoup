package com.example.furkan.homework2;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Tab1Food extends Fragment {
public TextView foodList;
    String url = "http://ybu.edu.tr/sks";
    ArrayList<String> arraylist;
    public ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab1_food, container, false);
        foodList=(TextView) view.findViewById(R.id.textViewFood);
        new Description().execute();
        return view;
    }


    // Description AsyncTask
    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            arraylist = new ArrayList<String>();
            try {



                URL url = new URL("http://ybu.edu.tr/sks");
                Document doc = Jsoup.parse(url, 3000);
                Element table = doc.select("table").first();

                Iterator<Element> ite = table.select("td").iterator();

                ite.next();
                while(ite.hasNext()){
                    //System.out.println("Value 1: " + ite.next().text());
                    arraylist.add(ite.next().text());


                }






                // first one is image, skip it
                // Connect to the Website URL

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
          //  TextView txtdesc = (TextView) findViewById(R.id.desctxt);
            String a="";
            for(int i=0;i<arraylist.size();i++){
                a=a+"\n"+arraylist.get(i).toString();
            }
           foodList.setText(a);
            //mProgressDialog.dismiss();
        }
    }


}
