package Lab4;

import java.util.Scanner;

public class EulerMethod {

    public static double a, b, h, y0;
    public static int amount = 1000;


    public static void main(String[] args) {

        getValues();

        computeValue();

    }

    private static void computeValue() {

        double [] YValues = new double[amount];
        YValues[0] = y0;
        for (short i = 1; i < amount; i++){

            YValues[i] = YValues[i-1] + h * (a * YValues[i-1] - b * Math.pow(YValues[i-1], 2) );

            if (i % 5 == 0){
                System.out.println("y_" + i + " = " +YValues[i]);
            }
        }

        System.out.println("Сравним вычесление с формулой приведенной на лекции :");
        int ind_a = (int) (a/h);
        double some = (YValues[0] * a * Math.exp( a * h)) / (a + b * YValues[0] * Math.exp(a*h) - 1);
        System.out.println("Exact y_" + ind_a + " (t) = " + ((YValues[0] * a * Math.exp( a * h)) /
                (a + b * YValues[0] * Math.exp(a*h) - 1)));
        System.out.println("Euler y_" + ind_a + " (t) = " + YValues[ind_a]);
    }

    public static void getValues(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter value A :");
        a = scanner.nextDouble();
        System.out.println("Enter value B :");
        b = scanner.nextDouble();
//        System.out.println("Enter value H :");
//        h = scanner.nextDouble();
        h = 10./amount;
        System.out.println("Enter value Y0 :");
        y0 = scanner.nextDouble();

    }
}
