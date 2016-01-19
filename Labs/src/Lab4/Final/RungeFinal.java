package Lab4.Final;


public class RungeFinal {

    public static void main(String[] args) {

        RungeFinal runge = new RungeFinal();
        double x1 = 0;
        double x2 = 10;
        double h1 = 1E-1;
        double y0 = 1;

        runge.printPoints(x1, x2, h1, y0);
    }

    public void printPoints(double x1, double x2, double h, double y0) {
        double x = x1 + h;
        double y = y0;
        double w = theoretical(x1);
        System.out.println("x\tRunge\ty");
        System.out.format("%.2f\t%.2f\t%.2f\n", x1, y, w);
        while (x < x2) {
            y = calculatePoint(y, h);
            w = theoretical(x);
            System.out.format("%.2f\t%.2f\t%.2f\n", x, y, w);
            x += h;
        }
    }

    private double calculatePoint(double y, double h) {
        double f1, f2, f3, f4;

        f1 = function(y);
        f2 = function(y + (h * f1/2));
        f3 = function(y + (h * f2/2));
        f4 = function(y + (h * f3));

        return y + (h * (f1 + (2 * f2) + (2 * f3) + f4)/6);
        //сюда нужно будет воткнуть метод элера
    }

    private double function(double y) {
        return 0.25 * y - 0.05 * y * y;
    }

    private double theoretical(double x) {
        return /* он тут есть, но равен 1 - function(y0) * */0.25 * Math.exp(0.25 * x) / (0.2 + /* он тут есть, но равен 1 - function(y0) * */0.05 * Math.exp(0.25 * x));
    }
}

