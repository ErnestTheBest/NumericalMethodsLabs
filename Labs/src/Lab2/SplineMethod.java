
import java.util.Scanner;

public class SplineMethod {

    static double[] XValues = {1,2,5,8};
    static double[] YValues = {0,4,3,37};
    static double[] HValues;
    static double[] AValues;
    static double[] BValues;
    static double[] CValues;
    static double[] DValues;
    static int valueCount = 4;



    public static void main(String[] args) {

//Запрос на ввод данных
//getValues();

//Вычисление H
        calculateHValues();

//Вычисление A
        calculateAValues();

//Вычислить C
        calculateCValues();

//Вычислить D
        calculateDValues();

//Вычислить B
        calculateBValues();

//Распечатать значения
        System.out.println(AValues);
        getValues();

    }
    private static void calculateBValues() {
        BValues = new double[valueCount-1];
        double temp;

        for (int i =0; i<valueCount-1;i++){

            BValues[i] = YValues[i+1] - YValues[i];
            temp = Math.pow(HValues[i], 2);
            temp *= CValues[i];
            BValues[i] -= temp;
            temp = Math.pow(HValues[i], 3);
            temp *= DValues[i];
            BValues[i] -= temp;
            BValues[i] /= HValues[i];
        }
    }

    private static void calculateDValues() {
        DValues = new double[valueCount-1];

        for (int i=0;i<valueCount-1;i++){
            DValues[i] = (CValues[i+1] - CValues[i]);
            DValues[i] /= 3*HValues[i];
        }
    }

    private static void calculateCValues() {
        CValues = new double[valueCount];
        double[][] tempMatrix = new double[2][3];

        for (int i=0;i<2;i++){
            tempMatrix[i][2] = 3 * ((YValues[i+2] - YValues[i+1]) / HValues[i+1] -
                    (YValues[i+1] - YValues[i]) / HValues[i]);
        }
        tempMatrix[0][0] = 2*(HValues[0] + HValues[1]);
        tempMatrix[1][1] = 2*(HValues[1] + HValues[2]);
        tempMatrix[0][1] = HValues[1];
        tempMatrix[1][0] = HValues[1];



        for (int i=0;i<valueCount-1;i++) {
            if (i == 0 || i == valueCount) {
                CValues[i] = 0;
            } else {
                magicGausMethod(tempMatrix);
            }
        }
    }

    private static void magicGausMethod(double[][] initialMatrix) {
        double coefficient;


        for (int row = 1; row < 2; row++) {
            for (int column = row; column < 2; column++) {
                coefficient = initialMatrix[column][row - 1] / initialMatrix[row - 1][row - 1];
                for (int i = 0; i < initialMatrix[column].length; i++) {
                    initialMatrix[column][i] = initialMatrix[column][i] - coefficient * initialMatrix[row - 1][i];
                }
            }
        }

        CValues[2] = initialMatrix[1][2] / initialMatrix[1][1];

        double temp1;
        double temp2;
        temp1 = CValues[2] * initialMatrix[0][1];
        temp2 = initialMatrix[0][2] - temp1;
        CValues[1] = temp2 / initialMatrix[0][0];

    }


    private static void calculateAValues() {
        AValues = new double[valueCount-1];

        for (int i=0;i<valueCount-1;i++){
            AValues[i] = YValues[i];
        }
    }

    private static void calculateHValues() {
        HValues = new double[valueCount-1];

        for (int i=0;i<valueCount-1;i++){
            HValues[i] = XValues[i+1] - XValues[i];
        }
    }
    private static void getValues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько значений дано : ");
        valueCount = scanner.nextInt();

        XValues = new double[valueCount];
        YValues = new double[valueCount];

        for (int i=0; i<valueCount; i++){
            System.out.println("Ведите Х " + (i+1));
            XValues[i] = scanner.nextDouble();
            System.out.println("Ведите Y " + (i+1));
            YValues[i] = scanner.nextDouble();
        }
    }
}
