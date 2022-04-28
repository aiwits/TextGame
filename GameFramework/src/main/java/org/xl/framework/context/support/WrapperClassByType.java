package org.xl.framework.context.support;

public final class WrapperClassByType {
	private WrapperClassByType() {}

	public static Object findWrapperClassByType(String value, Class<?> type) {
		Object arg = null;
		if(int.class.equals(type)) {
			arg = Integer.parseInt(value);
		} else if(short.class.equals(type)) {
			arg = Short.parseShort(value);
		} else if(long.class.equals(type)) {
			arg = Long.parseLong(value);
		} else if(float.class.equals(type)) {
			arg = Float.parseFloat(value);
		} else if(double.class.equals(type)) {
			arg = Double.parseDouble(value);
		} else if(boolean.class.equals(type)) {
			arg = Boolean.parseBoolean(value);
		} else if(byte.class.equals(type)) {
			arg = Byte.parseByte(value);
		} else if(char.class.equals(type)) {
			arg = value.charAt(0);
		} else {
			arg = value;
		}
		return arg;
	}
}
