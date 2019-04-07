package com.js.zadaniedomowe1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tV;
    ImageView iV;
    int drawable, rand = 0, sound = 0;
    String name = "", temp = "";
    MediaPlayer mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //pobranie referencji
        iV = findViewById(R.id.imageView);
        tV = findViewById(R.id.textView);
        randNumber();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tV.getText().equals("") && sound != 0) {
                    if (mP != null) {
                        mP.stop();
                        mP = null;
                    } else {
                        mP = MediaPlayer.create(getApplicationContext(), sound);
                        mP.start();

                    Snackbar.make(view, "Odtwarzanie...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                }else
                    Toast.makeText(getApplicationContext(), "Nie wybrano kontaktu lub dźwięku!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void randNumber(){
        Random r = new Random();
        rand = r.nextInt(5);
        choosePicture(rand);
    }

    public void choosePicture(int i){
        switch (i){
            case 0 :
                drawable = R.drawable.avatar_1;
                break;
            case 1:
                drawable = R.drawable.avatar_2;
                break;
            case 2:
                drawable = R.drawable.avatar_3;
                break;
            case 3:
                drawable = R.drawable.avatar_4;
                break;
            case 4:
                drawable = R.drawable.avatar_5;
                break;
        }
        iV.setImageResource(drawable);
    }

    public void changeContact(View view) {
        Intent intent = new Intent(getApplicationContext(), Wyborkontaktu.class);
        startActivityForResult(intent,1);
    }

    public void changeSound(View view) {
        if(!tV.getText().equals("")) {
            Intent intent = new Intent(getApplicationContext(), Wybordzwieku.class);
            startActivityForResult(intent, 2);
        }else
            Toast.makeText(this, "Najpierw wybierz kontakt!", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //sprawdzamy requestCode - dzięki temu wiemy z jakiej aktywności zwracany jest wynik
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // pobieramy rezultat
                temp = name;
                name = data.getStringExtra("nameSet");
                //jeśli chcemy aby po ponownym wyborze TEGO SAMEGO kontaktu zdjęcie się zmieniło na inne, należy usunąć poniższego if'a
                if(!temp.equals(name)){
                    tV.setText(new String((Base64.decode(name,Base64.DEFAULT))));
                    randNumber();
                }

            }else if (resultCode == RESULT_CANCELED) {
                // wykonujemy odpowiednią akcje dla przypadku gdy nie otrzymamy rezultatu
                Toast.makeText(getApplicationContext(), "Anulowano wybór", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                sound = data.getIntExtra("sound", 0);
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Anulowano wybór", Toast.LENGTH_SHORT).show();
            }
        }
    }
}