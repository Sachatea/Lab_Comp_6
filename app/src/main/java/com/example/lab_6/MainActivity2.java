package com.example.lab_6;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab_6.databinding.ActivityMain2Binding;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    EditText Title;
    EditText Massage;
    EditText Date;
    Button button;
    DatePicker datePicker;
    TimePicker timePicker;

    private ActivityMain2Binding binding;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        binding =ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();


        Title = findViewById(R.id.editTextText);
        Massage = findViewById(R.id.editTextText2);

        datePicker = findViewById(R.id.Date);
        timePicker = findViewById(R.id.Time);

        button =findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity2.this);

                int minute = timePicker.getMinute();
                int hour = timePicker.getHour();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);

                myDB.add(Title.getText().toString().trim(), Massage.getText().toString().trim(), calendar.getTime().toString());


                setAlarm(calendar);



            }
        });

    }

    private void setAlarm(Calendar calendar) {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmR.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();


    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "dfdsfsd";
            String description = "dfdsfddsd";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("foxandroid", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

    }


}