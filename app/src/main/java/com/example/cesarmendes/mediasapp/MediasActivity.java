package com.example.cesarmendes.mediasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cesarmendes.mediasapp.models.Cadeira;

import org.w3c.dom.Text;

import java.util.ArrayList;

import database.databaseAdapter;

public class MediasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medias);

        databaseAdapter dbAdapter = new databaseAdapter(this);
        dbAdapter.openBD();
        ArrayList<Cadeira> listaCadd = dbAdapter.getCadeiras();

        TextView mediaAr = (TextView) this.findViewById(R.id.show_media_arit);
        TextView mediaPond = (TextView) this.findViewById(R.id.show_media_pond);
        TextView ectsDone = (TextView) this.findViewById(R.id.show_ects_done);
        TextView year_one_med = (TextView) this.findViewById(R.id.show_primeiro);
        TextView year_sec_med = (TextView) this.findViewById(R.id.show_segundo);
        TextView year_trd_med = (TextView) this.findViewById(R.id.show_terceiro);

        int somaNotas = 0, i = 0;
        Double somaCreditos = 0.0 ,creditosNum = 0.0;

        double sumCredit1 = 0.0, sumCredit2 = 0.0, sumCredit3 = 0.0;
        double sumNotas1 = 0.0, sumNotas2= 0.0, sumNotas3 = 0.0;
        int a = 0,b = 0,c = 0;

        while(i < listaCadd.size()){
            somaNotas+= listaCadd.get(i).getNota();
            somaCreditos +=  listaCadd.get(i).getNota() * listaCadd.get(i).getCredito();
            creditosNum += listaCadd.get(i).getCredito();
            if(listaCadd.get(i).getAno() == 1){
                sumCredit1+=listaCadd.get(i).getCredito();
                sumNotas1+=listaCadd.get(i).getNota() * listaCadd.get(i).getCredito();
                a++;
            }
            if(listaCadd.get(i).getAno() == 2){
                sumCredit2+=listaCadd.get(i).getCredito();
                sumNotas2+=listaCadd.get(i).getNota() * listaCadd.get(i).getCredito();
                b++;
            }
            if(listaCadd.get(i).getAno() == 3){
                sumCredit3+=listaCadd.get(i).getCredito();
                sumNotas3+=listaCadd.get(i).getNota() * listaCadd.get(i).getCredito();
                c++;
            }
            i++;
    }
        if(i > 0){
        mediaAr.setText(String.format("%.2f",((double)somaNotas) / listaCadd.size()));
        mediaPond.setText(String.format("%.2f",(somaCreditos / creditosNum)));
        ectsDone.setText(String.format("%.2f",creditosNum));
        }else {
            mediaAr.setText("---");
            mediaPond.setText("---");
            ectsDone.setText("---");
        }
        if(a > 0)
        year_one_med.setText(String.format("%.2f",(sumNotas1/sumCredit1)));
        else
        year_one_med.setText("---");

        if(b > 0)
        year_sec_med.setText(String.format("%.2f",sumNotas2/sumCredit2));
        else
        year_sec_med.setText("---");

        if(c > 0)
        year_trd_med.setText(String.format("%.2f",sumNotas3/sumCredit3));
        else
            year_trd_med.setText("---");








    }
}
