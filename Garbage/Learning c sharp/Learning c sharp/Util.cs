using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public abstract class Util
    {
        public struct RetrunTemplate {
            public double[] realRoot;
            public Complex[] complexRoots;
        }
        public static RetrunTemplate zeros(double a, double b, double c) {

            if (b * b - (4 * a * c) > 0) {
                double[] result = new double[2];

                result[0] = (-b + Math.Sqrt(b * b - (4 * a * c))) / (2 * a);
                result[1] = (-b - Math.Sqrt(b * b - (4 * a * c))) / (2 * a);

                RetrunTemplate template = new RetrunTemplate
                {
                    realRoot = result
                };

                return template;
            }
            else
            {
                double complexA = -b / (2 * a);
                double complexB = Math.Sqrt(Math.Abs(b * b - (4 * a * c))) / (2 * a);

                Complex result1 = new Complex(complexA, complexB);
                Complex result2 = new Complex(complexA, -1 * complexB);

                RetrunTemplate template = new RetrunTemplate
                {
                    complexRoots = new Complex[2]
                };
                template.complexRoots[0] = result1;
                template.complexRoots[1] = result2;

                return template;
            }
        }

        public static void printArray<T>(T[] array)    {
            int index = 0;
            Console.Write("{ ");
            foreach (T element in array) {
                Console.Write(index + ": " + element);
                if (index != array.Length - 1) {
                    Console.Write(", ");
                }
                index++;
            }
            Console.Write(" }");
        }

        public static void printArray<T>(T[][] array) {
            int index = 0;
            Console.Write("{ ");
            foreach (T[] element in array)
            {
                Console.Write("\t" + index + ": ");
                printArray<T>(element);
                Console.WriteLine(", ");
                index++;
            }
            Console.Write(" }");
        }
    }
}
