package com.example.user.work5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Button buttonAdd,buttonRemove;
    TextView textView;
    EditText editTextSearch;
    ListView listView;
    ArrayList<Restaurant> list;
    MyAdapter adapter;
    Spinner spinner;
    ArrayAdapter spinnerAdapter;
    int index = 0;

    final int REQUEST_ADD_RESTAURANT = 0;
    final int REQUEST_READ_RESTAURANT_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        textView = (TextView) findViewById(R.id.textView1);
        listView = (ListView) findViewById(R.id.listView);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonRemove=(Button) findViewById(R.id.buttonRemove);
        editTextSearch = (EditText)findViewById(R.id.editTextSearch);
        buttonAdd.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);
        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.spinner,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setPrompt("정렬 기준 선택하기");
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String search = s.toString();
                if(search.length() > 0)
                    listView.setFilterText(search);
                else
                    listView.clearTextFilter();
            }
        });
}

    public void onClick(View view){

        switch(view.getId()){
            case R.id.buttonAdd:
                Intent intent = new Intent(this,MainActivity2.class);
                intent.putExtra("send1",index++);
                startActivityForResult(intent,REQUEST_ADD_RESTAURANT);
                break;
            case R.id.buttonRemove:
                if(MyAdapter.isDeletionMode){
                    buttonRemove.setText("맛집 삭제");
                    MyAdapter.isDeletionMode = false;
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("삭제")
                            .setPositiveButton("취소",null)
                            .setMessage("선택한 항목들을 정말로 숙청하겠습니까?")
                            .setIcon(R.drawable.potato)
                            .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(int i=0; i<list.size(); i++)
                                        if(list.get(i).isDeletionTarget)
                                            list.remove(i);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getApplicationContext(),"전부 숙청되었습니다.",Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    adapter.notifyDataSetChanged();
                }else{
                    adapter.notifyDataSetChanged();
                    if(adapter.getCount() == 0){
                        Toast.makeText(getApplicationContext(),"삭제 할 목록이 없습니다..",Toast.LENGTH_SHORT).show();
                    }else {
                        buttonRemove.setText("숙청");
                        MyAdapter.isDeletionMode = true;
                    }
                }
                break;

        }



    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//        dlg.setTitle("삭제")
//                .setPositiveButton("취소",null)
//                .setMessage("정말로 삭제하겠습니까?")
//                .setIcon(R.drawable.potato)
//                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        list.remove(position);
//                        adapter.notifyDataSetChanged();
//                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("send2", list.get(position));
        startActivityForResult(intent, REQUEST_READ_RESTAURANT_STATE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case REQUEST_ADD_RESTAURANT:
                    if (resultCode == RESULT_OK) {
                        list.add((Restaurant) data.getParcelableExtra("receive"));
                        textView.setText("맛집 리스트(" + list.size() + "개)");
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case REQUEST_READ_RESTAURANT_STATE:
                    break;
            }

        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Restaurant.compareOption = position;
        Collections.sort(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
