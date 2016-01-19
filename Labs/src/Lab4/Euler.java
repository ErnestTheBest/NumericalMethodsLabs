
public class Euler {

    public static void main(String[] args) {

        Euler euler = new Euler();
        double x1 = 0;
        double x2 = 10;
        double h1 = 1E-1;
        double y0 = 1;

        euler.printPoints(x1, x2, h1, y0);
    }

    // распечатать точки от x1 до x2 с шагом h при известном y0
    // в формате x -> решение методом Эйлера -> известное истинное значение
    public void printPoints(double x1, double x2, double h, double y0) {
        double x = x1 + h;
        double y = y0;
        double w = t(x1);
        System.out.println("x\tEuler\ty");
        System.out.format("%.2f\t%.2f\t%.2f\n", x1, y, w);
        while (x < x2) {
            y = calculatePoint(y, h);
            w = t(x);
            System.out.format("%.2f\t%.2f\t%.2f\n", x, y, w);
            x += h;
        }
    }

    // метод Эйлера
    private double calculatePoint(double y, double h) {
        return y + h * f(y);
    }

    // рещаемое уравнение
    private double f(double y) {
        return 0.25 * y - 0.05 * y * y;
    }

    // известное аналитическое решение
    private double t(double x) {
        return 0.25 * Math.exp(0.25 * x) / (0.2 + 0.05 * Math.exp(0.25 * x));
    }
}
