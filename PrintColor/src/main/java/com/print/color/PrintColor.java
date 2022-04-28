package com.print.color;

import java.io.PrintStream;
import java.util.Locale;

public class PrintColor {
	private final PrintStream print = new PrintStream(System.out);
	public static final PrintColor out;

	private PrintColor() {}

	static {
		out = new PrintColor();
	}

	public void print(int i, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(i);
		print.print("\033[m");
	}

	public void println(int i, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(i);
		print.println("\033[m");
	}

	public void print(int i, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(i);
		print.print("\033[m");
	}

	public void println(int i, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(i);
		print.println("\033[m");
	}

	public void print(int i, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(i);
		print.print("\033[m");
	}

	public void println(int i, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(i);
		print.println("\033[m");
	}

	public void print(int i, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(i);
		print.print("\033[m");
	}

	public void println(int i, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(i);
		print.println("\033[m");
	}

	public void print(long l, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(l);
		print.print("\033[m");
	}

	public void println(long l, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(l);
		print.println("\033[m");
	}

	public void print(long l, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(l);
		print.print("\033[m");
	}

	public void println(long l, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(l);
		print.println("\033[m");
	}

	public void print(long l, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(l);
		print.print("\033[m");
	}

	public void println(long l, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(l);
		print.println("\033[m");
	}

	public void print(long l, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(l);
		print.print("\033[m");
	}

	public void println(long l, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(l);
		print.println("\033[m");
	}

	public void print(char c, int colorStyle) {
		String color = colorStyle + "m";
		print.print("\033[" + color);
		print.print(c);
		print.print("\033[m");
	}

	public void println(char c, int colorStyle) {
		String color = colorStyle + "m";
		print.print("\033[" + color);
		print.print(c);
		print.println("\033[m");
	}

	public void print(char c, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(c);
		print.print("\033[m");
	}

	public void println(char c, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(c);
		print.println("\033[m");
	}

	public void print(char c, int colorStyle, String fontStyle) {
		String color = colorStyle + "m";
		print.print("\033[" + fontStyle + color);
		print.print(c);
		print.print("\033[m");
	}

	public void println(char c, int colorStyle, String fontStyle) {
		String color = colorStyle + "m";
		print.print("\033[" + fontStyle + color);
		print.print(c);
		print.println("\033[m");
	}

	public void print(char c, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(c);
		print.print("\033[m");
	}

	public void println(char c, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(c);
		print.println("\033[m");
	}

	public void print(float f, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(f);
		print.print("\033[m");
	}

	public void println(float f, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(f);
		print.println("\033[m");
	}

	public void print(float f, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(f);
		print.print("\033[m");
	}

	public void println(float f, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(f);
		print.println("\033[m");
	}

	public void print(float f, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(f);
		print.print("\033[m");
	}

	public void println(float f, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(f);
		print.println("\033[m");
	}

	public void print(float f, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(f);
		print.print("\033[m");
	}

	public void println(float f, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(f);
		print.println("\033[m");
	}

	public void print(double d, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(d);
		print.print("\033[m");
	}

	public void println(double d, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(d);
		print.println("\033[m");
	}

	public void print(double d, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(d);
		print.print("\033[m");
	}

	public void println(double d, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(d);
		print.println("\033[m");
	}

	public void print(double d, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(d);
		print.print("\033[m");
	}

	public void println(double d, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(d);
		print.println("\033[m");
	}

	public void print(double d, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(d);
		print.print("\033[m");
	}

	public void println(double d, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(d);
		print.println("\033[m");
	}

	public void print(char[] str, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(str);
		print.print("\033[m");
	}

	public void println(char[] str, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(str);
		print.println("\033[m");
	}

	public void print(char[] str, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(str);
		print.print("\033[m");
	}

	public void println(char[] str, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(str);
		print.println("\033[m");
	}

	public void print(char[] str, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(str);
		print.print("\033[m");
	}

	public void println(char[] str, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(str);
		print.println("\033[m");
	}

	public void print(char[] str, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(str);
		print.print("\033[m");
	}

	public void println(char[] str, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(str);
		print.println("\033[m");
	}

	public void print(boolean b, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(b);
		print.print("\033[m");
	}

	public void println(boolean b, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(b);
		print.println("\033[m");
	}

	public void print(boolean b, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(b);
		print.print("\033[m");
	}

	public void println(boolean b, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(b);
		print.println("\033[m");
	}

	public void print(boolean b, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(b);
		print.print("\033[m");
	}

	public void println(boolean b, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(b);
		print.println("\033[m");
	}

