package com.example.cesarmendes.mediasapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cesarmendes.mediasapp.Fragments.CadeiraFragment;
import com.example.cesarmendes.mediasapp.Fragments.HomeFragment;
import com.example.cesarmendes.mediasapp.Fragments.ListaCadeirasFragment;
import com.example.cesarmendes.mediasapp.models.Cadeira;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, CadeiraFragment.OnFragmentInteractionListener
, ListaCadeirasFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        getSupportFragmentManager().beginTransaction().add(R.id.containerFrame, new HomeFragment()).commit();

    }

    @Override
    public void onMediasClick(View v) {
        Intent intent = new Intent(this,MediasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCadeirasClick(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame, new ListaCadeirasFragment()).addToBackStack(null).commit();


    }

    @Override
    public void onEditClick(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame, new CadeiraFragment()).addToBackStack(null).commit();


    }

    @Override
    public void onSaveButtonClick(View v ,Boolean bool) {
        if(bool)
            Toast.makeText(this,getString(R.string.writeSuccess),Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,getString(R.string.writeFail),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(View view) {

    }

    @Override
    public void onSaveButtonUpdateClick(View v, boolean valid) {
        if(valid) {
            Toast.makeText(this, getString(R.string.writeSuccessUpdate), Toast.LENGTH_LONG).show();
            super.onBackPressed();
            //getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame, new ListaCadeirasFragment()).addToBackStack(null).commit();
        }else
            Toast.makeText(this,getString(R.string.writeFailUpdate),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void listCaddRowClicked(Cadeira cad,View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrame, CadeiraFragment.newInstance(cad)).addToBackStack(null).commit();
    }
}
