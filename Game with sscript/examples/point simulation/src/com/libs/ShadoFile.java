/**
 * A Shado file is used to store keys and value. Example:
 * <p>
 * 1 |	// beginning of the file
 * 2 |	MainFontColor: black
 * 3 |	BackgroundColor: white
 * 4 |	#if 0
 * 5 |		This block will be ignored
 * 6 |	#endif
 * 7 |
 * 8 |	#if BackgroundColor == white
 * 9 |	#include "other.shado"
 * 10|	#endif
 * <p>
 * As you have noticed you can use preprocessor commands in shado file
 * The #if command will ignore a block if the condition is false
 * You can use a previously declared key, value in your condition
 * <p>
 * The #include command will copy and past another *.shado file
 * <p>
 * Lines starting with a // will be ignored
 */

package com.libs;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ShadoFile implements Iterable<Map.Entry<String, String>> {

	private final String rawContent;
	private final String filename;

	private static final char DEFAULT_SEPARATOR = ':';
	private char separator = ':';

	private final Map<String, String> keyValues;

	public ShadoFile(final String filename) {
		this.filename = filename;
		this.keyValues = new HashMap<String, String>();

		this.rawContent = readFile(this.filename);
		compile(this.rawContent);
	}

	/**
	 * @param key
	 * @return Returns true of the Shado file contains that key
	 */
	public boolean hasKey(String key) {
		return keyValues.containsKey(key);
	}

	/**
	 * @param value
	 * @return Returns true of the Shado file contains that value
	 */
	public boolean hasValue(String value) {
		return keyValues.containsValue(value);
	}

	public String getValue(String key) {
		return keyValues.get(key);
	}

	public Iterator<Map.Entry<String, String>> iterator() {
		return ((HashMap<String, String>) keyValues).entrySet().iterator();
	}

	public void toJSON(String filepath) {

		StringBuilder builder = new StringBuilder();

		builder.append("{\n");

		int counter = 0;
		for (Map.Entry<String, String> entry : keyValues.entrySet()) {
			builder.append("\t\"").append(entry.getKey()).append("\"").append(" ").append(": ").append("\"").append(entry.getValue().replaceAll("\\\\+", "\\\\\\\\"));

			if (counter >= keyValues.size() - 1)
				builder.append("\"\n");
			else
				builder.append("\",\n");

			counter++;
		}

		builder.append("}");

		// Write file
		try {

			PrintWriter writer = new PrintWriter(new FileOutputStream(filepath));

			writer.print(builder.toString());

			writer.close();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void toJSON() {
		toJSON(this.filename + ".json");
	}

	/**
	 * Inserts a key value pair to the Shado File
	 *
	 * @param key   The key to insert
	 * @param value The value to insert
	 */
	public void insert(String key, Object value) {

		try {
			keyValues.put(key, value.toString());
		} catch (Exception e) {
			return;
		}

		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(filename, true));

			// If it is not the default separator then add the #using directive
			if (separator != DEFAULT_SEPARATOR)
				writer.printf("\n#using separator '%c'", separator);

			writer.printf("\n%s %c %s", key, separator, value.toString());

			if (separator != DEFAULT_SEPARATOR)
				writer.printf("\n#using separator '%c'", DEFAULT_SEPARATOR);

			writer.close();
		} catch (Exception ignored) {
		}
	}

	public void update(String key, String newValue) {

		if (!hasKey(key))
			return;

		// Get which line is the result to update
		int lineToChange = 0;
		boolean found = false;
		List<String> buffer = new ArrayList<String>();

		try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
			String line;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				if (!(line.contains(key) && line.contains(keyValues.get(key))) && !found) {
					lineToChange++;
				} else {
					found = true;
				}

				buffer.add(line);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Write the updated result
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {

			for (int i = 0; i < buffer.size(); i++) {
				if (i == lineToChange) {
					var x = buffer.get(i).split("\\s+");
					x[x.length - 1] = x[x.length - 1].replaceAll(keyValues.get(key), newValue.toString());

					writer.println(String.join(" ", x));
				} else {
					writer.println(buffer.get(i));
				}
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		keyValues.replace(key, newValue);
	}

	private static String readFile(String filepath) {

		try {
			Scanner scanner = new Scanner(new FileInputStream(filepath));

			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine()).append("\n");
			}

			scanner.close();

			return builder.toString();

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	private void compile(String content) throws RuntimeException {

		List<String> lines = new ArrayList<String>(Arrays.asList(content.split("\n")));

		String SEPERATOR = ":";

		// This line number is used for iteration and is modified depending how the program is viewing the file
		int lineNumber = 0;
		// This line number is used for Error message only and is not modified (Reflects the accual number in the file)
		int originalLineNumber = 0;

		// Detect the type of the file
		for (int i = 0; i < lines.size(); i++) {
			String tempLine = lines.get(i).trim();
			if (tempLine.startsWith("//") || tempLine.equals("") || tempLine.equals("\n")) {
				continue;
			} else if (tempLine.startsWith("#type")) {
				String tempExpr = tempLine.replaceAll("\\s+", " ");
				if (!tempLine.equalsIgnoreCase("#type object file")) {
					throw new RuntimeException("Currently the compiler only supports Shado Object files \"#type object file\"");
				} else {
					lines.set(i, "");
					keyValues.put("__FILE_TYPE", "Shado object file");
					break;
				}
			} else {
				throw new RuntimeException("The first statement of Shado File must be its type \"#type SOMETHING\"");
			}
		}

		while (lineNumber < lines.size()) {

			// Remove comments from line
			String[] lineWithoutComment = lines.get(lineNumber).split("//");

			// If the line accually contained a comment, the lineWithoutComment variable length would be greater than 1
			String line = lineWithoutComment[0].trim();
			lines.set(lineNumber, line);

			// See if line is a comment or not
			if (!line.startsWith("//"))
				// Replace compiler tokens
				line = replaceCompilerTokens(line, originalLineNumber);
			else {
				lineNumber++;
				originalLineNumber++;
				continue;
			}

			if (line.startsWith("#")) {

				String[] expression = line.split("\\s+");

				if (expression[0].equalsIgnoreCase("#if")) {

					String newExpression = arrayToStringWithoutIndex(expression, 0, " ");    // Regroup expression without the #if
					boolean result = evaluateExpression(newExpression);

					// Remove the #if line
					lines.set(lineNumber, "");

					if (!result) {

						// In the future we can use level system for nested #if statements
						while (!lines.get(lineNumber).equalsIgnoreCase("#endif")) {
							lines.set(lineNumber, ""); // Ignore line
							lineNumber++;
						}

						lineNumber -= 2;
					}

				} else if (expression[0].equalsIgnoreCase("#include")) {

					String otherFileName = arrayToStringWithoutIndex(expression, 0, " ");    // Regroup expression without the #if
					String otherFileContent = readFile(otherFileName.replaceAll("\"", ""));
					String[] otherFileLines = otherFileContent.split("\n");

					// Remove the #include line
					lines.set(lineNumber, "");

					// Merge both files to compile them together (handy for recursive #include)
					int _start_line = lineNumber;
					for (String s : otherFileLines)
						lines.add(lineNumber++, s);

					// Add a defined key for this include file so the user can check if the file was included or not
					var __temp_1 = otherFileName.replaceAll("\"", "").trim().split("\\\\");
					String definedFileNameValue = __temp_1[__temp_1.length - 1].replaceAll("\\.", "_").replaceAll("\\s+", "").trim().toUpperCase();
					keyValues.put("__" + definedFileNameValue, "true");

					// Restart from the #include line
					lineNumber = _start_line;

				} else if (expression[0].equalsIgnoreCase("#endif")) {
					// Ignore
					// In the future should add a check to see if a #if was declared before #endif
					lines.set(lineNumber, "");

				} else if (expression[0].equalsIgnoreCase("#error")) {

					String newExpression = arrayToStringWithoutIndex(expression, 0, " ");    // Regroup expression without the #if
					throw new RuntimeException("Error thrown by the file! " + newExpression + " at line " + (originalLineNumber + 1));

				} else if (expression[0].equalsIgnoreCase("#stop")) {

					break;

				} else if (expression[0].equalsIgnoreCase("#using")) {

					// See what's the using is for
					if (expression[1].equals("separator")) {
						String newSeparator = expression[expression.length - 1].replaceAll("[\"']", "");
						newSeparator = newSeparator.equals("__DEFAULT_SEPARATOR__") ? DEFAULT_SEPARATOR + "" : newSeparator;

						SEPERATOR = newSeparator;
						this.separator = newSeparator.charAt(0);
					} else
						throw new RuntimeException("Invalid usage of the #using directive. #using directive cannot be alone");

				} else if (expression[0].equalsIgnoreCase("#type")) {
					throw new RuntimeException("The file type can be defined only once! \"" + expression[0] + "\" at line " + (originalLineNumber + 1));
				} else {
					throw new RuntimeException("Invalid Shado file preprocess command \"" + expression[0] + "\" at line " + (originalLineNumber + 1));
				}

			} else if (line.equals("") || line.equals("\n") || line.equals("\r")) {
				// Ignore
			} else {
				// Parse tokens
				try {
					String[] tokens = line.split(SEPERATOR, 2); //lines.get(lineNumber).split(SEPERATOR);
					tokens[0] = tokens[0].trim();
					tokens[1] = tokens[1].trim();

					// If the key or value start and ends with quotes "" remove them
					if (tokens[0].startsWith("\"") && tokens[0].endsWith("\""))
						tokens[0] = tokens[0].replaceAll("\"", "");

					// If the value or value start and ends with quotes "" remove them
					if (tokens[1].startsWith("\"") && tokens[1].endsWith("\""))
						tokens[1] = tokens[1].replaceAll("\"", "");

					// Replace specific compiler tokens like: __LINE__ with values
					//tokens[1] = replaceCompilerTokens(tokens[1], originalLineNumber);

					// Push the key value
					keyValues.put(tokens[0], tokens[1]);

				} catch (ArrayIndexOutOfBoundsException e) {
					throw new RuntimeException("Syntax error! Keys and values must be split by a colon \"" + SEPERATOR + "\".\n\t> " + line + "\nat line " + (originalLineNumber + 1));
				}
			}

			lineNumber++;
			originalLineNumber++;
		}
	}

	private boolean evaluateExpression(String expression) throws RuntimeException {

		expression = expression.trim();

		try {
			if (expression.contains("==")) {
				String[] tokens = expression.split("==");

				trimArray(tokens);

				return getValue(tokens[0]) != null && getValue(tokens[0]).equals(tokens[1]);
			} else if (expression.contains("!=")) {
				String[] tokens = expression.split("!=");

				trimArray(tokens);


				return getValue(tokens[0]) != null && !getValue(tokens[0]).equals(tokens[1]);
			} else if (expression.contains("undefined(")) {

				String[] tokens = expression.split("\\(");
				trimArray(tokens);

				String key = tokens[tokens.length - 1].replaceAll("\\)", "");

				return !hasKey(key);
			} else if (expression.contains("defined(")) {

				String[] tokens = expression.split("\\(");
				trimArray(tokens);

				String key = tokens[tokens.length - 1].replaceAll("\\)", "");

				return hasKey(key);
			} else if (expression.equalsIgnoreCase("0") || expression.equalsIgnoreCase("false")) {
				return false;
			} else {
				return true;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("Syntax error: Invalid expression " + expression);
		}
	}

	private String replaceCompilerTokens(String token, int lineNumber) {

		String result = token.trim();

		if (token.contains("__LINE__")) {
			result = result.replaceAll("__LINE__", (lineNumber + 1) + "");
		}

		if (token.contains("__FILE__")) {
			result = result.replaceAll("__FILE__", new File(this.filename).getAbsolutePath().replaceAll("\\\\+", "\\\\\\\\"));
		}

		if (token.contains("__DATE__")) {
			result = result.replaceAll("__DATE__", new Date().toString());
		}

		if (token.contains("__DIR__")) {
			result = result.replaceAll("__DIR__", new File("").getAbsolutePath().replaceAll("\\\\+", "\\\\\\\\"));
		}

		return result;

	}

	private String arrayToStringWithoutIndex(Object[] array, int indexToRemove, String delemitor) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i != indexToRemove)
				builder.append(array[i].toString()).append(delemitor);
		}

		return builder.toString();
	}

	private static Stream<Object> flatten(Object[] array) {
		return Arrays.stream(array)
				.flatMap(o -> o instanceof Object[] ? flatten((Object[]) o) : Stream.of(o));
	}

	private static void trimArray(String[] array) {
		for (int i = 0; i < array.length; i++)
			array[i] = array[i].trim();
	}
}
