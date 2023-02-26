package com.example.converter;

import java.util.Arrays;
import java.util.List;

public class ConversionFunctions {



    public static double convertTemperature(String input_unit, String output_unit, Double input_temp){
        Double output_temp;
        output_temp = 0.0;
        // from ºC to ºF
        if(input_unit.equals("ºC") && output_unit.equals("ºF")){
            output_temp= 1.8* input_temp + 32;
        }
        // from ºC -> K
        else if(input_unit.equals("ºC") && output_unit.equals("K")){
            output_temp = input_temp + 273.15;
        }
        // from ºF -> ºC
        else if(input_unit.equals("ºF") && output_unit.equals("ºC")){
            output_temp = (input_temp- 32)/1.8;
        }
        // from K ->ºC
        else if(input_unit.equals("K") && output_unit.equals("ºC")){
            output_temp = input_temp - 273.15;
        }
        // from F-> K
        else if(input_unit.equals("ºF") && output_unit.equals("K")){
            // F-> ºC
            output_temp = (input_temp- 32)/1.8;
            //C -> ºK
            output_temp = output_temp + 273.15;
        }
        // from K -> ºF
        else if(input_unit.equals("K") && output_unit.equals("ºF")){
            // K -> ºC
            output_temp = input_temp - 273.15;
            // ºC -> ºF
            output_temp = 1.8* output_temp + 32;
        }
        // same output _unit
        else{
            output_temp = input_temp;
        }
        return output_temp;
    }

    public static double convertMassVolume(String input_unit, String output_unit, Double input_mv, String ingredient) {
        String [] mass_units = {"kg","lb","oz","gr","g"};
        List<String> list_mass_units = Arrays.asList(mass_units);
        // Reference mass unit: kg

        String [] volume_units = {"L","gal","pt","fl oz","cm3","in3","mL","cup","tblsp","tsp","drop"};
        List<String> list_volume_units = Arrays.asList(volume_units);
        // Reference volume unit: L

        Double output_mv;

        // conversion
        // input unit is mass
        if (list_mass_units.contains(input_unit)) {
            // input unit is mass && output unit is mass
            if (list_mass_units.contains(output_unit)) {
                // convert from kg to other mass unit
                if (input_unit.equals("kg")) {
                    output_mv = convertMassFromKG(output_unit, input_mv);
                }
                // convert from a mass unit to kg
                else if(output_unit.equals("kg")){
                    output_mv= convertMassToKG(input_unit, input_mv);
                }
                // convert from a mass unit to other mass unit
                else{
                    Double output_aux;
                    output_aux= convertMassToKG(input_unit, input_mv);
                    output_mv = convertMassFromKG(output_unit, output_aux);
                }
            }
            // input unit is mass && output unit is volume
            else {
                // input unit is kg && output unit is volume
                if (input_unit.equals("kg")) {
                    // input unit is kg && output unit is L
                    if (output_unit.equals("L")) {
                        output_mv = convertKGtoL(ingredient, input_mv);
                    }
                    // input unit is kg && output unit is volume
                    else{
                        Double output_aux;
                        output_aux=convertKGtoL(ingredient, input_mv);
                        output_mv=convertVolumeFromL(output_unit, output_aux);
                    }
                }
                // input unit is mass && output unit is L
                else if(output_unit.equals("L")){
                    Double output_aux;
                    output_aux=convertMassToKG(input_unit,input_mv);
                    output_mv=convertKGtoL(ingredient, output_aux);
                }
                // input unit is mass && output unit is volume
                else{
                    Double output_aux_1,output_aux_2 ;
                    output_aux_1=convertMassToKG(input_unit,input_mv);
                    output_aux_2=convertKGtoL(ingredient, output_aux_1);
                    output_mv=convertVolumeFromL(output_unit, output_aux_2);
                }
            }

        }
        // input unit is volume
        else{
            // input unit is volume && output unit is volume
            if (list_volume_units.contains(output_unit)) {
                // input unit is L && output unit is volume
                if (input_unit.equals("L")) {
                    output_mv = convertVolumeFromL(output_unit, input_mv);
                }
                // convert from a volume unit to L
                else if(output_unit.equals("L")){
                    output_mv= convertVolumeToL(input_unit, input_mv);
                }
                // convert from a volume unit to other volume unit
                else{
                    Double output_aux;
                    output_aux= convertVolumeToL(input_unit, input_mv);
                    output_mv = convertVolumeFromL(output_unit, output_aux);
                }
            }
            // input unit is volume && output unit is mass
            else{
                // input unit is L && output unit is mass
                if (input_unit.equals("L")) {
                    // input unit is L && output unit is Kg
                    if (output_unit.equals("kg")) {
                        output_mv = convertLtoKG(ingredient, input_mv);
                    }
                    // input unit is L && output unit is mass
                    else{
                        Double output_aux;
                        output_aux=convertLtoKG(ingredient, input_mv);
                        output_mv=convertMassFromKG(output_unit, output_aux);
                    }
                }
                // input unit is volume && output unit is Kg
                else if(output_unit.equals("gg")){
                    Double output_aux;
                    output_aux=convertVolumeToL(input_unit,input_mv);
                    output_mv=convertLtoKG(ingredient, output_aux);
                }
                // input unit is volume && output unit is mass
                else{
                    Double output_aux_1,output_aux_2 ;
                    output_aux_1=convertVolumeToL(input_unit,input_mv);
                    output_aux_2=convertLtoKG(ingredient, output_aux_1);
                    output_mv=convertMassFromKG(output_unit, output_aux_2);
                }
            }
        }

        return output_mv;
    }
    private static double convertMassToKG(String input_mass_unit, Double input_mass){
        Double output_mass_kg;
        output_mass_kg=0.0;
        switch(input_mass_unit){
            case "lb":output_mass_kg= input_mass/2.2046;break;
            case "oz":output_mass_kg= input_mass/35.274;break;
            case "gr":output_mass_kg= input_mass/15432;break;
            case "g":output_mass_kg= input_mass*Math.pow(10,-3);break;
        }
        return output_mass_kg;
    }

