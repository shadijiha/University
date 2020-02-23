/**
 * Magic happens here
 */

package com.shado.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

	private Formatter()	{

	}

	public static String parse(String raw, Object... args)	{
		Pattern pattern = Pattern.compile("\\{\\d}");
		Matcher match = pattern.matcher(raw);

		while (match.find())	{
			try {
				String temp = match.group();
				int index = Integer.parseInt(temp.replaceAll("\\{|}", ""));
				raw = raw.replaceFirst(String.valueOf(pattern), (String) args[index]);
			} catch (Exception e)	{
				break;
			}
		}

		return raw;
	}

}
