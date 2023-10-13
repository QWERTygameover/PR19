package com.example.myapplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.FragmentManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity {

    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;

    int DIALOG_DATE = 2;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    TextView tvDate;

    final String LOG_TAG = "myLogs";
    final int DIALOG = 3;

    Dialog dialog;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDate = (TextView) findViewById(R.id.tvDate);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        tvDate.setText(format.format(new Date()));
        DateFormat df = new SimpleDateFormat("HH:mm");
        tvTime.setText(df.format(Calendar.getInstance().getTime()));
    }

    public void onclick(View view) {
        showDialog(DIALOG_TIME);
    }
    public void onclicktwo(View view) {
        showDialog(DIALOG_DATE);
    }

    public void onclickThree(View v) {
        showDialog(DIALOG);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBackTwo, myYear, myMonth, myDay);
            return tpd;
        }
        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setIcon(R.drawable.nigga);
            adb.setPositiveButton("OK", null);
            dialog = adb.create();

            // обработчик отображения
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Show");
                }
            });

            // обработчик отмены
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Cancel");
                }
            });

            // обработчик закрытия
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    OnTimeSetListener myCallBack = new OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
    DatePickerDialog.OnDateSetListener myCallBackTwo = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
        }
    };
}