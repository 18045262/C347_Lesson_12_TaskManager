package webservices.rp.edu.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    EditText etName, etDesc, etSecond;
    Button btnAddNew, btnCancel;
    String name, desc;
    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDescription);
        etSecond = findViewById(R.id.etSecond);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnCancel = findViewById(R.id.btnCancel);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                desc = etDesc.getText().toString();
                second = Integer.parseInt(etSecond.getText().toString());

                if (!name.isEmpty() && !desc.isEmpty()) {
                    DBHelper dbhelper = new DBHelper(AddTaskActivity.this);
                    Long inserted_id = dbhelper.insertTask(name, desc);

                    if (inserted_id != -1) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.SECOND,second);
                        Toast.makeText(AddTaskActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(AddTaskActivity.this, TaskNotificationReceiver.class);
                        int reqCode = 12345;
                        i.putExtra("name", name);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTaskActivity.this,reqCode,
                                i, PendingIntent.FLAG_CANCEL_CURRENT);

                        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        finish();

                    }
                    etName.setText("");
                    etDesc.setText("");
                    etSecond.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Content is empty",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });



    }
}
