/**
 *
 */
package com.engin;

import com.engin.logger.*;

import java.awt.*;
import java.text.*;
import java.util.*;

public abstract class Util {

	private Util() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Generates a random number between a range
	 * @param min The minimum number
	 * @param max The maximum number it can take
	 * @return Returns the generated number
	 */
	public static double random(double min, double max) {
		return Math.random() * (max - min) + min;
	}

	/**
	 * Generates a random number between a range
	 * @param min The minimum number
	 * @param max The maximum number it can take
	 * @return Returns the generated number
	 */
	public static int random(int min, int max) {
		return (int) random((double) min, (double) max);
	}

	/**
	 * Generates a random string
	 * @param chars The number of characters
	 * @param specialChars if you want special chars and numbers in your string
	 * @return Returns the generated string
	 */
	public static String randomString(int chars, boolean specialChars) {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		String special = "1234567890!@#$%^&*(){}[];'/.,`";
		String complete = specialChars ? abc + special : abc;

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < chars; i++) {
			boolean capital = random(0.0f, 1.0f) >= 0.5;
			String c = String.valueOf(complete.charAt(random(0, complete.length())));

			if (capital)
				result.append(c.toUpperCase());
			else
				result.append(c.toLowerCase());
		}

		return result.toString();
	}

	/**
	 * Generates a random string with letters only
	 * @param chars The number of characters
	 * @return Returns the generated string
	 */
	public static String randomString(int chars) {
		return randomString(chars, false);
	}

	/**
	 * @return Returns a randomly generated color
	 */
	public static Color randomColor() {
		int red = random(0, 255);
		int blue = random(0, 255);
		int green = random(0, 255);

		return new Color(red, green, blue);
	}

	/**
	 * Generates a random color close to the color passed as parameter
	 * @param c The color to generate close to
	 * @return Returns the generated color
	 */
	public static Color randomColor(Color c) {

		int red = (c.getRed() + random(-20, 20)) % 255;
		int green = (c.getGreen() + random(-20, 20)) % 255;
		int blue = (c.getBlue() + random(-20, 20)) % 255;

		if (red < 0)
			red = 0;

		if (green < 0)
			green = 0;

		if (blue < 0)
			blue = 0;

		return new Color(red, green, blue);
	}

	public static String dateAsString(String format) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String dateAsString() {
		return dateAsString("yyyy-MM-dd hh-mm-ss");
	}

	public static TimeoutObject setTimeout(Runnable func, long delayMilli) {

		Thread timeout = new Thread(() -> {
			try {
				Thread.sleep(delayMilli);
			} catch (InterruptedException ignored) {
				return;
			}

			func.run();
		});
		timeout.start();

		return new TimeoutObject() {
			final Thread thread = timeout;
			final Runnable task = func;

			@Override
			public void cancel() {
				thread.interrupt();
			}

			@Override
			public void skipDelay() {
				thread.interrupt();
				task.run();
			}
		};
	}

	public static IntervalObject setInterval(Runnable func, long intervalMilli) {

		Thread interval = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(intervalMilli);
				} catch (InterruptedException ignored) {
					break;
				}

				func.run();
			}
		});
		interval.start();

		return new IntervalObject() {
			final Thread thread = interval;
			final Runnable task = func;

			@Override
			public void cancel() {
				thread.interrupt();
			}
		};

	}

	public static void sleep(long milli) {
		try {
			Thread.sleep(milli);
		} catch (Exception e) {
			Log.error(e);
		}
	}

	interface TimeoutObject {
		void skipDelay();

		void cancel();
	}

	interface IntervalObject {
		void cancel();
	}
}
