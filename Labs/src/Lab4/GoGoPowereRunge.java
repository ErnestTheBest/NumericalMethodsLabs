package Lab4;


import java.util.Scanner;

public class GoGoPowereRunge {

    //массив У и З
    public static double [] YValues, ZValues;
    //значение вычеслений на каждом этапе
    public static double f1,f2,f3,f4;
    //значение вычеслений на каждом этапе
    public static double g1,g2,g3,g4;
    //У и З нулевое
    public static double y0, z0;
    public static double stepSize;
    //корень характерестического уравнения, шаг дискретезации, интервал времени наблюдения
    public static double root, step, interval;
    public static int numberOfPoints;


    public static void main(String[] args) {
        getValues();
        calculateMisc();
        calculateValues();
        System.out.println();
    }

    private static void calculateValues() {

        double halfInt = stepSize/2;

        for (int i = 1; i<=numberOfPoints; i++){
            f1 = ZValues[i-1];
            g1 = function (YValues[i-1], ZValues[i-1]);

            f2 = ZValues[i-1] + (halfInt * g1);
            g2 = function(YValues[i-1] + (halfInt*f1), ZValues[i-1] + (halfInt*g1));

            f3 = ZValues[i-1] + (halfInt * g2);
            g3 = function(YValues[i-1] + (halfInt*f2), ZValues[i-1] + (halfInt*g2));

            f4 = ZValues[i-1] + (halfInt * g3);
            g4 = function(YValues[i-1] + (stepSize*f3), ZValues[i-1] + (stepSize*g3));

            YValues[i] = YValues[i-1] + (stepSize/6) * (f1+(2*f2)+(2*f3)+f4);
            ZValues[i] = ZValues[i-1] + (stepSize/6) * (g1+(2*g2)+(2*g3)+g4);



            System.out.println();
        }
    }

    public static double function (double y, double z){
        return 16 - 5*y - 5*z;
    }

    private static void calculateMisc() {
        YValues = new double[numberOfPoints+1];
        ZValues = new double[numberOfPoints+1];

        YValues[0] = y0;
        ZValues[0] = z0;

        System.out.println("Интервал наблюдения : " + interval);
        stepSize = interval / numberOfPoints;
        System.out.println("Шаг дискретезации : " + stepSize);
    }

    private static void getValues() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите y0 :");
        y0 = scanner.nextDouble();
        System.out.println("Введите z0 :");
        z0 = scanner.nextDouble();
        System.out.println("Введите интервал наблюдения :");
        interval = scanner.nextDouble();
        System.out.println("ВВедите кол-во точек на интервале :");
        numberOfPoints = scanner.nextInt();

    }
}
