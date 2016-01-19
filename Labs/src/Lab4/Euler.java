
public class Euler {

    public static void main(String[] args) {

        Euler euler = new Euler();
        double x1 = 0;
        double x2 = 10;
        double h1 = 1E-1;
        double y0 = 1;

        euler.printPoints(x1, x2, h1, y0);
    }

    // ����������� ����� �� x1 �� x2 � ����� h ��� ��������� y0
    // � ������� x -> ������� ������� ������ -> ��������� �������� ��������
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

    // ����� ������
    private double calculatePoint(double y, double h) {
        return y + h * f(y);
    }

    // �������� ���������
    private double f(double y) {
        return 0.25 * y - 0.05 * y * y;
    }

    // ��������� ������������� �������
    private double t(double x) {
        return 0.25 * Math.exp(0.25 * x) / (0.2 + 0.05 * Math.exp(0.25 * x));
    }
}
