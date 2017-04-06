package com.example.user.work5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    Button buttonAdd;
    TextView textView;
    ListView listView;
    ArrayList<Restaurant> list;
    ArrayAdapter<Restaurant> adapter;

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
        buttonAdd.setOnClickListener(this);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
}

    public void onClick(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("send1",list.size());
        startActivityForResult(intent,REQUEST_ADD_RESTAURANT);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("삭제")
                .setPositiveButton("취소",null)
                .setMessage("정말로 삭제하겠습니까?")
                .setIcon(R.drawable.potato)
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                }).show();
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,MainActivity3.class);
        intent.putExtra("send2",list.get(position));
        startActivityForResult(intent,REQUEST_READ_RESTAURANT_STATE);
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
}
