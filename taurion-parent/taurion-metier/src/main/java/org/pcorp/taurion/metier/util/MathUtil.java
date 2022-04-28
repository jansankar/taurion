package org.pcorp.taurion.metier.util;

public class MathUtil {

	public static float atan(float y, float x) {
		return (float) Math.atan(y / x);
	}

	public static float cos(float a) {
		return (float) Math.cos(a);
	}

	public static float sin(float a) {
		return (float) Math.sin(a);
	}

	public static float hypo(float a, float b) {
		return (float) Math.sqrt(a * a + b * b);
	}

	public static float hypo(float a, float b, float c) {
		return (float) Math.sqrt(a * a + b * b + c * c);
	}

	public static float round(float a, int precision) {
		float ap = a * (float) Math.pow(10, precision);
		return Math.round(ap) / (float) Math.pow(10, precision);
	}

	public static float round(double a, int precision) {
		float ap = (float) a * (float) Math.pow(10, precision);
		return Math.round(ap) / (float) Math.pow(10, precision);
	}

	public static float round3(float x) {
		return Math.round(x * 1000) / 1000f;
	}
}
