package Lab8; /**
 *
 */

import java.io.*;

public class Main {

	public static void main(String... args) {


		Record[] records = new Record[10];

		for (int i = 0; i < records.length; i++) {
			records[i] = Record.random();
		}

		// Write objects to the binaty file
		for (int i = 0; i < records.length; i++)
			writeToBinary("firstFile", records[i], true);

		// Read files
		System.out.println("Now reading the written binary file");
		readFromBinaryFile("firstFile");

		// Modify records
		System.out.println("Now after modifying the objects:");
		for (int i = 0; i < records.length; i += 2) {
			records[i].randomize();
			System.out.println(records[i]);
		}

		System.out.println("Rewrite the objects to the binary file");
		for (int i = 0; i < records.length; i++)
			writeToBinary("firstFile", records[i], true);
	}

	public static void writeToBinary(String filename, Object obj, boolean append) {
		File file = new File(filename);
		ObjectOutputStream out = null;

		try {
			if (!file.exists() || !append) out = new ObjectOutputStream(new FileOutputStream(filename));
			else out = new AppendableObjectOutputStream(new FileOutputStream(filename, append));
			out.writeObject(obj);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void readFromBinaryFile(String filename) {
		File file = new File(filename);

		if (file.exists()) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(filename));
				while (true) {
					Record s = (Record) ois.readObject();
					System.out.println(s);
				}
			} catch (EOFException e) {

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ois != null) ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class AppendableObjectOutputStream extends ObjectOutputStream {
		public AppendableObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
		}
	}
}
