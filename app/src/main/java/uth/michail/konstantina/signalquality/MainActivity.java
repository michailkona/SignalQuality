package uth.michail.konstantina.signalquality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView)findViewById(R.id.imageView_id);
        imageView.setImageResource(R.drawable.imagemain);

        menuActivity();
    }

    private void menuActivity() {
        Button menuButton=(Button)findViewById(R.id.menuButton_id);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu_intent= new Intent(MainActivity.this, MenuActivity.class);
                Toast.makeText(MainActivity.this,"Πλοηγηθείτε στις Επιλογές",Toast.LENGTH_LONG).show();
                startActivity(menu_intent);
            }
        });


    }
}
