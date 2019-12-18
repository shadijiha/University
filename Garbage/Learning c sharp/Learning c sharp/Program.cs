using System;

namespace Learning_c_sharp
{
    class Program
    {
        public static void Main(string[] args)
        {
            Console.Title = "Z OMEGALUL E";

            try
            {
                Vector x = new Vector();
                x.random3D();

                Vertex ver = (Vertex)x;
                ver.print();
            }
            catch (Exception e) {
                Console.WriteLine("Error! " + e.Message);            
            }           

            // ---- PAUSE ----
            Console.ReadLine();
        }
    }
}
