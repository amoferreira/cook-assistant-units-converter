package com.example.converter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/* A melhorar:
 - outra opção é o spinner 2 depender do 1, ou seja se o utilizador selecionar ºC não aparece no spinner 2
 */
public class TemperatureConverterActivity extends AppCompatActivity{

    private EditText input_value_et;
    private TextView output_value_tv;
    private Spinner input_unit_sp;
    private Spinner output_unit_sp;
    private String input_unit;
    private String output_unit;
    private Double input_temp;
    private Double output_temp;
    private Button convertButton_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setIcon(R.drawable.app_icon);
            bar.setTitle("Converter");
            bar.setDisplayShowHomeEnabled(true);
        }

        setContentView(R.layout.activity_temperature_converter);
        //////////////////////////////////////////////////////////////////////


        //Initializations
        convertButton_temp = (Button) findViewById(R.id.buttonConvert);
        input_value_et = (EditText) findViewById(R.id.input_temp);
        output_value_tv = (TextView)findViewById(R.id.output_temp);
        input_unit_sp = (Spinner) findViewById(R.id.input_unit_temp_sp);
        output_unit_sp = (Spinner) findViewById(R.id.output_unit_temp_sp);
        // stop default keyboard from popping up
        input_value_et.setShowSoftInputOnFocus(false);

        // List of temperatures
        ArrayAdapter<CharSequence> temp_adapter = ArrayAdapter.createFromResource(this, R.array.temperature_unit, android.R.layout.simple_spinner_item);
        temp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //add the list of temperatures to spinners
        input_unit_sp.setAdapter(temp_adapter);
        output_unit_sp.setAdapter(temp_adapter);

        //get input unit
        input_unit_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                input_unit = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //get output unit
        output_unit_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                output_unit = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //////////////////////////////////////////////////////////////////////
    // keyboard
    //method to update text as we click on the buttons
    private void updateText(String strToAdd){
        String oldStr = input_value_et.getText().toString();
        //to place a number where the cursor is - divide the old string in two (the portion at the left and at the right of the place of the cursor)
        int cursorPos = input_value_et.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        input_value_et.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        //keep the cursor at the place where it was put
        input_value_et.setSelection(cursorPos + 1);
    }

    public void zeroBTN(View view){
        String strToAdd = "0";
        updateText(strToAdd);
    }

    public void pointBTN(View view){
        String strToAdd = ".";
        updateText(strToAdd);
    }

    public void backspaceBTN(View view){
        int cursorPos = input_value_et.getSelectionStart();
        int textLen = input_value_et.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            //replace different characters within a string
            SpannableStringBuilder selection = (SpannableStringBuilder) input_value_et.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            input_value_et.setText(selection);
            input_value_et.setSelection(cursorPos - 1);
        }
    }

    public void oneBTN(View view){
        String strToAdd = "1";
        updateText(strToAdd);
    }

    public void twoBTN(View view){
        String strToAdd = "2";
        updateText(strToAdd);
    }

    public void threeBTN(View view){
        String strToAdd = "3";
        updateText(strToAdd);
    }

    public void fourBTN(View view){
        String strToAdd = "4";
        updateText(strToAdd);
    }

    public void fiveBTN(View view){
        String strToAdd = "5";
        updateText(strToAdd);
    }

    public void sixBTN(View view){
        String strToAdd = "6";
        updateText(strToAdd);
    }

    public void sevenBTN(View view){
        String strToAdd = "7";
        updateText(strToAdd);
    }

    public void eightBTN(View view){
        String strToAdd = "8";
        updateText(strToAdd);
    }

    public void nineBTN(View view){
        String strToAdd = "9";
        updateText(strToAdd);
    }

    // Convert Button
    public void convertBTN(View view){
        if (input_value_et.getText().toString().trim().length() <= 0 || input_value_et.getText().toString().equals(".")) {
            Toast.makeText(TemperatureConverterActivity.this, "Input temperature value to convert.", Toast.LENGTH_SHORT).show();
            output_value_tv.setText(" ");
        }
        else{
            if(input_unit==output_unit){
                Toast.makeText(TemperatureConverterActivity.this, "Conversion between the same units.", Toast.LENGTH_SHORT).show();
                input_temp= Double.valueOf(input_value_et.getText().toString());
                output_value_tv.setText(String.format("%.2f", input_temp));
            }
            else {//get input value
                input_temp = Double.valueOf(input_value_et.getText().toString());
                output_temp = ConversionFunctions.convertTemperature(input_unit, output_unit, input_temp);
                output_value_tv.setText(String.format("%.2f", output_temp));
            }
        }
    }

    //////////////////////////////////////////////////////////////////////
    // Three dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater(); //from activity
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.home:{
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.mass_volume_conv:{
                Intent intent = new Intent(this, MassVolumeConverterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.temperature_conv:{
                Intent intent = new Intent(this, TemperatureConverterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.version_info:{
                Toast.makeText(this,R.string.app_version,Toast.LENGTH_SHORT ).show();
                break;
            }
        }
        return true;
    }
}
