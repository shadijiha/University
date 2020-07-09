package prof.comp346pa1s2020;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author Kerly Titus
 */
public class comp546pa1driver {

	/**
	 * main class
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {

		/*******************************************************************************************************************************************
		 * TODO : implement all the operations of main class   																					*
		 ******************************************************************************************************************************************/
		Network objNetwork = new Network("network");            /* Activate the network */
		objNetwork.start();
		Server objServer = new Server();                        /* Start the server */
		objServer.start();
		Client objClient1 = new Client("sending");              /* Start the sending client */
		objClient1.start();
		Client objClient2 = new Client("receiving");            /* Start the receiving client */
		objClient2.start();

		// Make the output without the debug
		List<String> file_with_debug_lines = fileAsString("output_with_debug.txt");

		var file_no_debug_lines = file_with_debug_lines.stream()
				.filter(line -> !line.contains("DEBUG : "))
				.filter(line -> !line.equals(""))
				.filter(line -> !line.equals("\n"))
				.collect(Collectors.toList());

		var str = listToString(file_no_debug_lines);

		writeToFile("output_without_debug.txt", str, false);
	}

	public static List<String> fileAsString(String filename) {

		List<String> lines = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));

			while (scanner.hasNextLine())
				lines.add(scanner.nextLine());

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static void writeToFile(String filename, Object data, boolean append) {

		try {

			PrintWriter writer = new PrintWriter(new FileOutputStream(filename, append));

			writer.println(data.toString());

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String listToString(List<?> list) {
		StringBuilder builder = new StringBuilder();

		for (var e : list)
			builder.append(e.toString()).append("\n");

		return builder.toString();
	}
}
