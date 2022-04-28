package com.print.color;

public class ToColorString {
	private volatile static ToColorString toColorString;

	private ToColorString() {}

	public static ToColorString getInstance() {
		if(toColorString == null) {
			synchronized(ToColorString.class) {
				if(toColorString == null) {
					toColorString = new ToColorString();
				}
			}
		}
		return toColorString;
	}

	/**
	 * 黑色高亮加粗斜体
	 */
	public String valueOfHighlightBlackBoldItalic(String str) {
		return toColorString(str, Color.STYLE_BLACK_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 红色高亮加粗斜体
	 */
	public String valueOfHighlightRedBoldItalic(String str) {
		return toColorString(str, Color.STYLE_RED_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 绿色高亮加粗斜体
	 */
	public String valueOfHighlightGreenBoldItalic(String str) {
		return toColorString(str, Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 黄色高亮加粗斜体
	 */
	public String valueOfHighlightYellowBoldItalic(String str) {
		return toColorString(str, Color.STYLE_YELLOW_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 蓝色高亮加粗斜体
	 */
	public String valueOfHighlightBlueBoldItalic(String str) {
		return toColorString(str, Color.STYLE_BLUE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 紫色高亮加粗斜体
	 */
	public String valueOfHighlightPurpleBoldItalic(String str) {
		return toColorString(str, Color.STYLE_PURPLE_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 青色高亮加粗斜体
	 */
	public String valueOfHighlightCyanBoldItalic(String str) {
		return toColorString(str, Color.STYLE_CYAN_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	/**
	 * 灰色高亮加粗斜体
	 */
	public String valueOfHighlightGrayBoldItalic(String str) {
		return toColorString(str, Color.STYLE_GRAY_HIGHLIGHT, Color.FONT_BOLD_ITALIC);
	}

	public String toColorString(String str, int colorStyle) {
		return "\033[" + colorStyle + "m" + str + "\033[m";
	}

	public String toColorString(String str, int colorStyle, String fontStyle) {
		return "\033[" + fontStyle + colorStyle + "m" + str + "\033[m";
	}

	public String toColorString(String str, int colorStyle, int backgroundColor) {
		return "\033[" + backgroundColor  + ";" + colorStyle + "m" + str + "\033[m";
	}

	public String toColorString(String str, int colorStyle, String fontStyle, int backgroundColor) {
		return "\033[" + fontStyle + backgroundColor  + ";" + colorStyle + "m" + str + "\033[m";
	}
}
