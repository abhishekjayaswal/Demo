package com.anjali.flightbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    AppCompatSpinner editTextSearchFrom;
    AppCompatSpinner editTextSearchTo;
    AppCompatTextView editTextSearchDate;
    AppCompatButton buttonSearchFlights;
    AppCompatImageView imageViewsSwapHoriz;
    Calendar myCalendarFrom;
    SpinnerAdapter spinnerAdapter;
    SpinnerAdapter spinnerAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myCalendarFrom = Calendar.getInstance();
        editTextSearchFrom = findViewById(R.id.edit_text_search_from);
        editTextSearchTo = findViewById(R.id.edit_text_search_to);
        editTextSearchDate = findViewById(R.id.edit_text_search_date);
        buttonSearchFlights = findViewById(R.id.button_search_flights);
        imageViewsSwapHoriz = findViewById(R.id.image_view_swap_horiz);
        imageViewsSwapHoriz.setClickable(true);
        buttonSearchFlights.setOnClickListener(this);
        imageViewsSwapHoriz.setOnClickListener(this);
        editTextSearchDate.setClickable(true);
        editTextSearchDate.setOnClickListener(this);
        ArrayList<String> cities= new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.list_of_cites)));
//        spinnerAdapter= new SpinnerAdapter(this , R.layout.item, cities);
//        editTextSearchFrom.setAdapter(spinnerAdapter);
//        editTextSearchFrom.setOnItemSelectedListener(this);
//        spinnerAdapter1= new SpinnerAdapter(this , R.layout.item, cities);
//        editTextSearchTo.setAdapter(spinnerAdapter1);
//        editTextSearchTo.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

                case R.id.button_search_flights:
                    processSearchFlight();
                    break;
                case R.id.image_view_swap_horiz:
                    processSwap();
                    break;
                 case R.id.edit_text_search_date:
                    processDate();
                    break;

        }

    }

    private void processDate() {
        final int year = myCalendarFrom.get(Calendar.YEAR);
        final int month = myCalendarFrom.get(Calendar.MONTH);
        final int day = myCalendarFrom.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog fromDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        int finalMonth = monthOfYear + 1;
                        Calendar c = Calendar.getInstance();
                        c.set(year1,monthOfYear,dayOfMonth);
                        Date date= c.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy", Locale.US);
                        editTextSearchDate.setText(sdf.format(date));
                    }
                }, year, month, day);
        fromDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
        fromDatePicker.show();
    }

    private void processSwap() {

//        if (TextUtils.isEmpty(editTextSearchFrom.getSelectedItem().toString())) {
//            editTextSearchFrom.("Enter city name");
//        } else if (TextUtils.isEmpty(editTextSearchTo.getSelectedItem().toString())) {
//            editTextSearchTo.setError("Enter city name ");
//        } else
//        {
////            String swap = editTextSearchFrom.getSelectedItem().toString();
////            editTextSearchFrom.setSelected(editTextSearchTo.getSelectedItem().toString());
////            editTextSearchTo.setSelected(swap);
//        }
    }

    private void processSearchFlight() {
//        if (TextUtils.isEmpty(editTextSearchFrom.getSelectedItem().toString())) {
//            editTextSearchFrom.setError("Enter city name");
//        } else if (TextUtils.isEmpty(editTextSearchTo.getText())) {
//            editTextSearchTo.setError("Enter city name ");
//        } else if (TextUtils.isEmpty(editTextSearchDate.getText())) {
//            editTextSearchDate.setError("Enter date");
//        } else {
//            Toast.makeText(this, "Flight Details", Toast.LENGTH_SHORT).show();
//        }
//    }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}