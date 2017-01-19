package uth.michail.konstantina.signalquality;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.TextView;

public class SignalStengthActivity extends AppCompatActivity {

    MyPhoneStateListener MyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_stength);
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

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        MyListener = new MyPhoneStateListener();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        telephonyManager.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }


    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            TextView signalStrength_textView = (TextView) findViewById(R.id.signalStrength_id);
           // String level = Integer.toString(signalStrength.getLevel());
            String gsmSignalStrength = Integer.toString(signalStrength.getGsmSignalStrength() * 2 - 113);//dbm


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
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            int cid = gsmCellLocation.getCid();
            int lac = gsmCellLocation.getLac();

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

            String info = "";
            info += "\n Signal Strength: " + gsmSignalStrength + " dBm";
            info += "\n\n Level:\n" + strlevelType;
            info+="\n\n CID/LAC:\n" + cid + "/" + lac;
            info+="\n\n Call State:\n" +strCallState ;
            info+="\n\n Data State:\n" +strDataState ;

            signalStrength_textView.setText(info);
        }
    }

}
