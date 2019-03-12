package base.oop.enumPackage;

public enum Operation {
    PLUS {
        public double eval(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double eval(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        public double eval(double x, double y) {
            return x * y;
        }
    },
    DIVEDE {
        public double eval(double x, double y) {
            return x / y;
        }
    };

    public abstract double eval(double x, double y);
}
