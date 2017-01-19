package uth.michail.konstantina.signalquality;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

        mapActivity();
        informationMobileActivity();
        signalStrengthActivity();
    }


    private void mapActivity() {
        Button map_button=(Button)findViewById(R.id.mapButton_id);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map_intent= new Intent(MenuActivity.this, MapsActivity.class);
                Toast.makeText(MenuActivity.this,"Πλοηγηθείτε στον Χάρτη",Toast.LENGTH_LONG).show();
                startActivity(map_intent);
            }
        });

    }


    private void informationMobileActivity() {
        Button informationMobile_button=(Button)findViewById(R.id.informationMobileButton_id);
        informationMobile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent informationMobile_intent= new Intent(MenuActivity.this, InformationMobileActivity.class);
                Toast.makeText(MenuActivity.this,"Πληροφορίες Κινητού",Toast.LENGTH_LONG).show();
                startActivity(informationMobile_intent);
            }
        });
    }

    private void signalStrengthActivity() {
        Button signalStrength_button=(Button)findViewById(R.id.signalStrengthButton_id);
        signalStrength_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signalStrength_intent= new Intent(MenuActivity.this, SignalStengthActivity.class);
                Toast.makeText(MenuActivity.this,"Ισχύς Σήματος Κινητού",Toast.LENGTH_LONG).show();
                startActivity(signalStrength_intent);
            }
        });
    }
}
