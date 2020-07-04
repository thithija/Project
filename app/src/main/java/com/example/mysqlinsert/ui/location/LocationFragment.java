package com.example.mysqlinsert.ui.location;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mysqlinsert.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LocationFragment extends Fragment implements OnMapReadyCallback {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_location, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng Matara = new LatLng(5.9527522,80.5371428);
        googleMap.addMarker(new MarkerOptions().position(Matara).title("CEB Matara"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Matara,07));

        LatLng Colombo = new LatLng(6.9240,79.8648);
        googleMap.addMarker(new MarkerOptions().position(Colombo).title("CEB Headquarters Colombo"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Colombo,07));

        LatLng Rathnapura = new LatLng(6.7082,80.3834);
        googleMap.addMarker(new MarkerOptions().position(Rathnapura).title("CEB Rathnapura"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Rathnapura,07));

        LatLng Galle = new LatLng(6.0393,80.2289);
        googleMap.addMarker(new MarkerOptions().position(Galle).title("CEB Galle"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Galle,07));

        LatLng Kandy = new LatLng(7.2918,80.6332);
        googleMap.addMarker(new MarkerOptions().position(Kandy).title("CEB Kandy"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Kandy,07));

        LatLng Dambulla = new LatLng(7.8682,80.6470);
        googleMap.addMarker(new MarkerOptions().position(Dambulla).title("CEB Dambulla"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Dambulla,07));

        LatLng Polonnaruva = new LatLng(7.9145,81.0004);
        googleMap.addMarker(new MarkerOptions().position(Polonnaruva).title("CEB Polonnaruva"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Polonnaruva,07));

        LatLng Hambanthota = new LatLng(6.1695,81.1239);
        googleMap.addMarker(new MarkerOptions().position(Hambanthota).title("CEB Hambanthota"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Hambanthota,07));

        LatLng Jaffna = new LatLng(9.66845,80.00742);
        googleMap.addMarker(new MarkerOptions().position(Jaffna).title("CEB Jaffna"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Jaffna,07));

        LatLng Kilinochchi = new LatLng(9.3766,80.4088);
        googleMap.addMarker(new MarkerOptions().position(Kilinochchi).title("CEB Kilinochchi"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Kilinochchi,07));

        LatLng anuradhapura = new LatLng(8.3393,80.4125);
        googleMap.addMarker(new MarkerOptions().position(anuradhapura).title("CEB Anuradhapura"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(anuradhapura,07));

    }

    public class MyWeatherService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("test","doInBackground : "+ strings[0]);
            StringBuffer stringBuffer =  new StringBuffer();
            try {

                URL url = new URL(strings[0]);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);

                String line= "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }

                Log.d("network","doInBackground" + stringBuffer);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuffer.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}