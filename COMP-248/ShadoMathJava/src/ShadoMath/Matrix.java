/**
 * 
 */
package ShadoMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shadi Jiha
 *
 */
public class Matrix {

	private Double[][] data;
	private int rows;
	private int cols;
	
	/**
	 * @param {Double[][]} array: Takes a 2D array and push the data
	 * to this.data. All colonnes must have the same size.
	 */
	public Matrix(Double[][] array) {
		// TODO Auto-generated constructor stub
		this.data = array;
		this.rows = array.length;
		this.cols = array[0].length;
	}
	
	/**
	 * @param {Integer[][]} array: Takes a 2D array and push the data
	 * to this.data. All colonnes must have the same size.
	 */
	public Matrix(Integer[][] array) {
		this.rows = array.length;
		this.cols = this.getBiggestCol(array);
		
		this.data = new Double[this.rows][this.cols];
		for (int i = 0; i < array.length; i++)	{
			for(int j = 0; j < array[i].length; j++)	{
				this.data[i][j] = (double)array[i][j];
			}
		}
	}
	
	public Matrix(int rows, int cols)	{
		this.rows = rows;
		this.cols = cols;
		this.data = new Double[rows][cols];
		this.randomize();
	}

	public Matrix(final Matrix other)	{
		
		// Copy matrix
		this(other.getRows(), other.getCols());
		
		for (int i = 0; i < this.rows; i++)	{
			for (int j = 0; j < this.cols; j++)	{
				this.setData(i, j, other.getData(i, j));
			}
		}
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
						this.data[i][j] = 1.0;
					else
						this.data[i][j] = 0.0;
				}
			}		
		} else	{
			throw new Error("Cannot build an identity matrix if rows and colonnes are not equal");
		}
	}
	
	public double determinant2D() throws Error	{
		if (this.isSquare())	{
			return this.data[0][0] * this.data[1][1] - this.data[0][1] * this.data[1][0];
		} else	{
			throw new Error("Cannot return the determiant of a non 2x2 matrix.");
		}
	}

	public double determinant()	throws Error	{
		
		if (this.isSquare())	{
			
			// IF 2x2 matrix return ad - bc
			if (this.is2D())	{
				return this.determinant2D();
			}
			
			// ELSE use the recurtion rule:
			// det(A_nxn) = a_11 * det(A_11) - a_12 * det(A_12) 
			// 				+ a_13 * det(A_13) - ... 
			//				+- a_1xn * det(A_1xn)
			double sum = 0.0;
			int element = 1;	// To know if we have to add or substract
			
			for (int j = 0; j < this.cols; j++)	{
				if (element % 2 == 0)	{						
					sum -= this.getData(0, j) * this.subMatrix(0, j).determinant();
				} else	{
					sum += this.getData(0, j) * this.subMatrix(0, j).determinant();
				}
				element++;
			}			
			
			return sum;	
			
		} else	{
			throw new Error("Cannot compute the detminant of a non square matrix");
		}
	}
	
	public Matrix subMatrix(int rowToIgnore, int colToIgnore)	{
		
		Matrix result = new Matrix(this);
		
		for (int i = 0; i < result.getRows(); i++)	{
			for (int j = 0; j < result.getCols(); j++)	{
				if (i != rowToIgnore && j != colToIgnore)	{
					result.setData(i, j, this.getData(i, j));
				} else	{
					result.setData(i, j);	// Set to null
				}
			}
		}		
		
		result.compresse();
		
		return result;		
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

	public Matrix multiply(final Matrix other) throws Error	{
		
		if (this.cols == other.getRows() && this.rows == other.getCols())	{
			Matrix result = new Matrix(this.rows, other.cols);
			
			for (int i = 0; i < result.getRows(); i++)	{
				for (int j = 0; j < result.getCols(); j++)	{
					int sum = 0;
					for (int k = 0; k < this.cols; k++)	{
						sum += this.getData(i, k) * other.getData(k, j);
					}
					result.setData(i, j, sum);
				}
			}
			
			return result;
		} else	{
			throw new Error("Cannot multiply matrices where Acols != Brows && Arows != Bcols");
		}
	}
	
	public Matrix multiply(final Vector mat)	{
		Matrix vec = new Matrix(3, 1);		
		Matrix result = vec.multiply(mat);
		
		return result;	
	}
	
	public Matrix power(int exponent) throws Error	{		
		if (this.isSquare())	{
			Matrix result = new Matrix(this);
			
			for (int i = 0; i < exponent - 1; i++)	{
				result = result.multiply(this);
			}
			
			return result;
		} else	{
			throw new Error("Cannot compute the power of " + exponent + " a non square matrix.");
		}
	}
	
	public Matrix inverse()	throws Error	{

		throw new Error("ERROR!!!!!!!");
		/*if (this.is2D())	{
			double temp = 1 / this.determinant2D();
			this.data[0][1] = -1.0 * this.data[0][1];
			this.data[1][0] = -1.0 * this.data[1][0];
			return this.multiply(temp);
		} else	{
			return this.inverse().multiply(1 / this.determinant());
		}*/
		
	}
	
	public Matrix transpose()	{
		Matrix mat = new Matrix(this.cols, this.rows);
		
		for (int i = 0; i < this.getRows(); i++)	{
			for (int j = 0; j < this.getCols(); j++)
				mat.setData(j, i, this.data[i][j]);
		}		
		return mat;
	}
	
	// Private functions	
	private int factorial(int number)	{
		if (number == 1)	{return 1;}
		return number * factorial(number - 1);
	}
	
	private void compresse()	{
		this.data = this.removeAllNulls(this.data);
		this.resize();
	}

    private Double[][] removeAllNulls(Double[][] array)	{
    	
    	List<Double[]> outerList = new ArrayList<Double[]>(array.length);
    	for(int i = 0; i < array.length; i++) {
    		Double[] inner = array[i];
    	    if (inner != null) {
    	        List<Object> list = new ArrayList<Object>(inner.length);
    	        for(int j = 0; j < inner.length; j++){
    	            if(inner[j] != null){
    	                list.add(inner[j]);
    	            }
    	        }
    	        outerList.add(list.toArray(new Double[list.size()]));
    	    }
    	}
    	array = outerList.toArray(new Double[outerList.size()][]);   
    	
    	return array;
    }
    
    private void resize()	{
   	
    	// find biggest cols
    	this.cols = this.getBiggestCol(this.data);
    	
    	// find number of rows that aren't {} and put all {} to null
    	int numberOfRows = 0;
    	for (int i = 0; i < this.data.length; i++)	{
    		if (this.data[i].length > 0)	{
    			numberOfRows++;
    		} else	{
    			this.data[i] = null;
    		}	
    	}
    	
    	this.data = this.removeAllNulls(this.data);    	
    	this.rows = this.data.length;
    }
    
    private <T> int getBiggestCol(T[][] array)	{
    	int biggest = 0;
    	for (int i = 0; i < array.length; i++)	{
    		if (array[i].length > biggest)
    			biggest = array[i].length;
    	}
    	
    	return biggest;
    }

	private void setData(int row, int col)	{
		try	{
			this.data[row][col] = null;
		} catch(Exception e)	{
			System.out.println(e.getMessage());
		}
	}    

	// Static methodes	
	public static Matrix vectorToMatrix(final Vector vector)	{
		Matrix temp = new Matrix(3, 1);		
		temp.setData(0, 0, vector.x);
		temp.setData(1, 0, vector.y);
		temp.setData(2, 0, vector.z);
		
		return temp;
	}
	
	public static Matrix rotationX(double angle)	{
		Matrix result = new Matrix(3, 3);
		
		result.setData(0, 0, 1);
		result.setData(0, 1, 0);
		result.setData(0, 2, 0);
		
		result.setData(1, 0, 0);
		result.setData(1, 1, Math.cos(angle));
		result.setData(1, 2, Math.sin(angle));
		
		result.setData(2, 0, 0);
		result.setData(2, 1, -Math.sin(angle));
		result.setData(2, 2, Math.cos(angle));
		
		return result;
	}
	
	public static Matrix rotationY(double angle)	{
		Matrix result = new Matrix(3, 3);
		
		result.setData(0, 0, Math.cos(angle));
		result.setData(0, 1, 0);
		result.setData(0, 2, -Math.sin(angle));
		
		result.setData(1, 0, 0);
		result.setData(1, 1, 1);
		result.setData(1, 2, 0);
		
		result.setData(2, 0, 0);
		result.setData(2, 1, Math.sin(angle));
		result.setData(2, 2, Math.cos(angle));
		
		return result;
	}
	
	public static Matrix rotationZ(double angle)	{
		Matrix result = new Matrix(3, 3);
		
		result.setData(0, 0, Math.cos(angle));
		result.setData(0, 1, Math.sin(angle));
		result.setData(0, 2, 0);
		
		result.setData(1, 0, -Math.sin(angle));
		result.setData(1, 1, Math.cos(angle));
		result.setData(1, 2, 0);
		
		result.setData(2, 0, 0);
		result.setData(2, 1, 0);
		result.setData(2, 2, 1);
		
		return result;
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
	
	public double[][] toArray()	{
		double[][] clone = new double[this.rows][this.cols];
		
		for (int i = 0; i < this.data.length; i++)	{
			for (int j = 0; j < this.data[i].length; j++)	{
				clone[i][j] = this.data[i][j];
			}
		}
		
		return clone;
	}
	
	public int[][] toIntArray()	{
		int[][] clone = new int[this.rows][this.cols];
		
		for (int i = 0; i < this.data.length; i++)	{
			for (int j = 0; j < this.data[i].length; j++)	{
				clone[i][j] = this.data[i][j].intValue();
			}
		}
		
		return clone;
	}

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
