package com.main;

import com.main.algorithms.SJF;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int numberOfCpusToUse = 1;

	public static void main(String[] args) {
		// write your code here
		var processes = readInput("input.txt");

		var cpus = new CPU[numberOfCpusToUse];
		for (int i = 0; i < cpus.length; i++)
			cpus[i] = new CPU();

		var dispatcher = new Dispatcher(cpus, new SJF(cpus));
		dispatcher.submit(processes);
		dispatcher.start();
	}

	public static ShadoProcess[] readInput(String path) {

		List<ShadoProcess> result = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(new FileInputStream(path));

			while (scanner.hasNextLine()) {

				var line = scanner.nextLine();

				// the number of cpus to use
				if (line.contains(":")) {
					String tokens[] = line.split(":");
					numberOfCpusToUse = Integer.parseInt(tokens[1].trim());
					continue;
				}

				// Ignore comments and empty lines
				if (line.startsWith("//") || line.equals(""))
					continue;

				// Otherwise split the line by white space
				String tokens[] = line.split("\\s");

				// Create the process
				ShadoProcess process = new ShadoProcess(tokens[0],
						Long.parseLong(tokens[1]),
						Long.parseLong(tokens[2]));

				// Add all IO request times if they exist
				if (tokens.length > 3) {
					for (int i = 3; i < tokens.length; i++)
						process.addIORequest(Long.parseLong(tokens[i]));
				}

				// Add the process to the fetched process
				result.add(process);
			}

			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toArray(ShadoProcess[]::new);
	}
}
