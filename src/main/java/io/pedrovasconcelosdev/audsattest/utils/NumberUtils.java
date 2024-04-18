package io.pedrovasconcelosdev.audsattest.utils;

import java.text.DecimalFormat;

public class NumberUtils {

    public static String formatToTwoDecimalPlaces(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(number);
    }
}
