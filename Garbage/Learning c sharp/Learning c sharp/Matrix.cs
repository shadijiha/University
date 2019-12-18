using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Matrix
    {
		private double?[][] data;
		private int rows;
		private int cols;

		private T[][] init2DArray<T>(int rows, int cols) {
			T[][] temp = new T[rows][];

			for (int i = 0; i < rows; i++) {
				temp[i] = new T[cols];
			}

			return temp;
		}

		private T[] arrayListToArray<T>(ArrayList list)
		{
			T[] result = new T[list.Count];

			for (int i = 0; i < result.Length; i++)
			{
				result[i] = (T)list[i];
			}
			return result;
		}

		private double?[][] toNullable2DArray(double[][] array) {
			double?[][] result = init2DArray<double?>(array.Length, array[0].Length);

			for (int i = 0; i < array.Length; i++) {
				for (int j = 0; j < array[i].Length; j++) {
					result[i][j] = array[i][j];
				}
			}
			return result;
		}

		/**
		 * @param {Double[][]} array: Takes a 2D array and push the data
		 * to this.data. All colonnes must have the same size.
		 */
		public Matrix(double?[][] array)
		{
			// TODO Auto-generated constructor stub
			this.data = array;
			this.rows = array.Length;
			this.cols = array[0].Length;
		}

		/**
		 * @param {Integer[][]} array: Takes a 2D array and push the data
		 * to this.data. All colonnes must have the same size.
		 */
		public Matrix(int[][] array)
		{
			this.rows = array.Length;
			this.cols = this.getBiggestCol<int>(array);

			this.data = this.init2DArray<double?>(this.rows, this.cols);
			for (int i = 0; i < array.Length; i++)
			{
				for (int j = 0; j < array[i].Length; j++)
				{
					this.data[i][j] = (double)array[i][j];
				}
			}
		}

		public Matrix(int rows, int cols)
		{
			this.rows = rows;
			this.cols = cols;
			this.data = this.init2DArray<double?>(rows, cols);
			this.randomize();
		}

		public Matrix(Matrix other)
		{
			// Copy matrix
			this.rows = other.getRows();
			this.cols = other.getCols();
			this.data = this.init2DArray<double?>(this.rows, this.cols);			

			for (int i = 0; i < this.rows; i++)
			{
				for (int j = 0; j < this.cols; j++)
				{
					this.setData(i, j, other.getData(i, j));
				}
			}
		}

		public void randomize(int scale)
		{
			Random rand = new Random();
			for (int i = 0; i < this.data.Length; i++)
			{
				for (int j = 0; j < this.data[i].Length; j++)
				{
					this.data[i][j] = rand.Next(0, scale);
				}
			}
		}

		public void randomize()
		{
			this.randomize(10);
		}

		public void generateIdentity()
		{
			if (this.rows == this.cols)	{
				for (int i = 0; i < this.data.Length; i++)
				{
					for (int j = 0; j < this.data[i].Length; j++)
					{
						if (i == j)
							this.data[i][j] = 1.0;
						else
							this.data[i][j] = 0.0;
					}
				}
			} else	{
				throw new Exception("Cannot build an identity matrix if rows and colonnes are not equal");
			}
		}

		public double determinant2D()
		{
			if (this.isSquare())	{
				return (double) (this.data[0][0] * this.data[1][1] - this.data[0][1] * this.data[1][0]);
			} else	{
				throw new Exception("Cannot return the determiant of a non 2x2 matrix.");
			}
		}

		public double determinant()
		{
		
			if (this.isSquare())	{

				// IF 2x2 matrix return ad - bc
				if (this.is2D())
				{
					return this.determinant2D();
				}

				// ELSE use the recurtion rule:
				// det(A_nxn) = a_11 * det(A_11) - a_12 * det(A_12) 
				// 				+ a_13 * det(A_13) - ... 
				//				+- a_1xn * det(A_1xn)
				double sum = 0.0;
				int element = 1;    // To know if we have to add or substract

				for (int j = 0; j < this.cols; j++)
				{
					if (element % 2 == 0)
					{
						sum -= (double)this.getData(0, j) * this.subMatrix(0, j).determinant();
					}
					else
					{
						sum += (double)this.getData(0, j) * this.subMatrix(0, j).determinant();
					}
					element++;
				}

				return sum;

			} else	{
				throw new Exception("Cannot compute the detminant of a non square matrix");
			}
		}

		public Matrix subMatrix(int rowToIgnore, int colToIgnore)
		{

			Matrix result = new Matrix(this);

			for (int i = 0; i < result.getRows(); i++)
			{
				for (int j = 0; j < result.getCols(); j++)
				{
					if (i != rowToIgnore && j != colToIgnore)
					{
						result.setData(i, j, this.getData(i, j));
					}
					else
					{
						result.setData(i, j);   // Set to null
					}
				}
			}

			result.compresse();

			return result;
		}

		public Matrix add(Matrix other)
		{
		
			if (this.rows == other.rows && this.cols == other.cols)	{
				Matrix result = new Matrix(this.rows, this.cols);

				for (int i = 0; i < result.getRows(); i++)
				{
					for (int j = 0; j < result.getCols(); j++)
					{
						result.setData(i, j, (double)this.data[i][j] + (double)other.getData(i, j));
					}
				}

				return result;

			} else	{
				throw new Exception("Cannot add 2 Matrix with diffrent rows and colonnes count.");
			}

		}

		public Matrix multiply(double scale)
		{
			Matrix result = new Matrix(this.rows, this.cols);

			for (int i = 0; i < result.getRows(); i++)
			{
				for (int j = 0; j < result.getCols(); j++)
				{
					result.setData(i, j, (double)this.data[i][j] * scale);
				}
			}

			return result;
		}

		public Matrix multiply(Matrix other)
		{
		
			if (this.cols == other.getRows() && this.rows == other.getCols())	{
				Matrix result = new Matrix(this.rows, other.cols);

				for (int i = 0; i < result.getRows(); i++)
				{
					for (int j = 0; j < result.getCols(); j++)
					{
						double sum = 0;
						for (int k = 0; k < this.cols; k++)
						{
							sum += (double)this.getData(i, k) * (double)other.getData(k, j);
						}
						result.setData(i, j, sum);
					}
				}

				return result;
			} else	{
				throw new Exception("Cannot multiply matrices where Acols != Brows && Arows != Bcols");
			}
		}

		public Matrix multiply(ref Vector mat)
		{
			Matrix vec = new Matrix(3, 1);
			Matrix result = vec.multiply(ref mat);

			return result;
		}

		public Matrix power(int exponent)
		{		
			if (this.isSquare())	{
				Matrix result = new Matrix(this);

				for (int i = 0; i < exponent - 1; i++)
				{
					result = result.multiply(this);
				}

				return result;
			} else	{
				throw new Exception("Cannot compute the power of " + exponent + " a non square matrix.");
			}
		}

		public Matrix inverse()
		{
			throw new Exception("ERROR!!!!!!!");
			/*if (this.is2D())	{
				double temp = 1 / this.determinant2D();
				this.data[0][1] = -1.0 * this.data[0][1];
				this.data[1][0] = -1.0 * this.data[1][0];
				return this.multiply(temp);
			} else	{
				return this.inverse().multiply(1 / this.determinant());
			}*/
		}

		public Matrix transpose()
		{
			Matrix mat = new Matrix(this.cols, this.rows);

			for (int i = 0; i < this.getRows(); i++)
			{
				for (int j = 0; j < this.getCols(); j++)
					mat.setData(j, i, (double)this.data[i][j]);
			}
			return mat;
		}

		// Private functions	
		protected int factorial(int number)
		{
			if (number == 1) { return 1; }
			return number * factorial(number - 1);
		}

		private void compresse()
		{
			this.data = this.toNullable2DArray(this.removeAllNulls(this.data));
			this.resize();
		}

		private double[][] removeAllNulls(double?[][] array)
		{
			ArrayList outerList = new ArrayList(array.Length);
			for (int i = 0; i < array.Length; i++)
			{
				double?[] inner = array[i];
				if (inner != null)
				{
					ArrayList list = new ArrayList(inner.Length);
					for (int j = 0; j < inner.Length; j++)
					{
						if (inner[j] != null)
						{
							list.Add(inner[j]);
						}
					}
					outerList.Add(this.arrayListToArray<double>(list));
					
				}
			}
			double[][] result = this.arrayListToArray<double[]>(outerList);

			return result;
		}

		private void resize()
		{

			// find biggest cols
			this.cols = this.getBiggestCol<double?>(this.data);

			// find number of rows that aren't {} and put all {} to null
			for (int i = 0; i < this.data.Length; i++)
			{
				if (this.data[i].Length <= 0)
				{
					this.data[i] = null;
				}
			}

			this.data = this.toNullable2DArray(this.removeAllNulls(this.data));
			this.rows = this.data.Length;
		}

		private int getBiggestCol<T> (T[][] array)
		{
			int biggest = 0;
			for (int i = 0; i < array.Length; i++)
			{
				if (array[i].Length > biggest)
					biggest = array[i].Length;
			}

			return biggest;
		}

		private void setData(int row, int col)
		{
			try
			{
				this.data[row][col] = null;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.Message);
			}
		}

		// Static methodes	
		public static Matrix vectorToMatrix(Vector vector)
		{
			Matrix temp = new Matrix(3, 1);
			temp.setData(0, 0, vector.x);
			temp.setData(1, 0, vector.y);
			temp.setData(2, 0, vector.z);

			return temp;
		}

		public static Matrix rotationX(double angle)
		{
			Matrix result = new Matrix(3, 3);

			result.setData(0, 0, 1);
			result.setData(0, 1, 0);
			result.setData(0, 2, 0);

			result.setData(1, 0, 0);
			result.setData(1, 1, Math.Cos(angle));
			result.setData(1, 2, Math.Sin(angle));

			result.setData(2, 0, 0);
			result.setData(2, 1, -Math.Sin(angle));
			result.setData(2, 2, Math.Cos(angle));

			return result;
		}

		public static Matrix rotationY(double angle)
		{
			Matrix result = new Matrix(3, 3);

			result.setData(0, 0, Math.Cos(angle));
			result.setData(0, 1, 0);
			result.setData(0, 2, -Math.Sin(angle));

			result.setData(1, 0, 0);
			result.setData(1, 1, 1);
			result.setData(1, 2, 0);

			result.setData(2, 0, 0);
			result.setData(2, 1, Math.Sin(angle));
			result.setData(2, 2, Math.Cos(angle));

			return result;
		}

		public static Matrix rotationZ(double angle)
		{
			Matrix result = new Matrix(3, 3);

			result.setData(0, 0, Math.Cos(angle));
			result.setData(0, 1, Math.Sin(angle));
			result.setData(0, 2, 0);

			result.setData(1, 0, -Math.Sin(angle));
			result.setData(1, 1, Math.Cos(angle));
			result.setData(1, 2, 0);

			result.setData(2, 0, 0);
			result.setData(2, 1, 0);
			result.setData(2, 2, 1);

			return result;
		}

		public static explicit operator double(Matrix mat) {
			return mat.determinant();
		}

		public static Matrix operator *(Matrix first, Matrix other) {
			return first.multiply(other);
		}
		public static Matrix operator *(Matrix first, double scale)
		{
			return first.multiply(scale);
		}

		// Setters
		public void setData(int row, int col, double? newData)
		{
			try
			{
				this.data[row][col] = newData;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.Message);
			}
		}

		// Getters
		public double? getData(int row, int col)
		{
			double? result = 0.0;

			try
			{
				result = this.data[row][col];
			}
			catch (Exception e)
			{
				Console.WriteLine(e.Message);
			}
			return result;
		}

		public int getRows() { return this.rows; }
		public int getCols() { return this.cols; }

		public double[][] toArray()
		{
			this.compresse();
			double[][] clone = this.init2DArray<double>(this.rows, this.cols);

			for (int i = 0; i < this.data.Length; i++)
			{
				for (int j = 0; j < this.data[i].Length; j++)
				{
					clone[i][j] = (double)this.data[i][j];
				}
			}
			return clone;
		}

		public int[][] toIntArray()
		{
			this.compresse();
			int[][] clone = this.init2DArray<int>(this.rows,this.cols);

			for (int i = 0; i < this.data.Length; i++)
			{
				for (int j = 0; j < this.data[i].Length; j++)
				{
					clone[i][j] = (int) this.data[i][j];
				}
			}

			return clone;
		}

		public bool isSquare()
		{
			return this.rows == this.cols;
		}

		public bool is2D()
		{
			return this.rows == 2 && this.cols == 2;
		}

		public void print()
		{
			for (int i = 0; i < this.data.Length; i++)
			{
				for (int j = 0; j < this.data[i].Length; j++)
				{
					Console.Write(this.data[i][j] + "\t");
				}
				Console.WriteLine();
			}
		}

	}
}
