package com.js.zadaniedomowe1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class Wybordzwieku extends AppCompatActivity {

    String[] tabSound;
    Spinner spinner;
    Button cancel;
    int sound = 0;
    //ArrayAdapter pozwala nam skonwertować dane z obiektu ArrayList na dane które możemy wyświetlić np za pomocą Spinnera
    ArrayAdapter<String> arrayAdapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybordzwieku);

        cancel = findViewById(R.id.button3);
        spinner = findViewById(R.id.spinner);
        list = new ArrayList<>();
        tabSound = getResources().getStringArray(R.array.sounds);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tabSound);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        sound = R.raw.mario;
                        break;
                    case 1:
                        sound = R.raw.ring01;
                        break;
                    case 2:
                        sound = R.raw.ring02;
                        break;
                    case 3:
                        sound = R.raw.ring03;
                        break;
                    case 4:
                        sound = R.raw.ring04;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ok(View view) {
            Intent intent = new Intent();
            intent.putExtra("sound", sound);
            setResult(RESULT_OK, intent);
            finish();

    }
}
