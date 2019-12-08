using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Complex
    {
        private double a;
        private double b;
        private double r;
        private double phi;

        public Complex(double a, double b) {
            this.a = a;
            this.b = b;
            this.r = Math.Sqrt(a * a + b * b);
            this.phi = Math.Atan2(b, a);
        }

        public Complex() {
            this.fromPolar(0, 0);
        }

        public void fromPolar(double r, double angle) {
            this.a = r * Math.Cos(angle);
            this.b = r * Math.Sin(angle);
            this.r = r;
            this.phi = angle;
        }

        // Getters
        public double getA() { return this.a; }
        public double getB() { return this.b; }
        public double getR() { return this.r; }
        public double getPhi() { return this.phi; }

        public string toString() {
            return this.a + " + " + this.b + " i";
        }

    }
}
