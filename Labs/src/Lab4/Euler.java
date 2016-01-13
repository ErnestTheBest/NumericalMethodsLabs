
public class Euler {

    public static void main(String[] args) {

        Euler euler = new Euler();
        double x1 = 0;
        double x2 = 10;
        double h1 = 1E-1;
        double y0 = 1;

        euler.printPoints(x1, x2, h1, y0);
    }

    // ‡ÒÔÂ˜‡Ú‡Ú¸ ÚÓ˜ÍË ÓÚ x1 ‰Ó x2 Ò ¯‡„ÓÏ h ÔË ËÁ‚ÂÒÚÌÓÏ y0
    // ‚ ÙÓÏ‡ÚÂ x -> Â¯ÂÌËÂ ÏÂÚÓ‰ÓÏ ›ÈÎÂ‡ -> ËÁ‚ÂÒÚÌÓÂ ËÒÚËÌÌÓÂ ÁÌ‡˜ÂÌËÂ
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

    // ÏÂÚÓ‰ ›ÈÎÂ‡
    private double calculatePoint(double y, double h) {
        return y + h * f(y);
        //сюда нужно будет воткнуть метод элера
    }

    // Â˘‡ÂÏÓÂ Û‡‚ÌÂÌËÂ
    private double f(double y) {
        return 0.25 * y - 0.05 * y * y;
    }

    // ËÁ‚ÂÒÚÌÓÂ ‡Ì‡ÎËÚË˜ÂÒÍÓÂ Â¯ÂÌËÂ
    private double t(double x) {
        return /* он тут есть, но равен 1 - f(y0) * */0.25 * Math.exp(0.25 * x) / (0.2 + /* он тут есть, но равен 1 - f(y0) * */0.05 * Math.exp(0.25 * x));
    }
}
