/**
 * This class is used to compress a string to indices massively reducing its size in memory
 *
 * @author: Shadi Jiha
 * @Date: 02 March 2021
 */
package com.libs;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.text.*;
import java.util.*;

public class CompressedString {

	public static final CompressedStringOptions DEFAULT_OPTIONS = new CompressedStringOptions("X0wlMkVWPJzAZ5U0QsGFqHgY16gQZ4Lh",
			"/home/shadi/Desktop/cloud/admin@example.com/code/common/string_compressor_index.txt",
			"http://shadijiha.ddns.net/shado-cloud/public/api");

	private final String cdnURL;
	private final String apiKey;
	private final String FilePathCDN;
	private final String fullCDNPath;

	private final String JOIN_DELIMITER = " ";

	private int[] indices;

	public CompressedString(String original) {
		this(original, DEFAULT_OPTIONS);
	}

	public CompressedString(String original, CompressedStringOptions options) {
		cdnURL = options.CDNUrl;
		apiKey = options.ApiKey;
		FilePathCDN = options.IndexFileCDNPath;
		fullCDNPath = cdnURL + "?key=" + apiKey + "&path=" + FilePathCDN;

		compress(original);
	}

	private CompressedString(CompressedStringOptions options) {
		cdnURL = options.CDNUrl;
		apiKey = options.ApiKey;
		FilePathCDN = options.IndexFileCDNPath;
		fullCDNPath = cdnURL + "?key=" + apiKey + "&path=" + FilePathCDN;
	}

	private CompressedString(int[] indices) {
		this(DEFAULT_OPTIONS);
		this.indices = indices;
	}

	/**
	 * Converts the indices into a normal string
	 *
	 * @return Returns a full string from the indices of the compressed string
	 */
	public String deCompress() {
		StringBuilder builder = new StringBuilder();

		for (var index : indices) {
			String str = indexToString(index);
			builder.append(str).append(JOIN_DELIMITER);
		}

		return builder.toString();
	}

	/**
	 * Dumps the compressed string to a Binary file
	 *
	 * @param filepath
	 */
	public void toFile(String filepath) {

		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filepath));

			for (int index : indices) {
				stream.writeInt(index);
			}
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toFile() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String strDate = dateFormat.format(date);

		toFile(strDate + ".sstr");
	}

	/**
	 * Reads a compressed string binary file and converts it to a memory object
	 *
	 * @param compressedFilepath The file path of the compressed string
	 * @return
	 */
	public static CompressedString fromFile(String compressedFilepath) {
		List<Integer> list = new ArrayList<>();

		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(compressedFilepath));

			int byteRead;
			while ((byteRead = stream.readInt()) != -1) {
				list.add(byteRead);
			}

			stream.close();

		} catch (EOFException ignored) {
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] array = new int[list.size()];
		int index = 0;
		for (int val : list) {
			array[index++] = val;
		}

		return new CompressedString(array);
	}

	/// <summary>
	///
	/// </summary>
	/// <param name="filepath"></param>
	private void compress(String original) {
		String[] tokens = original.split("\\s+");
		indices = new int[tokens.length];

		int i = 0;
		for (var token : tokens) {
			int index = stringToIndex(token);
			indices[i] = index;
			i++;
		}
	}

	/**
	 * ****************** HELPER FUNCTIONS **********************
	 */
	/**
	 * This function converts a word to unique index
	 *
	 * @param s The word to convert
	 * @return Returns the index of the word in a common file
	 */
	private int stringToIndex(String s) {

		s = s.replaceAll("[,.\\-\"\\[\\]{};:'?!]", "").trim();

		int counter = 0;

		try {
			URL oracle = new URL(fullCDNPath);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));

			String line;
			while ((line = in.readLine()) != null) {
				if (line.trim().equals(s.trim())) {
					return counter;
				}
				counter++;
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we are here that means that the word is not in the file,
		// So add it to the file
		String finalS = s;
		Map<String, String> arguments = new HashMap<String, String>() {{
			put("key", apiKey);
			put("path", FilePathCDN);
			put("append", "true");
			put("data", "!CMD_NEW_LINE!" + finalS);
		}};


		try {
			URL url = new URL(cdnURL);
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST"); // PUT is another valid option
			http.setDoOutput(true);

			StringJoiner sj = new StringJoiner("&");
			for (Map.Entry<String, String> entry : arguments.entrySet())
				sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
						+ URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
			byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			http.setFixedLengthStreamingMode(length);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(out);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counter;
	}

	/**
	 * This function converts a unique index to a string
	 *
	 * @param i The index to convert
	 * @return Returns the word that the index refers to
	 */
	private String indexToString(int i) {
		int counter = 0;

		try {
			URL oracle = new URL(fullCDNPath);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));

			String line;
			while ((line = in.readLine()) != null) {
				if (i == counter) {
					return line;
				}
				counter++;
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new RuntimeException(i + " was not found in the file");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (var index : indices) {
			builder.append(index).append(" ");
		}

		return builder.toString();
	}

	/**
	 * Computes if 2 compressed strings are equal
	 *
	 * @param obj The other string
	 * @return Returns true if the 2 strings have the same indices, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != getClass())
			return false;
		else {
			CompressedString str = (CompressedString) obj;
			if (indices.length != str.indices.length)
				return false;

			for (int i = 0; i < indices.length; i++) {
				if (indices[i] != str.indices[i])
					return false;
			}

			return true;
		}
	}

	public static class CompressedStringOptions {
		public String ApiKey;
		public String IndexFileCDNPath;
		public String CDNUrl;

		public CompressedStringOptions(String apiKey, String indexFileCDNPath, String CDNUrl) {
			ApiKey = apiKey;
			IndexFileCDNPath = indexFileCDNPath;
			this.CDNUrl = CDNUrl;
		}

		public CompressedStringOptions() {
		}

		public CompressedStringOptions setApiKey(String apiKey) {
			this.ApiKey = apiKey;
			return this;
		}

		public CompressedStringOptions setIndexFileCDNPath(String IndexFileCDNPath) {
			this.IndexFileCDNPath = IndexFileCDNPath;
			return this;
		}

		public CompressedStringOptions setCDNUrl(String CDNUrl) {
			this.CDNUrl = CDNUrl;
			return this;
		}
	}

}
