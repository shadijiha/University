import com.engin.logger.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 */

public abstract class Env {

	private static final Map<String, String> map = new HashMap<>();

	static {
		launch();
	}

	private static void launch() {
		// Read .env file
		String line = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(".env"));

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String tokens[] = line.split("=");
				map.put(tokens[0], tokens[1]);
			}

		} catch (FileNotFoundException e) {
			Log.error(e);
		} catch (ArrayIndexOutOfBoundsException e) {
			Log.error("Error parsing .env file at line --> %s aborting", line);
		}
	}

	public static String get(String var) {
		return map.get(var);
	}
}
