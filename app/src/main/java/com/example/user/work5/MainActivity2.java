package com.example.user.work5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    
    
    EditText editTextName,editTextTel,editTextHomePage;
    EditText[] editTextMenu, editTextMenuPrice;

    boolean isDeletionMode = false;
    
    Intent intent;
    
    Button buttonAdd,buttonCancel;
    RadioButton[] radioButton;
    int category;
    RadioGroup radioGroup;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        try {
            editTextMenu = new EditText[3];
            editTextMenuPrice = new EditText[3];
            radioButton = new RadioButton[3];

            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextTel = (EditText) findViewById(R.id.editTextTel);
            editTextMenu[0] = (EditText) findViewById(R.id.editTextMenu1);
            editTextMenu[1] = (EditText) findViewById(R.id.editTextMenu2);
            editTextMenu[2] = (EditText) findViewById(R.id.editTextMenu3);
            editTextMenuPrice[0] = (EditText) findViewById(R.id.editTextMenuPrice1);
            editTextMenuPrice[1] = (EditText) findViewById(R.id.editTextMenuPrice2);
            editTextMenuPrice[2] = (EditText) findViewById(R.id.editTextMenuPrice3);
            editTextHomePage = (EditText) findViewById(R.id.editTextHomePage);

            buttonAdd = (Button) findViewById(R.id.buttonAdd);
            buttonCancel = (Button) findViewById(R.id.buttonCancel);

            radioButton[0] = (RadioButton) findViewById(R.id.radioButtonChicken);
            radioButton[1] = (RadioButton) findViewById(R.id.radioButtonPizza);
            radioButton[2] = (RadioButton) findViewById(R.id.radioButtonHamburger);

            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(this);

            category = 0;
            radioButton[0].isChecked();
            
            intent = getIntent();
            
        }catch(RuntimeException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.buttonAdd:
                    Menu menu[] = new Menu[3];
                    for (int i = 0; i < menu.length; i++)
                        menu[i] = new Menu(editTextMenu[i].getText().toString(), getInt(editTextMenuPrice[i].getText().toString()));
                    Restaurant restaurant = new Restaurant(
                            intent.getIntExtra("send1", 0),
                            editTextName.getText().toString(),
                            editTextTel.getText().toString(),
                            editTextHomePage.getText().toString(),
                            menu,
                            category);
                    intent.putExtra("receive", restaurant);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;

                case R.id.buttonCancel:
                    setResult(RESULT_CANCELED);
                    finish();
                    break;
            }
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    
    public int getInt(String s){
        return s.isEmpty()?0:Integer.parseInt(s);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        category = 0;
        switch(checkedId){
            case R.id.radioButtonChicken:
                category = 0;
                break;
            case R.id.radioButtonPizza:
                category = 1;
                break;
            case R.id.radioButtonHamburger:
                category = 2;
                break;
        }
        radioButton[category].isChecked();
    }
}
