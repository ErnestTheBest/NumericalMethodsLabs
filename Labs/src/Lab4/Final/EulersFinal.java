package Lab4.Final;

public class EulersFinal {

    public static void main(String[] args) {

        EulersFinal eulerss = new EulersFinal();
        double x1 = 0;
        double x2 = 50;
        double h1 = 1E-1;
        double y0 = 1;

        eulerss.printPoints(x1, x2, h1, y0);
    }

    public void printPoints(double x1, double x2, double h, double y0) {
        double x = x1 + h;
        double y = y0;
        double w = t(x1);
        double difference = 0;
        double [] maxDif = new double[2];
        System.out.println("x\tEulers\ty");
        System.out.format("%.2f\t%.2f\t%.2f\n", x1, y, w);
        while (x < x2) {
            y = calculatePoint(y, h);
            w = t(x);
            difference = y - w;
            System.out.format("%.2f\t%.2f\t%.2f\n", x, y, w);
            x += h;


            if (Math.abs(maxDif[0]) < Math.abs(difference)) {
                maxDif[0] = Math.abs(difference);
                maxDif[1] = x;
            }

        }

        System.out.printf("Максимальная ошибка = " + maxDif[0] + " x = " + maxDif[1]);
    }


    private double calculatePoint(double y, double h) {
        return y + h * f(y);
    }

    private double f(double y) {
        return 0.25 * y - 0.05 * y * y;
    }

    private double t(double x) {
        return /* он тут есть, но равен 1 - f(y0) * */0.25 * Math.exp(0.25 * x) / (0.2 + /* он тут есть, но равен 1 - f(y0) * */0.05 * Math.exp(0.25 * x));
    }
}
