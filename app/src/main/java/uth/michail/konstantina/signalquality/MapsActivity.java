package uth.michail.konstantina.signalquality;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Signal Quality Folder";

    MyPhoneStateListener MyListener;

    private GoogleMap mMap;
    GPSTracker gpsTracker;

    private ArrayList<LatLng> pointsBlack;
    private ArrayList<LatLng> pointsRed;
    private ArrayList<LatLng> pointsYellow;
    private ArrayList<LatLng> pointsGreen;
    private ArrayList<LatLng> pointsBlue;

    private ArrayList<String> arrayInfo;

    Polyline line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Get the instance of TelephonyManager


    }

    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

            TextView infoAndMap_textView = (TextView) findViewById(R.id.infoAndMap_id);

            String gsmSignalStrength = Integer.toString(signalStrength.getGsmSignalStrength() * 2 - 113);//dbm

            //Get the Network Type
            String strNetworkType = "";
            int networkType = telephonyManager.getNetworkType();

            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    strNetworkType = "1xRTT";
                    break;

                case TelephonyManager.NETWORK_TYPE_CDMA:
                    strNetworkType = "CDMA";
                    break;

                case TelephonyManager.NETWORK_TYPE_EDGE:
                    strNetworkType = "EDGE";
                    break;

                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    strNetworkType = "eHRPD";
                    break;

                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    strNetworkType = "EVDO revision 0";
                    break;

                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    strNetworkType = "EVDO revision A";
                    break;

                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    strNetworkType = "EVDO revision B";
                    break;

                case TelephonyManager.NETWORK_TYPE_GPRS:
                    strNetworkType = "GPRS";
                    break;

                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    strNetworkType = "HSDPA";
                    break;

                case TelephonyManager.NETWORK_TYPE_HSPA:
                    strNetworkType = "HSPA";
                    break;

                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    strNetworkType = "HSPA+";
                    break;

                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    strNetworkType = "HSUPA";
                    break;

                case TelephonyManager.NETWORK_TYPE_IDEN:
                    strNetworkType = "iDen";
                    break;

                case TelephonyManager.NETWORK_TYPE_LTE:
                    strNetworkType = "LTE";
                    break;

                case TelephonyManager.NETWORK_TYPE_UMTS:
                    strNetworkType = "UMTS";
                    break;

                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    strNetworkType = "unknown";
                    break;


            }
            //  CID/LAC
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            int cid = gsmCellLocation.getCid();
            int lac = gsmCellLocation.getLac();

            //getlevel
            String strlevelType = "";
            int levelType = signalStrength.getLevel();

            switch (levelType) {
                case (0):
                    strlevelType = "SIGNAL_STRENGTH_NONE_OR_UNKNOWN";
                    break;
                case (1):
                    strlevelType = "SIGNAL_STRENGTH_POOR";
                    break;
                case (2):
                    strlevelType = "SIGNAL_STRENGTH_MODERATE";
                    break;
                case (3):
                    strlevelType = "SIGNAL_STRENGTH_GOOD";
                    break;
                case (4):
                    strlevelType = "SIGNAL_STRENGTH_GREAT";
                    break;
            }

            gpsTracker = new GPSTracker(MapsActivity.this);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            String info = "";

            if (gpsTracker.canGetLocation) {
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);
                // points.add(latLng);

                String strlevelTypeColor = "";
                int levelTypeColor = signalStrength.getLevel();

                switch (levelTypeColor) {
                    case (0):
                        strlevelTypeColor = "SIGNAL_STRENGTH_NONE_OR_UNKNOWN";
                        pointsBlack.add(latLng);
                        //redrawLineBlack();
                        break;
                    case (1):
                        strlevelTypeColor = "SIGNAL_STRENGTH_POOR";
                        pointsRed.add(latLng);
                        //redrawLineRed();
                        break;
                    case (2):
                        strlevelTypeColor = "SIGNAL_STRENGTH_MODERATE";
                        pointsYellow.add(latLng);
                        // redrawLineYellow();
                        break;
                    case (3):
                        strlevelTypeColor = "SIGNAL_STRENGTH_GOOD";
                        pointsGreen.add(latLng);
                        //redrawLineGreen();
                        break;
                    case (4):
                        strlevelTypeColor = "SIGNAL_STRENGTH_GREAT";
                        pointsBlue.add(latLng);
                        //redrawLineBlue();
                        break;
                }
                redrawLineBlack();
                redrawLineRed();
                redrawLineYellow();
                redrawLineGreen();
                redrawLineBlue();


                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Βρίσκεστε εδώ");
                mMap.addMarker(markerOptions);


                //getCallState
                String strCallState = "";
                int CallState = telephonyManager.getCallState();

                switch (CallState) {
                    case (0):
                        strCallState = "CALL_STATE_IDLE";
                        break;
                    case (1):
                        strCallState = "CALL_STATE_RINGING";
                        break;
                    case (2):
                        strCallState = "CALL_STATE_OFFHOOK";
                        break;
                }
                //getDataState
                String strDataState = "";
                int DataState = telephonyManager.getDataState();

                switch (DataState) {
                    case (0):
                        strDataState = "DATA_DISCONNECTED";
                        break;
                    case (1):
                        strDataState = "DATA_CONNECTING";
                        break;
                    case (2):
                        strDataState = "DATA_CONNECTED";
                        break;
                    case (3):
                        strDataState = "DATA_SUSPENDED";
                        break;
                }


                info += "\n Date :" + dateFormat.format(date);
                info += "\n CID/LAC : " + cid + "/" + lac;
                info += "\n " + strNetworkType + " " + gsmSignalStrength + " dBm";
                info += "\n Level:" + strlevelType;
                info += "\n Latitude : " + latitude;
                info += "\n Longitude : " + longitude;
                info += "\n Call State: " + strCallState;
                info += "\n Data State: " + strDataState;

                arrayInfo.add(info);
                writeInfo(info);

            } else {
                gpsTracker.showSettingsAlert();
            }


            infoAndMap_textView.setText(info);
        }
    }

    //How to draw path Black
    //private void writeInfo(LatLng info) {
    private void writeInfo(String info) {

        //txt file
        try {
            FileWriter fileWriter = new FileWriter(path + "/Signal Quality.txt");
            BufferedWriter out = new BufferedWriter(fileWriter);
            for (int i = 0; i < arrayInfo.size(); i++) {
                String inform = arrayInfo.get(i);
                out.write(inform);
                out.newLine();

            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //How to draw path Black
    private void redrawLineBlack() {

        mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLACK).geodesic(true);
        for (int i = 0; i < pointsBlack.size(); i++) {
            LatLng point = pointsBlack.get(i);
            options.add(point);
        }
        line = mMap.addPolyline(options); //add Polyline
    }

    //How to draw path Red
    private void redrawLineRed() {

        mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(8).color(Color.RED).geodesic(true);
        for (int i = 0; i < pointsRed.size(); i++) {
            LatLng point = pointsRed.get(i);
            options.add(point);
        }
        line = mMap.addPolyline(options); //add Polyline
    }

    //How to draw path Yellow
    private void redrawLineYellow() {

        mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(8).color(Color.YELLOW).geodesic(true);
        for (int i = 0; i < pointsYellow.size(); i++) {
            LatLng point = pointsYellow.get(i);
            options.add(point);
        }
        line = mMap.addPolyline(options); //add Polyline
    }

    //How to draw path Green
    private void redrawLineGreen() {

        //mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(8).color(Color.GREEN).geodesic(true);
        for (int i = 0; i < pointsGreen.size(); i++) {
            LatLng point = pointsGreen.get(i);
            options.add(point);
        }
        line = mMap.addPolyline(options); //add Polyline
    }

    //How to draw path Blue
    private void redrawLineBlue() {

        //mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < pointsBlue.size(); i++) {
            LatLng point = pointsBlue.get(i);
            options.add(point);
        }
        line = mMap.addPolyline(options); //add Polyline
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            //Get the instance of TelephonyManager
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            MyListener = new MyPhoneStateListener();
            telephonyManager.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

            pointsBlack = new ArrayList<LatLng>();
            pointsRed = new ArrayList<LatLng>();
            pointsYellow = new ArrayList<LatLng>();
            pointsGreen = new ArrayList<LatLng>();
            pointsBlue = new ArrayList<LatLng>();

            arrayInfo = new ArrayList<String>();

            File directory = new File(path);
            directory.mkdirs();


            return;
        }


    }
}
