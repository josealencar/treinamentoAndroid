package br.com.alencar.jose.layouts;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Exercicio3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio3);
        LinearLayout llBottom = (LinearLayout) findViewById(R.id.llBotton);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            llBottom.setVisibility(View.GONE);
        } else {
            llBottom.setVisibility(View.VISIBLE);
        }
    }
}
