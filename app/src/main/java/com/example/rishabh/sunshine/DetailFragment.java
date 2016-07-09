package com.example.rishabh.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URISyntaxException;

/**
 * A placeholder fragment containing a simple view.
 */
@SuppressWarnings("ALL")
public class DetailFragment extends Fragment {

    public String weather;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        weather = intent.getExtras().getString(Intent.EXTRA_TEXT);

        TextView textDisplay = (TextView) v.findViewById(R.id.weatherInfo);
        textDisplay.setText(weather);
        return v;
    }

    public void onCreate(Bundle savedInstanceStates){
        super.onCreate(savedInstanceStates);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_detail, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(), Settings.class));
            return true;
        }
        else if(id == R.id.view_location){
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String location = pref.getString("location", "110085");
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+location);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            return true;
        }
        else if(id == R.id.share_weather){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sendIntent.putExtra(Intent.EXTRA_TEXT, weather+"#SunshineApp");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
