package com.example.yutzuliu.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    String gender="", blood="",birth="";
    RadioButton menRb, womRb;
    CheckBox check;
    EditText nameT, phoneT, emailT;
    TextView lab1, lab2;
    boolean iscorrect = false , isNull=true;
    Button createbtn, birthBtn;
    String mYear, mMonth, mDate;
    Calendar calendar = Calendar.getInstance();

    RadioGroup genderRg;
    Spinner spinner;

    DatePickerDialog.OnDateSetListener datePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        RadioGroupListener listener = new RadioGroupListener();
        genderRg.setOnCheckedChangeListener(listener);

        final ArrayAdapter<CharSequence> lunchlist = ArrayAdapter.createFromResource(MainActivity.this, R.array.bloodtype, android.R.layout.simple_spinner_item);
        spinner.setAdapter(lunchlist);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blood = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        emailT.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (Linkify.addLinks(emailT.getText(), Linkify.EMAIL_ADDRESSES)) {
                    lab2.setText("correct!");
                } else {
                    lab2.setText("");
                }
                return false;
            }

        });


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonNullData();
                if(isNull==false) {
                    checkData();
                    if (iscorrect == true && isNull == false) {

                        Intent intent = new Intent(MainActivity.this, Page2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", nameT.getText().toString());
                        bundle.putString("gender", gender);
                        bundle.putString("blood", blood);
                        bundle.putString("email", emailT.getText().toString());
                        bundle.putString("phone", phoneT.getText().toString());
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }
            }
        });



        datePick = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear=String.valueOf(year);
                mMonth=String.valueOf(month);
                mDate=String.valueOf(dayOfMonth);
                birthBtn.setText(mYear+"/"+mMonth+"/"+mDate);
                birth=mYear+"/"+mMonth+"/"+mDate;
            }
        };

        birthBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, datePick,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


    }




    public void checkData(){

        if(check.isChecked()==true && Linkify.addLinks(emailT.getText(),Linkify.EMAIL_ADDRESSES)){
            iscorrect=true;
        }
        else if(check.isChecked()==false){
            iscorrect=false;
            Toast.makeText(MainActivity.this, "請勾選確認", Toast.LENGTH_LONG).show();
        }
        else if (Linkify.addLinks(emailT.getText(), Linkify.EMAIL_ADDRESSES)==false){
            iscorrect=false;
            Toast.makeText(MainActivity.this, "請確認信箱正確", Toast.LENGTH_LONG).show();
        }
    }


    public void findView(){
        createbtn = (Button) findViewById(R.id.createBtn);
        birthBtn=(Button)findViewById(R.id.birthBtn);
        nameT = (EditText) findViewById(R.id.nameEdt);
        emailT = (EditText) findViewById(R.id.mailEdt);
        phoneT = (EditText) findViewById(R.id.phoneEdt);
        lab1 = (TextView) findViewById(R.id.label);
        lab2 = (TextView) findViewById(R.id.label2);
        check = (CheckBox) findViewById(R.id.sureChk);
        menRb = (RadioButton) findViewById(R.id.radioButton);
        womRb = (RadioButton) findViewById(R.id.radioButton2);
        genderRg = (RadioGroup) findViewById(R.id.radioGroup);
        spinner = (Spinner) findViewById(R.id.bloodSpn);
    }


    public void nonNullData(){
        if("".equals(nameT.getText().toString().trim())||
                "".equals(gender.trim())||
                "".equals(blood.trim())||
                "".equals(birth.trim())||
                "".equals(phoneT.getText().toString().trim())){
            isNull=true;
            Toast.makeText(MainActivity.this,"請確認資料都已填寫",Toast.LENGTH_LONG).show();
        }
        else
            isNull = false;

    }



    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radioButton :
                    gender=menRb.getText().toString();
                    break;
                case R.id.radioButton2:
                    gender=womRb.getText().toString();
                    break;
            }
        }
    }

}