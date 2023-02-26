package com.example.converter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MassVolumeConverterActivity extends AppCompatActivity {

    private Spinner spinner_ingredients;
    private Spinner input_unit_sp;
    private Spinner output_unit_sp;
    private ImageView imageView_ingredient_icon;
    private EditText input_value_et;
    private TextView output_value_tv;
    private int ingredient;
    private String input_unit;
    private String output_unit;
    private Double input_mv;
    private Double output_mv;
    private String input_ingredient;
    private Button convertButton_mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setIcon(R.drawable.app_icon);
            bar.setTitle("Converter");
            bar.setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_mass_volume_converter);

        //////////////////////////////////////////////////////////////////////
        //Initializations
        convertButton_mv = (Button) findViewById(R.id.buttonConvert);
        input_value_et = (EditText) findViewById(R.id.input_temp);
        output_value_tv = (TextView)findViewById(R.id.output_temp);
        input_unit_sp = (Spinner) findViewById(R.id.input_unit_temp_sp);
        output_unit_sp = (Spinner) findViewById(R.id.output_unit_temp_sp);
        spinner_ingredients=findViewById(R.id.ingredients_sp);
        imageView_ingredient_icon=findViewById(R.id.ingredient_icon);

        // stop default keyboard from popping up
        input_value_et.setShowSoftInputOnFocus(false);

        // List of ingredients
        ArrayAdapter<CharSequence> adapterIngredients = ArrayAdapter.createFromResource(this, R.array.ingredients_list, android.R.layout.simple_spinner_item);
        adapterIngredients.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add the list of ingredients to spinners
        spinner_ingredients.setAdapter(adapterIngredients);

        //get input ingredient
        spinner_ingredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageView_ingredient_icon.setImageResource(R.drawable.bake);
                input_ingredient = parent.getItemAtPosition(position).toString();
                switch(position){
                    case 0:
                        imageView_ingredient_icon.setImageResource(R.drawable.bake);
                        ingredient = 0;
                        break;
                    case 1:
                        imageView_ingredient_icon.setImageResource(R.drawable.water);
                        ingredient = 1;
                        break;
                    case 2:
                        imageView_ingredient_icon.setImageResource(R.drawable.olive_oil);
                        ingredient = 2;
                        break;
                    case 3:
                        imageView_ingredient_icon.setImageResource(R.drawable.white_wheat_flour);
                        ingredient = 3;
                        break;
                    case 4:
                        imageView_ingredient_icon.setImageResource(R.drawable.sugar);
                        ingredient = 4;
                        break;
                    case 5:
                        imageView_ingredient_icon.setImageResource(R.drawable.honey);
                        ingredient = 5;
                        break;
                    case 6:

                        imageView_ingredient_icon.setImageResource(R.drawable.wine_glass);
                        ingredient = 6;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // List of mass-volume units
        ArrayAdapter<CharSequence> mv_adapter = ArrayAdapter.createFromResource(this, R.array.mass_volume_unit, android.R.layout.simple_spinner_item);
        mv_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add the list of mass-volume units to spinners
        input_unit_sp.setAdapter(mv_adapter);
        output_unit_sp.setAdapter(mv_adapter);

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
        if (input_value_et.getText().toString().trim().length() <= 0  || input_value_et.getText().toString().equals(".")) {
            Toast.makeText(MassVolumeConverterActivity.this, "Input quantity to convert.", Toast.LENGTH_SHORT).show();
            output_value_tv.setText(" ");
        }
        else{
            // input unit is the same as the output
            if(input_unit==output_unit){
                Toast.makeText(MassVolumeConverterActivity.this, "Conversion between the same units.", Toast.LENGTH_SHORT).show();
                input_mv= Double.valueOf(input_value_et.getText().toString());
                output_value_tv.setText(String.format("%.2f", input_mv));
            }
            else{
                String [] mass_units = {"kg","lb","oz","gr","g"};
                List<String> list_mass_units = Arrays.asList(mass_units);
                String [] volume_units = {"L","gal","pt","fl oz","cm3","in3","mL","cup","tblsp","tsp","drop"};
                List<String> list_volume_units = Arrays.asList(volume_units);
                String [] ingredients_list = {"Water", "Olive Oil", "White Wheat Flour", "White Granulated Sugar", "Honey", "Wine" };
                List<String> list_ingredients = Arrays.asList(ingredients_list);

                // mass -> volume or volume -> mass
                if ((list_mass_units.contains(input_unit) && list_volume_units.contains(output_unit)) || (list_volume_units.contains(input_unit) && list_mass_units.contains(output_unit))) {
                    // mass -> volume or volume -> mass and ingredient
                    if (list_ingredients.contains(input_ingredient)){
                        input_mv= Double.valueOf(input_value_et.getText().toString());
                        output_mv= ConversionFunctions.convertMassVolume(input_unit, output_unit, input_mv,input_ingredient);
                        output_value_tv.setText(String.format("%.2f", output_mv));
                    }
                    // mass -> volume or volume -> mass and no ingredient
                    else {
                        Toast.makeText(MassVolumeConverterActivity.this, "Pick an ingredient.", Toast.LENGTH_SHORT).show();
                        output_value_tv.setText(" ");
                    }
                }
                // mass -> mass or volume -> volume
                else{
                    input_mv= Double.valueOf(input_value_et.getText().toString());
                    output_mv= ConversionFunctions.convertMassVolume(input_unit, output_unit, input_mv,input_ingredient);
                    output_value_tv.setText(String.format("%.2f", output_mv));
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////
    // info
    public void popMethod(View v) {
        Intent i = new Intent(getApplicationContext(),PopActivity.class);
        i.putExtra("ingredient",Integer.toString(ingredient));
        startActivity(i);
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