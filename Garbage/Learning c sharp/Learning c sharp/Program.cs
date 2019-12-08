using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Title = "OMEGALUL";

            double[] test = Util.zeros(2, 9, 5);

            foreach(double element in test) {
                Console.WriteLine(element);
            }

            Console.ReadLine();
        }
    }
}
