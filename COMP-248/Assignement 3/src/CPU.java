
/***
 * 
 * Main file for CPU class
 * 
 * CLASS CPU
 * @constructor (NULL) : returns CPU with default values to variables
 * @constructor (args[6]):	@param _gen: expects a integer from 1 to 10
 * 							@param _series: expects a String, either "i3", "i5", "i7" or "i9"
 * 							@param _price: expects an unsigned double 
 * 							@param _speed: expects an unsigned double
 * 							@param _launch: expects a String of the form "XQ'XX" where X is any integer
 * 							@param _sgxSupport: expects a boolean
 * */

public class CPU {
	
	private int generation;
	private String series;
	private double price;
	private double speed;
	private String launchDate;
	private boolean sgxSupport;
	
	CPU()	{
		generation = 1;
		series = "i3";
		price = 117.0;
		speed = 2.93;
		launchDate = "Q1'10";
		sgxSupport = false;
	}
	
	CPU(int _gen, String _series, double _price, double _speed, String _launch, boolean _sgxSupport)	{
		generation = _gen;
		series = _series;
		price = _price;
		speed = _speed;
		launchDate = _launch;
		sgxSupport = _sgxSupport;
	}
	
	int getGeneration()	{	return generation;}
	
	String getSeries()	{	return series;}
	
	double getPrice()	{	return price;}
	
	double getSpeed()	{	return speed;}
	
	String getLaunchDate()	{	return launchDate;}
	
	boolean getSGXSupport()	{	return sgxSupport;}
	
	void setPrice(double newPrice)	{
		price = newPrice;
	}
	
	/**
	 * FUNCTION parseQuarter
	 * @param quarterYear: expects a String date of the following form "XQ'XX" where X is any integer
	 * @returns Returns a integer array where array[0] is the Quarter and array[1] is the year
	 * */
	private int[] parseQuarter(String quarterYear)	{
		String[] temp = quarterYear.split("'");
		int quarter = Integer.parseInt(temp[0].split("")[1]);
		int year = Integer.parseInt(temp[1]);
		
		int[] result = {quarter, year};
		
		return result;
	}
	
	/**
	 * FUNCTION priceNow
	 * @param sQuarterYear: expects a String date of the following form "XQ'XX" where X is any integer
	 * @returns Return a double price depending on the difference between the lanchDate and the passedDate
	 * */
	double priceNow(String sQuarterYear)	{
		
		int[] passedDate = parseQuarter(sQuarterYear);
		int[] initialDate = parseQuarter(launchDate);
		double currentPrice = 0.0;
		
		if (passedDate[1] < initialDate[1])	{
			return getPrice();
		}
		
		// find the difference between the years the multiply by 4 because 1 year = 4 quater
		// Then add the difference between both dates quarters
		int quarterElapsed = (passedDate[1] - initialDate[1]) * 4 + (passedDate[0] - initialDate[0]);
		
		currentPrice = price * (1 - (quarterElapsed * 0.02));
		
		if (currentPrice < 0.0)	{
			currentPrice = 0.0;
		}
		
		return currentPrice;			
	}
	
	String hehexd()	{
		return String.format("This CPU is %dth generation %s (%1.2fGHz), launched: %s with price: %1.2f USD. SGX is %s.", generation, series, speed, launchDate, price, sgxSupport ? "supported": "not supported");
	}
	
	boolean equals(CPU other)	{
		return (generation == other.generation && series.equalsIgnoreCase(other.series) && sgxSupport == other.sgxSupport);
	}
	
	boolean isHigherGeneration(CPU other)	{
		return (generation > other.generation);
	}
}
