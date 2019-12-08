using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Util
    {
        public static double[] zeros(double a, double b, double c) {

            if (b * b - (4 * a * c) > 0)   {
                Double[] result = new Double[2];

                result[0] = (-b + Math.Sqrt(b * b - (4 * a * c))) / (2 * a);
                result[1] = (-b - Math.Sqrt(b * b - (4 * a * c))) / (2 * a);

                return result;
            }
            else
            {
                throw new Exception("Cannot compute square root if negative number");
            }
        }
    }
}
