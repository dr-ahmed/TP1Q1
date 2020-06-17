package com.dev.tp1q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    TextView infoTxt;
    EditText nameEdt, ageEdt, heightEdt;
    Button mentionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getBackDataFromSecondActivityIntent();
    }

    private void initViews() {
        infoTxt = findViewById(R.id.infoTxt);
        nameEdt = findViewById(R.id.nameEdt);
        ageEdt = findViewById(R.id.ageEdt);
        heightEdt = findViewById(R.id.heightEdt);
        mentionBtn = findViewById(R.id.mentionBtn);
        mentionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mentionBtn) {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", nameEdt.getText().toString());
            intent.putExtra("age", ageEdt.getText().toString());
            intent.putExtra("height", heightEdt.getText().toString());
            startActivity(intent);
            Log.v("TAG", "Données envoyées pour traitement …");
        }
    }

    private void getBackDataFromSecondActivityIntent() {
        if (getIntent().hasExtra("name"))
            nameEdt.setText(getIntent().getStringExtra("name"));
        if (getIntent().hasExtra("age"))
            ageEdt.setText(getIntent().getStringExtra("age"));
        if (getIntent().hasExtra("height"))
            heightEdt.setText(getIntent().getStringExtra("height"));
        if (getIntent().hasExtra("info")) {
            infoTxt.setText(getIntent().getStringExtra("info"));
            Log.i("TAG", "Traitement réussi, données reçues.");
        }
    }
}
