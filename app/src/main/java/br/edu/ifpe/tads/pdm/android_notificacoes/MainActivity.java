package br.edu.ifpe.tads.pdm.android_notificacoes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText editTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTime = (EditText) findViewById(R.id.edit_time);
    }


    public void scheduleAlarm(View view) {


        Long time = getAlarmTime();

        Intent intentAlarm = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(
                this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingAlarmIntent);

        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_LONG).show();

        this.finish();

    }

    public Long getAlarmTime() {
        Long tempoAdicional = null;

        if (editTime != null) {
            tempoAdicional = Long.valueOf(editTime.getText().toString());
            tempoAdicional *= 1000;
        } else {
            tempoAdicional = 10000l;
        }

        Long time = tempoAdicional;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            time += new GregorianCalendar().getTimeInMillis();
        } else {
            time += new Date().getTime();
        }
        return time;
    }


}
