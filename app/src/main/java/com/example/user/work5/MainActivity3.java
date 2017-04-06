package com.example.user.work5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{
    TextView textViewName,textViewTel,textViewHomePage,textViewDate;
    TextView[] textViewMenu;
    ImageView imageViewCategory,imageViewCall,imageViewHomePage;
    Restaurant restaurant;
    Button buttonBack;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
    }

    private void init() {
        try {
            textViewName = (TextView) findViewById(R.id.textViewName);
            textViewDate = (TextView) findViewById(R.id.textViewDate);
            textViewTel = (TextView) findViewById(R.id.textViewTel);
            textViewHomePage = (TextView) findViewById(R.id.textViewHomePage);

            imageViewCategory = (ImageView) findViewById(R.id.imageViewCategory);
            imageViewCall = (ImageView) findViewById(R.id.imageViewCall);
            imageViewHomePage = (ImageView) findViewById(R.id.imageViewHomePage);

            buttonBack = (Button) findViewById(R.id.buttonBack);
            buttonBack.setOnClickListener(this);

            textViewMenu = new TextView[3];

            textViewMenu[0] = (TextView) findViewById(R.id.textViewMenu1);
            textViewMenu[1] = (TextView) findViewById(R.id.textViewMenu2);
            textViewMenu[2] = (TextView) findViewById(R.id.textViewMenu3);

            imageViewCall.setOnClickListener(this);
            intent = getIntent();
            setTextView((Restaurant) intent.getParcelableExtra("send2"));
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    private void setTextView(Restaurant restaurant){
        this.restaurant = restaurant;
        textViewName.setText(restaurant.getName());
        textViewTel.setText(restaurant.getTel());
        textViewHomePage.setText(restaurant.getHomepage());
        textViewDate.setText(restaurant.getDate());
        imageViewCategory.setImageResource(restaurant.getCategoryImage());
        for(int i=0; i<textViewMenu.length; i++)
            textViewMenu[i].setText(restaurant.getMenu()[i].toString());
    }


    @Override
    public void onClick(View v) {
        try{
        switch(v.getId()) {
            case R.id.imageViewCall:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" + restaurant.getTel())));
                break;
            case R.id.imageViewHomePage:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + restaurant.getHomepage())));
                break;
            case R.id.buttonBack:
                setResult(RESULT_OK, intent);
                finish();
        }
        }catch(RuntimeException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
