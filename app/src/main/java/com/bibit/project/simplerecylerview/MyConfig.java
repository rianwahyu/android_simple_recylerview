package com.bibit.project.simplerecylerview;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MyConfig {
    public static String konversi(String nilai) {

        String harga="";

        try {
            DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
            formatSymbols.setDecimalSeparator(',');
            formatSymbols.setGroupingSeparator('.');

            DecimalFormat df = new DecimalFormat("####,###.###", formatSymbols);

            Double d = Double.parseDouble(nilai);

            harga = df.format(d);

        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return harga ;
    }
}
