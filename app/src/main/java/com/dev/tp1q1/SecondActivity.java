package com.dev.tp1q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mentionTxt;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();

        getDataFromIntent();
    }

    private void initViews() {
        mentionTxt = findViewById(R.id.mentionTxt);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
    }

    private void getDataFromIntent() {
        String name = getIntent().getStringExtra("name"),
                age = getIntent().getStringExtra("age"),
                height = getIntent().getStringExtra("height");
        mentionTxt.setText(getMention(name, age, height));
    }

    private String getMention(String name, String age, String heightAsString) {
        String heightDescription;
        try {
            float heightAsFloat = Float.parseFloat(heightAsString);
            if (heightAsFloat == 1.7f)
                heightDescription = "moyenne";
            else if (heightAsFloat > 1.7f)
                heightDescription = "élancée";
            else
                heightDescription = "courte";
            return "Bonjour " + name + ", vous êtes âgé de " + age + " et vous êtes de taille " + heightDescription;
        } catch (NumberFormatException e) {
            /**
             * C'est on fait comme dessous, c'est quoi l'erreur ??
             */
            mentionTxt.setText("Désolé, une erreur s'est produite !");
            String s = "Traitement échoué !\n"
                    + "Nom de l'exception : " + e.getClass().getName() + "\n"
                    + "Message de l'exception : " + e.getMessage();
            Log.e("TAG", s);
            return "Désolé, une erreur s'est produite !";
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            Intent intent = new Intent(this, FirstActivity.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("age", getIntent().getStringExtra("age"));
            intent.putExtra("height", getIntent().getStringExtra("height"));
            //if (!mentionTxt.getText().equals("Désolé, une erreur s'est produite !"))
            intent.putExtra("info", mentionTxt.getText());
            startActivity(intent);
        }
    }
}
