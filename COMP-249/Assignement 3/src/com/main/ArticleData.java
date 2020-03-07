/**
 *
 */
package com.main;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadi
 *
 */
public class ArticleData {

	private String id;
	private String author;
	private String journal;
	private String title;
	private int year;
	private String volume;
	private int number;
	private String pages;
	private List<String> keywords;
	private String doi;
	private String ISSN;
	private String month;

	/**
	 *
	 */
	private ArticleData(String id, String author, String journal, String title, int year, String volume, int number,
						String pages, List<String> keywords, String doi, String ISSN, String month) {
		super();
		this.id = id;
		this.author = author;
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.keywords = keywords;
		this.doi = doi;
		this.ISSN = ISSN;
		this.month = month;
	}

	public boolean isNull() {
		return id == null && author == null && journal == null && title == null;
	}

	public static class ArticleDataBuilder {

		private String b_id;
		private String b_author;
		private String b_journal;
		private String b_title;
		private int b_year;
		private String b_volume;
		private int b_number;
		private String b_pages;
		private List<String> b_keywords;
		private String b_doi;
		private String b_ISSN;
		private String b_month;

		public ArticleDataBuilder() {
		}

		public ArticleDataBuilder id(String id) {
			b_id = id;
			return this;
		}

		public ArticleDataBuilder author(String author) {
			b_author = author;
			return this;
		}

		public ArticleDataBuilder journal(String journal) {
			b_journal = journal;
			return this;
		}

		public ArticleDataBuilder title(String title) {
			b_title = title;
			return this;
		}

		public ArticleDataBuilder year(int year) {
			b_year = year;
			return this;
		}

		public ArticleDataBuilder volume(String volume) {
			b_volume = volume;
			return this;
		}

		public ArticleDataBuilder number(int number) {
			b_number = number;
			return this;
		}

		public ArticleDataBuilder pages(String pages) {
			b_pages = pages;
			return this;
		}

		public ArticleDataBuilder keywords(List<String> keywords) {
			b_keywords = keywords;
			return this;
		}

		public ArticleDataBuilder keywords(String[] keywords) {
			b_keywords = Arrays.asList(keywords);
			return this;
		}

		public ArticleDataBuilder addKeyword(String keyword) {
			b_keywords.add(keyword);
			return this;
		}

		public ArticleDataBuilder doi(String doi) {
			b_doi = doi;
			return this;
		}

		public ArticleDataBuilder ISSN(String ISSN) {
			b_ISSN = ISSN;
			return this;
		}

		public ArticleDataBuilder month(String month) {
			b_month = month;
			return this;
		}

		public ArticleData build() {
			return new ArticleData(b_id, b_author, b_journal, b_title, b_year, b_volume, b_number, b_pages, b_keywords,
					b_doi, b_ISSN, b_month);
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the journal
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the pages
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * @return the keywords
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	/**
	 * @return the doi
	 */
	public String getDoi() {
		return doi;
	}

	/**
	 * @return the iSSN
	 */
	public String getISSN() {
		return ISSN;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
}
