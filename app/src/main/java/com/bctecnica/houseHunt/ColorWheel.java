package com.bctecnica.houseHunt;

import android.graphics.Color;
import java.util.Random;

public class ColorWheel {

    // list of background colors to use
    private String[] colors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#53bbb4", // aqua
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#723DBA", // dark purple
            "#f092b0", // pink
            "#FFB100", // orange
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#b7c0c7", // light grey
            "#990000", // blood red
            "#808080", // grey
            "#20E537", // dark green
            "#FFBB81", // salmon
            "#26a69a"  // teal
    };

    // Used to get a random color from array then returns converted the hash to int
    public int getColor(){
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(colors.length);
        return Color.parseColor(colors[randomNumber]);
    }

}

