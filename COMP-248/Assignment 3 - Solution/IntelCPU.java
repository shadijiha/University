import java.text.DecimalFormat;



/**
Program to demonstrate a simple class.  
See A3 Handout for full description
Author: M. Mannan.
E-mail Address: m.mannan@concordia.ca
Last Changed: October 4, 2019.

https://ark.intel.com/content/www/us/en/ark.html

*/


public class IntelCPU
{
	
	public static void main(String[] args)
    {
		System.out.println("\nWelcome to the simple class example!\n");
		
		CPU cpu1 = new CPU(); //6, "i7", 623, 2.8, "Q1'16", true);
		CPU cpu2 = new CPU(10, "i9", 449, 3.1, "Q2'19", true);
		
		System.out.println("CPU 1: " + cpu1);
		System.out.println("CPU 2: " + cpu2);
		
		System.out.println("CPU 1 Series: " + cpu1.getSeries());
		System.out.println("CPU 1 Suggested price: " + new DecimalFormat("0.00").format(cpu1.getSuggestedPrice()) + " USD");
		cpu1.setSuggestedPrice(110);
		System.out.println("CPU 1 Suggested price (after mutator call): " + new DecimalFormat("0.00").format(cpu1.getSuggestedPrice()) + " USD");
		System.out.println("Are CPU 1 and CPU 2 equal? " + (cpu1.equals(cpu2) ? "YES" : "NO"));
		System.out.println("Is CPU 1 of higher generation than CPU 2? " + (cpu1.isHigerGeneration(cpu2) ? "YES" : "NO"));
		
		String sNow = "Q3'19";
		System.out.println("CPU 1 Price at " + sNow + " :" + new DecimalFormat("0.00").format(cpu1.priceNow(sNow)) + " USD");
		System.out.println("CPU 2 Price at " + sNow + " :" + new DecimalFormat("0.00").format(cpu2.priceNow(sNow)) + " USD");
		
		
		System.out.println("\nThank you for testing the simple class example!\n");

    }
}


// definition of the CPU class
class CPU
{
	private int generation;
	private String series;
	private double suggestedPrice;
	private double speed;
	private String launchDate;
	private boolean sgx;
	
	// Constructor - default
	public CPU() {
		this.generation = 1;
		this.series = "i3";
		this.launchDate = "Q1'10";
		this.suggestedPrice = 117;
		this.speed = 2.93;
		this.sgx = false;
	}
	
	// Constructor - with all params	
	public CPU(int generation, String series, double suggestedPrice, double speed, String launchDate, boolean sgx) {
		this.generation = generation;
		this.series = series;
		this.launchDate = launchDate;
		this.suggestedPrice = suggestedPrice;
		this.speed = speed;
		this.sgx = sgx;
	}
	
	// accessor methods
	public int getGeneration()
	{
		return this.generation;
	}
	
	public String getSeries()
	{
		return this.series;
	}
	
	public double getSuggestedPrice()
	{
		return this.suggestedPrice;
	}
	
	public String getLaunchDate()
	{
		return this.launchDate;
	}
	
	public boolean getSgx()
	{
		return this.sgx;
	}
	
	public double getSpeed()
	{
		return this.speed;
	}
	
	//set the new suggested price
	public void setSuggestedPrice(double newPrice)
	{
		this.suggestedPrice = newPrice;
	}
	
	
	//toString method
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
	    String sPrice = df.format(this.suggestedPrice);
	    
		return "This CPU is " + this.generation + "th generation " + this.series + " (" + this.speed + "GHz), launched: " + 
					this.launchDate + " with price: " + sPrice + " USD." +
					" SGX is " + (this.sgx ? "supported." : "not supported.");
	}
	
	// check for CPU generations
	public boolean isHigerGeneration(CPU cpu)
	{
		return this.generation - cpu.generation > 0 ? true : false;
	}
	
	// checks equality based on CPU generation, series, and the SGX feature.
	public boolean equals(CPU cpu)
	{
		return (this.generation == cpu.generation) && (this.sgx == cpu.sgx) && this.series.equalsIgnoreCase(cpu.series);
	}
	
	// method to calculate the current price with depreciation
	public double priceNow(String sDate)
	{
		String[] arr = sDate.split("'");
		String[] arr2 = arr[0].split("Q");
		
		int quarter1 = Integer.parseInt(arr2[1]);
		int year1 = Integer.parseInt(arr[1]);
		
		arr = this.launchDate.split("'");
		int quarter2 = Integer.parseInt(arr[0].split("Q")[1]);
		int year2 = Integer.parseInt(arr[1]);
		
		
		int diff = 4 * (year1 - year2) + quarter1 - quarter2;
		
		// if the given date is before the launch date, return the suggested price 
		if (diff < 0)
			return this.suggestedPrice;
		
		double PRICE_REDUCTION_RATE = 0.02;
		double price = this.suggestedPrice - (this.suggestedPrice * PRICE_REDUCTION_RATE * diff);
		
		// the returned price is always >= 0
		return price < 0 ? 0:price;
	}
}
       


