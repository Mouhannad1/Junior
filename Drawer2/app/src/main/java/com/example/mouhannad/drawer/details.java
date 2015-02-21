package com.example.mouhannad.drawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;


public class details extends Activity {
    TextView tv,tv2;
    ImageView image;
    Button button;
    String name, ball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv = (TextView)findViewById(R.id.textView2);
        Intent intent = getIntent();
        String description = intent.getStringExtra("Description");
        tv.setText(description);


         image = (ImageView)findViewById(R.id.imageV);
        Intent intent2 = getIntent();
       String im = intent2.getStringExtra("Image");
        Ion.with(image).load(im);

        tv2 = (TextView)findViewById(R.id.textView3);
        Intent intent3 = getIntent();
        String price = intent3.getStringExtra("Price");
        tv.setText(price);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Added To Cart",
                        Toast.LENGTH_LONG).show();

                MyApplication app = new MyApplication();
                Actors actor = new Actors();
                actor.setDescription(getIntent().getStringExtra("Description"));
                actor.setImage(getIntent().getStringExtra("Image"));
                actor.setDob(getIntent().getStringExtra("Price"));
                actor.setName(getIntent().getStringExtra("Name"));

//                actor.setName(getIntent().getStringExtra("name"));

                app.globalActors.add(actor);


            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
