package driver;

import java.io.*;
import java.util.*;

public final class FileUtil {

	private FileUtil()	{}

	public static String fileAsString(String filepath)	{

		StringBuilder builder = new StringBuilder();

		try {
			Scanner scanner = new Scanner(new FileInputStream(filepath));

			while (scanner.hasNextLine())
				builder.append(scanner.nextLine()).append('\n');

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static String[] fileAsLines(String filepath)	{
		return fileAsString(filepath).split("\n");
	}

	public static void writeToFile(String filepath, Object data, boolean append)	{

		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(filepath, append));

			writer.print(data.toString());

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String filepath, Object data)	{
		writeToFile(filepath, data, false);
	}

	public static File asFile(String path)	{
		return new File(path);
	}
}
