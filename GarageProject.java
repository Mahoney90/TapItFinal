package com.mahoneyapps.tapitfinal;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GarageProject extends Fragment {

    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beer_list_view, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);

        if (container != null) {
            container.removeAllViews();
        }

        new otherTitle(getActivity()).execute();

        return view;
    }


    private class otherTitle extends AsyncTask<Void, Void, ArrayList<String>> {
        Context mContext;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.beer_list_view, null);


        String url = "http://garageproject.co.nz/pages/taproom";

        public otherTitle(Context context) {
            mContext = context;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arr_beerName = new ArrayList<>();

            String beerName = "";

            try {
                Document doc = Jsoup.connect(url).get();
                Elements element = doc.select("a[class=link light]");
                for (Element item : element){
                    beerName = item.text();

                    arr_beerName.add(beerName);
                }

            } catch(IOException e){
                e.printStackTrace();
            }

            return arr_beerName;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
//                TextView beerOne = (TextView) findViewById(R.id.one);
//                for (String beer_addition : result){
//                    beerOne.append(beer_addition + "\n");
//                }

//

            ArrayAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.pub_item, R.id.pub_text_view, result);
            SwingLeftInAnimationAdapter swingAdapter = new SwingLeftInAnimationAdapter(adapter);
            swingAdapter.setAbsListView(mListView);
            mListView.setAdapter(swingAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String spotInList = mListView.getItemAtPosition(position).toString();
                    Log.d("listview click test", spotInList);

                    FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("beer", spotInList);
                    SelectedBeerView sbv = new SelectedBeerView();
                    sbv.setArguments(args);


                    ft.replace(R.id.recycler_view_layout_container, sbv).addToBackStack(null).commit();

//                    UntappdAPICall apiCall = new UntappdAPICall();
//                    apiCall.searchForBeer(spotInList);

                }
            });

        }
    }
}























//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * Created by Brendan on 3/10/2016.
// */
//public class GarageProject extends AppCompatActivity {
//    ListView mListView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.beer_list_view);
//        mListView = (ListView) findViewById(R.id.list_view);
//        new otherTitle(this).execute();
//    }
//
//    private class otherTitle extends AsyncTask<Void, Void, ArrayList<String>> {
//        Context mContext;
////        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
////        View v = inflater.inflate(R.layout.beer_list_view, null);
//
//
//        String url = "http://garageproject.co.nz/pages/taproom";
//
//        public otherTitle(Context context) {
//            mContext = context;
//        }
//
//        @Override
//        protected ArrayList<String> doInBackground(Void... params) {
//            ArrayList<String> arr_beerName = new ArrayList<>();
//
//            String beerName = "";
//
//            try {
//                Document doc = Jsoup.connect(url).get();
//                Elements element = doc.select("a[class=link light]");
//                for (Element item : element){
//                    beerName = item.text();
//
//                    arr_beerName.add(beerName);
//                }
//
//            } catch(IOException e){
//                e.printStackTrace();
//            }
//
//            return arr_beerName;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<String> result) {
////                TextView beerOne = (TextView) findViewById(R.id.one);
////                for (String beer_addition : result){
////                    beerOne.append(beer_addition + "\n");
////                }
//
////
//
//            ArrayAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.pub_item, R.id.pub_text_view, result);
//            SwingLeftInAnimationAdapter swingAdapter = new SwingLeftInAnimationAdapter(adapter);
//            swingAdapter.setAbsListView(mListView);
//            mListView.setAdapter(swingAdapter);
//
//        }
//    }
//
//}
