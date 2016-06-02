package dev.jgo.com.pms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import controllers.DBHandler;
import models.PM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBHandler db =  new DBHandler(this);

        //INSERT
        Log.d("Insert: ", "Inserting ..");
       // List<PM> pms = new ArrayList<PM>();
        PM pm1 = new PM("01/05/2014");
        PM pm2 = new PM("01/05/2015");
        PM pm3 = new PM("01/05/2016");

       // pms.add(pm1);
       // pms.add(pm2);
       // pms.add(pm3);
        db.addPM(pm1);
        db.addPM(pm2);
        db.addPM(pm3);


        //READ
        Log.d("Reading: ", "Reading all pms..");

        List<PM> pms = db.getAllPM();

        for(PM pm: pms){

            String log = "Id "+ pm.getId()+ " date "+ pm.getDate();
            Log.d("PM: ", log);


        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.user) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
