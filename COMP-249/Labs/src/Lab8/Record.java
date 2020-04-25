package Lab8; /**
 *
 */

import java.io.*;

public class Record implements Serializable {
	public static final long serialVersionUID = 4L;
	public static final String[] names = {"Winston", "Jean", "Frost", "Jakal", "Tony", "Kevin"};

	private String artist;
	private String title;
	private int year;
	private double price;
	private int id;

	public Record(String artist, String title, int year, double price) {
		this.artist = artist;
		this.title = title;
		this.year = year;
		this.price = price;
		this.id = (int) (Math.random() * Integer.MAX_VALUE);
	}

	@Override
	public String toString() {
		return "Lab8.Record{" +
				"artist='" + artist + '\'' +
				", title='" + title + '\'' +
				", year=" + year +
				", price=" + price +
				"$ , id=" + id +
				'}';
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void randomize() {
		this.artist = randomName();
		this.title = "Song " + randomNumber(0, 50);
		this.year = randomNumber(2000, 2020);
		this.price = randomNumber(10, 100);
	}

	public static Record random() {
		return new Record(randomName(), "Song " + randomNumber(0, 50), randomNumber(2000, 2020), randomNumber(10, 100));
	}

	public static String randomName() {
		return Record.names[randomNumber(0, Record.names.length - 1)];
	}

	public static int randomNumber(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

}