	public void print(boolean b, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(b);
		print.print("\033[m");
	}

	public void println(boolean b, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(b);
		print.println("\033[m");
	}

	public void print(Object o, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(o);
		print.print("\033[m");
	}

	public void println(Object o, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(o);
		print.println("\033[m");
	}

	public void print(Object o, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(o);
		print.print("\033[m");
	}

	public void println(Object o, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(o);
		print.println("\033[m");
	}

	public void print(Object o, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(o);
		print.print("\033[m");
	}

	public void println(Object o, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(o);
		print.println("\033[m");
	}

	public void print(Object o, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(o);
		print.print("\033[m");
	}

	public void println(Object o, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(o);
		print.println("\033[m");
	}

	public void println() {
		print.println();
	}

	public void print(String str) {
		print.print(str);
	}

	public void println(String str) {
		print.println(str);
	}

	public void print(String str, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(str);
		print.print("\033[m");
	}

	public void printf(String str, int colorStyle, Object... args) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.printf(str, args);
		print.print("\033[m");
	}

	public void printf(Locale l, String str, int colorStyle, Object... args) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.printf(l, str, args);
		print.print("\033[m");
	}

	public void println(String str, int colorStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + c);
		print.print(str);
		print.println("\033[m");
	}

	public void print(String str, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(str);
		print.print("\033[m");
	}

	/*public void printf(String str, int colorStyle, int backgroundColor, Object... args) {
		String background = ";" + backgroundColor + "m";
		out.print("\033[" + colorStyle + background);
		out.printf(str, args);
		out.print("\033[m");
	}

	public void printf(Locale l, String str, int colorStyle, int backgroundColor, Object... args) {
		String background = ";" + backgroundColor + "m";
		out.print("\033[" + colorStyle + background);
		out.printf(l, str, args);
		out.print("\033[m");
	}*/

	public void println(String str, int colorStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + colorStyle + background);
		print.print(str);
		print.println("\033[m");
	}

	public void print(String str, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(str);
		print.print("\033[m");
	}

	/*public void printf(String str, int colorStyle, String fontStyle, Object... args) {
		String c = colorStyle + "m";
		out.print("\033[" + fontStyle + c);
		out.printf(str, args);
		out.print("\033[m");
	}

	public void printf(Locale l, String str, int colorStyle, String fontStyle, Object... args) {
		String c = colorStyle + "m";
		out.print("\033[" + fontStyle + c);
		out.printf(l, str, args);
		out.print("\033[m");
	}*/

	public void println(String str, int colorStyle, String fontStyle) {
		String c = colorStyle + "m";
		print.print("\033[" + fontStyle + c);
		print.print(str);
		print.println("\033[m");
	}

	public void print(String str, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(str);
		print.print("\033[m");
	}

	/*public void printf(String str, int colorStyle, String fontStyle, int backgroundColor, Object... args) {
		String background = ";" + backgroundColor + "m";
		out.print("\033[" + fontStyle + colorStyle + background);
		out.printf(str, args);
		out.print("\033[m");
	}

	public void printf(Locale l, String str, int colorStyle, String fontStyle, int backgroundColor, Object... args) {
		String background = ";" + backgroundColor + "m";
		out.print("\033[" + fontStyle + colorStyle + background);
		out.printf(l, str, args);
		out.print("\033[m");
	}*/

	public void println(String str, int colorStyle, String fontStyle, int backgroundColor) {
		String background = ";" + backgroundColor + "m";
		print.print("\033[" + fontStyle + colorStyle + background);
		print.print(str);
		print.println("\033[m");
	}

	/**
	 * 以黑色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightBlackBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_BLACK_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以黑色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightBlackBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_BLACK_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以红色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightRedBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_RED_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以红色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightRedBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_RED_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以绿色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightGreenBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以绿色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightGreenBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以黄色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightYellowBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_YELLOW_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以黄色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightYellowBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_YELLOW_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以蓝色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightBlueBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_BLUE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以蓝色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightBlueBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_BLUE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以紫色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightPurpleBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_PURPLE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以紫色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightPurpleBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_PURPLE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以青色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightCyanBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_CYAN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以青色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightCyanBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_CYAN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以灰色高亮加粗斜体打印文本，加换行
	 */
	public void printlnHighlightGrayBoldItalic(String str) {
		PrintColor.out.println(str, Color.STYLE_GRAY_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 以灰色高亮加粗斜体打印文本，不换行
	 */
	public void printHighlightGrayBoldItalic(String str) {
		PrintColor.out.print(str, Color.STYLE_GRAY_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}
}
