package com.example.furkan.homework2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;


public class Tab2Announcement extends Fragment {

    public ListView announce;
    public ArrayList<String> announcement;
    public ArrayList<String> links;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab2_announcement, container, false);
        announce =(ListView) view.findViewById(R.id.announceList);

        new DescriptionAn().execute();

        return view;
    }

    // Description AsyncTask
    private class DescriptionAn extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                announcement= new ArrayList<String>();
                links= new ArrayList<String>();
                Document document = Jsoup.connect("http://www.ybu.edu.tr/muhendislik/bilgisayar/").get();
                Element masthead = document.select("div.caContent").first();
                Iterator<Element> ite = masthead.select("div.cncItem").iterator();
                //ite.next();

                while(ite.hasNext()){
                    Element div =ite.next();
                    announcement.add(div.text());
                    System.out.println("Value 1: " + div.select("a").attr("href"));
                    links.add(div.select("a").attr("href"));



                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            //  TextView txtdesc = (TextView) findViewById(R.id.desctxt);
            ArrayAdapter a=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,announcement);
            announce.setAdapter(a);
            announce.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {


                    String[] a = new String[10000];
                    for(int i=0;i<links.size();i++){
                        a[i]="http://www.ybu.edu.tr/muhendislik/bilgisayar/"+links.get(i).toString();
                    }

                    Uri uri = Uri.parse(a[position]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);






                }
            });

           // foodList.setText(a);
            //mProgressDialog.dismiss();
        }
    }





}
