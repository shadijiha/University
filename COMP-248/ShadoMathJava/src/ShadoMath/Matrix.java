/**
 * 
 */
package ShadoMath;

import java.lang.reflect.Array;

/**
 * @author shadi
 *
 */
public class Matrix {

	private double[][] data;
	private int rows;
	private int cols;
	
	/**
	 * 
	 */
	public Matrix(double[][] array) {
		// TODO Auto-generated constructor stub
		this.data = array;
		this.rows = array.length;
		this.cols = array[0].length;
	}
	
	public Matrix(int[][] array) {
		for (int i = 0; i < array.length; i++)	{
			for(int j = 0; j < array[i].length; j++)	{
				this.data[i][j] = (double)array[i][j];
			}
		}
		this.rows = array.length;
		this.cols = array[0].length;
	}
	
	public Matrix(int rows, int cols)	{
		this.rows = rows;
		this.cols = cols;
		this.data = new double[rows][cols];
		this.randomize();
	}

	public void randomize(int scale)	{
		for (int i = 0; i < this.data.length; i++)	{
			for(int j = 0; j < this.data[i].length; j++)	{
				this.data[i][j] = Math.floor(Math.random() * scale);
			}
		}		
	}

	public void randomize()	{
		this.randomize(10);	
	}
	
	public void generateIdentity() throws Error	{
		if (this.rows == this.cols)	{
			for (int i = 0; i < this.data.length; i++)	{
				for(int j = 0; j < this.data[i].length; j++)	{
					if (i == j)
						this.data[i][j] = 1;
					else
						this.data[i][j] = 0;
				}
			}		
		} else	{
			throw new Error("Cannot build an identity matrix if rows and colonnes are not equal");
		}
	}
	
	public double determinant2D() throws Error	{
		if (this.rows == 2 && this.cols == 2)	{
			return this.data[0][0] * this.data[1][1] - this.data[0][1] * this.data[1][0];
		} else	{
			throw new Error("Cannot return the determiant of a non 2x2 matrix.");
		}
	}

	public Matrix add(final Matrix other) throws Error	{
		
		if (this.rows == other.rows && this.cols == other.cols)	{
			Matrix result = new Matrix(this.rows, this.cols);
			
			for (int i = 0; i < result.getRows(); i++)	{
				for (int j = 0; j < result.getCols(); j++)	{
					result.setData(i, j, this.data[i][j] + other.getData(i, j));
				}
			}
			
			return result;
			
		} else	{
			throw new Error("Cannot add 2 Matrix with diffrent rows and colonnes count.");
		}	
		
	}

	public Matrix multiply(double scale)	{
		Matrix result = new Matrix(this.rows, this.cols);
		
		for (int i = 0; i < result.getRows(); i++)	{
			for (int j = 0; j < result.getCols(); j++)	{
				result.setData(i, j, this.data[i][j] * scale);
			}
		}		
		
		return result;
	}

	public Matrix inverse()	throws Error	{

		throw new Error("inverse() is not working hehexd");
		
		/*if (this.is2D())	{
			return this.multiply( 1 / this.determinant2D());
		} else	{
			throw new Error("Cannot inverse a non 2x2 matrix");
		}*/
		
	}
	
	// Setters
	public void setData(int row, int col, double newData)	{
		try	{
			this.data[row][col] = newData;
		} catch(Exception e)	{
			System.out.println(e.getMessage());
		}
	}		
	
	// Getters
	public double getData(int row, int col)	{
		double result = 0.0;
		
		try	{
			result = this.data[row][col];
		} catch(Exception e)	{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int getRows()	{	return this.rows;}
	public int getCols()	{	return this.cols;}
	
	public boolean isSquare()	{
		return this.rows == this.cols;
	}
	
	public boolean is2D()	{
		return this.rows == 2 && this.cols == 2;
	}
		
	public void print()	{
		for (int i = 0; i < this.data.length; i++)	{
			for (int j = 0; j < this.data[i].length; j++)	{
				System.out.print(this.data[i][j] + "\t");
			}
			System.out.println();
		}		
	}

}
