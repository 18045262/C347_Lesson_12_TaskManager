package webservices.rp.edu.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;

    ArrayList<Task> al;
    ArrayAdapter<Task> ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(MainActivity.this);
        al = db.getAllTasks();
        db.close();

        ca = new TaskAdapter(MainActivity.this, R.layout.row,al);
        lv.setAdapter(ca);
        lv.deferNotifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddTaskActivity.class);
                startActivity(i);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task n = al.get(position);
                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                i.putExtra("task", n);
                startActivityForResult(i,9);
                lv.deferNotifyDataSetChanged();
            }
        });


    }
}
