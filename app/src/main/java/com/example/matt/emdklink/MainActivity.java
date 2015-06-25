package com.example.matt.emdklink;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.symbol.emdk.*;


public class MainActivity extends ActionBarActivity implements EMDKManager.EMDKListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            EMDKResults results = EMDKManager.getEMDKManager(this.getApplicationContext(), this);
            if (results.statusCode == EMDKResults.STATUS_CODE.FAILURE) {
                this.alert("getEMDKManager failed" + results.statusCode);
            } else {
                this.alert("loaded?");
            }
        }
        catch(Exception ex) {
            this.alert(ex.getMessage());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOpened(EMDKManager emdkManager) {
        this.alert("onOpened");
    }

    @Override
    public void onClosed() {
        this.alert("onClosed");
    }

    private void alert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .show();
    }
}
