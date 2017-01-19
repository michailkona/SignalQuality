package uth.michail.konstantina.signalquality;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class InformationMobileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_mobile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ntmichail@yahoo.gr", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView infoMobile_textView=(TextView)findViewById(R.id.infoMobile_id);

        //Get the instance of TelephonyManager
        TelephonyManager telephonyManager=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);

        String networkCountryIso=telephonyManager.getNetworkCountryIso();
        String simCountryIso=telephonyManager.getSimCountryIso();


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

        String info = "";

        info += "\n Network Country ISO:" + networkCountryIso;
        info += "\n SIM Country ISO:" + simCountryIso;
        info += "\n Network Type:" + strNetworkType;


        infoMobile_textView.setText(info);//displaying the information in the textView


    }

}
