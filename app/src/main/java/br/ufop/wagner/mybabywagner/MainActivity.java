package br.ufop.wagner.mybabywagner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedResources.getInstance().loadBabies(this);

        Intent intent3 = new Intent(this, MyBaby.class);

        if (!SharedResources.getInstance().getBaby().getName().toString().equals("")){
            startActivity(intent3);
            finish();
        }

    }

    public void add(View view) {
    Intent intent = new Intent(this, BabyRegister.class);
    startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        SharedResources.getInstance().saveBabies(this);
        super.onDestroy();
    }
}
