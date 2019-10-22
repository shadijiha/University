


public class ShadoMath {
	
	public static float power(float base, int exposant)	{
		
		float sum = 1.0f;
		
		for (int i = 0; i < exposant; i++)	{
			sum *= base;			
		}
		
		return sum;
	}

}