    private static double convertMassFromKG(String output_mass_unit, Double input_mass_kg){
        Double output_mass;
        output_mass=0.0;

        switch(output_mass_unit){
            case "lb":output_mass= input_mass_kg*2.2046;break;
            case "oz":output_mass= input_mass_kg*35.274;break;
            case "gr":output_mass= input_mass_kg*15432;break;
            case "g":output_mass= input_mass_kg*Math.pow(10,3);break;
        }
        return output_mass;
    }

    private  static double convertVolumeToL(String input_volume_unit, Double input_volume){
        Double output_volume_L;
        output_volume_L=0.0;
        switch(input_volume_unit){
            case "gal": output_volume_L=input_volume/0.26417;break;
            case "pt":output_volume_L=input_volume/2.1134; break;
            case "fl oz":output_volume_L=input_volume/33.814; break;
            case "cm3":output_volume_L=input_volume*Math.pow(10,-3); break;
            case "in3":output_volume_L=input_volume/61.024; break;
            case "mL": output_volume_L=input_volume*Math.pow(10,-3); break;
            case "cup":output_volume_L=input_volume/4.2268; break;
            case "tblsp":output_volume_L=input_volume/67.628; break;
            case "tsp":output_volume_L=input_volume/202.88; break;
            case "drop":output_volume_L=input_volume*20000; break;
        }
        return output_volume_L;
    }

    private static double convertVolumeFromL(String output_volume_unit, Double input_volume_l){
        Double output_volume;
        output_volume=0.0;
        switch(output_volume_unit){
            case "gal": output_volume=input_volume_l*0.26417;break;
            case "pt":output_volume=input_volume_l*2.1134; break;
            case "fl oz":output_volume=input_volume_l*33.814; break;
            case "cm3":output_volume=input_volume_l*Math.pow(10,3); break;
            case "in3":output_volume=input_volume_l*61.024; break;
            case "mL": output_volume=input_volume_l*Math.pow(10,3); break;
            case "cup":output_volume=input_volume_l*4.2268; break;
            case "tblsp":output_volume=input_volume_l*67.628; break;
            case "tsp":output_volume=input_volume_l*202.88; break;
            case "drop":output_volume=input_volume_l/20000; break;
        }
        return output_volume;
    }

    private static double convertKGtoL(String ingredient, Double input_mass_kg){
        Double output_volume_L;
        output_volume_L=0.0;
        switch(ingredient){
            case "Water":output_volume_L=input_mass_kg/1.0;break;
            case "Olive Oil":output_volume_L=input_mass_kg/1.0945;break;
            case "White Wheat Flour":output_volume_L=input_mass_kg/1.6863;break;
            case "White Granulated Sugar":output_volume_L=input_mass_kg/1.1765;break;
            case "Honey":output_volume_L=input_mass_kg/0.72464;break;
            case "Wine":output_volume_L=input_mass_kg/1.0838;break;
        }
        return output_volume_L;
    }

    private static double convertLtoKG(String ingredient, Double input_volume_L){
        Double output_mass_kg;
        output_mass_kg=0.0;
        switch(ingredient){
            case "Water":output_mass_kg=input_volume_L*1.0;break;
            case "Olive Oil":output_mass_kg=input_volume_L*1.0945;break;
            case "White Wheat Flour":output_mass_kg=input_volume_L*1.6863;break;
            case "White Granulated Sugar":output_mass_kg=input_volume_L*1.1765;break;
            case "Honey":output_mass_kg=input_volume_L*0.72464;break;
            case "Wine":output_mass_kg=input_volume_L*1.0838;break;
        }
        return output_mass_kg;
    }
}
