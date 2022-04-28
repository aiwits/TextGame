package com.print;

import com.print.color.Color;
import com.print.color.ToColorString;

public class Main {

    public static void main(String[] args) {
        ToColorString colorString = ToColorString.getInstance();
        String str = "ABC";
        System.out.println(colorString.toColorString(str, Color.STYLE_RED_HIGHLIGHT, Color.FONT_BOLD_ITALIC));
    }
}
