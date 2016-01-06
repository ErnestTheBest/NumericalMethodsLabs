package Lab1;

import java.util.Scanner;


public class JakobiMethod {
    static double[][] initialMatrix;
    static double[] xValues;
    static double[] xTemporaryValues;
    static double precision;
    static int iterationsCounter;
    static boolean done = false;
    static double temp1;
    static double temp2;
    static double temp3;
    public static void main (String[] args){
        xValues = new double[3];
        xTemporaryValues = new double[3];
//запросить ввод матрицы
        initialMatrix = new double [3][4];
        getMatrix(initialMatrix);
//распечатать матрицу до проверки сходимости
        System.out.println("Матрица до проверки сходимости : ");
        printMatrix(initialMatrix);
//проверка сходимости
        checkConvergence(initialMatrix);
        if (done){
            System.out.println("Матрица после проверки сходимости.");
            printMatrix(initialMatrix);
        }
//выразить х из матрицы
        getXEquasions(initialMatrix);

//итерации по методу Якоби
        jakobiIterations(initialMatrix,xValues,xTemporaryValues);

//распечатка результатов
        printXValues(xValues);
    }



    private static void printXValues(double[] xValues) {
        for (int i=0;i<xValues.length; i++){
            System.out.println("x"+i+" = " +xValues[i]);
        }
        System.out.println("Колличество итераций : " + iterationsCounter);
    }
    private static double[][] checkConvergence(double[][] initialMatrix) {
        if (initialMatrix[0][0] > initialMatrix[1][0] && initialMatrix[1][0] > initialMatrix[2][0]){
            System.out.println("Система сходима.");
        } else while (done==false){
            if (initialMatrix[0][0] < initialMatrix[1][0] && initialMatrix[1][0] < initialMatrix[2][0]){
                for (int j=0;j<4;j++){
                    temp1 = initialMatrix[0][j];
                    initialMatrix [0][j] = initialMatrix[2][j];
                    initialMatrix[2][j] = temp1;
                }
            }
            if (initialMatrix[0][0] < initialMatrix[1][0]){
                for (int j=0;j<4;j++){
                    temp1 = initialMatrix[0][j];
                    initialMatrix [0][j] = initialMatrix[1][j];
                    initialMatrix[1][j] = temp1;
                }
            }
            if (initialMatrix[1][0] < initialMatrix[2][0]){
                for (int j=0;j<4;j++){
                    temp1 = initialMatrix[1][j];
                    initialMatrix [1][j] = initialMatrix[2][j];
                    initialMatrix[2][j] = temp1;
                }
            }
            if (initialMatrix[0][0] > initialMatrix[1][0] && initialMatrix[1][0] > initialMatrix[2][0]){
                done = true;
                temp1 =0;
            }
        }
        return initialMatrix;
    }
    private static double[] jakobiIterations(double[][] initialMatrix, double[] xValues, double[] xTemporaryValues) {
        done = false;
        iterationsCounter = 0;
        for (int i =0;i<50;i++){
//temp1 = initialMatrix[0][3] - (initialMatrix[0][1] * xTemporaryValues[1]) - (initialMatrix[0][2] * xTemporaryValues[2]) / initialMatrix[0][0];
            temp1 += initialMatrix[0][3];
            temp2 = initialMatrix[0][1] * xTemporaryValues[1];
            temp1 -= temp2;
            temp2 = initialMatrix[0][2] * xTemporaryValues[2];
            temp1 -= temp2;
            temp1 /= initialMatrix[0][0];
            xValues[0] = temp1;
            temp1 = 0;
            temp2 = 0;

//temp2 = initialMatrix[1][3] - (initialMatrix[1][0] * xTemporaryValues[0]) - (initialMatrix[1][2] * xTemporaryValues[2]) / initialMatrix[1][1];
            temp1 += initialMatrix[1][3];
            temp2 = initialMatrix[1][0] * xTemporaryValues[0];
            temp1 -= temp2;
            temp2 = initialMatrix[1][2] * xTemporaryValues[2];
            temp1 -= temp2;
            temp1 /= initialMatrix[1][1];
            xValues[1] = temp1;
            temp1 = 0;
            temp2 = 0;
//temp3 = initialMatrix[2][3] - (initialMatrix[2][0] * xTemporaryValues[0]) - (initialMatrix[2][1] * xTemporaryValues[1]) / initialMatrix[2][2];
            temp1 += initialMatrix[2][3];
            temp2 = initialMatrix[2][0] * xTemporaryValues[0];
            temp1 -= temp2;
            temp2 = initialMatrix[2][1] * xTemporaryValues[1];
            temp1 -= temp2;
            temp1 /= initialMatrix[2][2];
            xValues[2] = temp1;
            temp1 = 0;
            temp2 = 0;
            temp1 = xTemporaryValues[0] - xValues[0];
            temp2 = xTemporaryValues[1] - xValues[1];
            temp3 = xTemporaryValues[2] - xValues[2];
            temp1 = Math.abs(temp1);
            temp2 = Math.abs(temp2);
            temp3 = Math.abs(temp3);
            iterationsCounter++;
            if (temp1 < precision || temp2 < precision || temp3 < precision){
//break;
            }
            temp1=0;
            temp2=0;
            temp3=0;
            xTemporaryValues = xValues;
        }
        return xValues;
    }
    private static void getXEquasions(double[][] initialMatrix) {
        System.out.println("Выразим 'x' из уравнений : ");
        System.out.println("x1 = ("+initialMatrix[0][3]+" - ("+initialMatrix[0][1]+")x2 - ("+initialMatrix[0][2]+")x3 / "+initialMatrix[0][0]);
        System.out.println("x2 = ("+initialMatrix[1][3]+" - ("+initialMatrix[1][0]+")x1 - ("+initialMatrix[1][2]+")x3 / "+initialMatrix[1][1]);
        System.out.println("x3 = ("+initialMatrix[2][3]+" - ("+initialMatrix[2][0]+")x1 - ("+initialMatrix[2][1]+")x2 / "+initialMatrix[2][2]);
        System.out.println();

    }
    private static double definePrecision(double precision) {
        Scanner scanner = new Scanner(System.in);
        precision = scanner.nextDouble();
        return precision;
    }
    private static double[][] getMatrix(double[][] initialMatrix) {
        Scanner scanner = new Scanner(System.in);
        for (int i=1;i < 4; i++){
            for (int j = 1; j <4; j++){
                System.out.println("Enter value for A" + i+j);
                initialMatrix[i-1][j-1] = scanner.nextDouble();
            }
            System.out.println("Enter value for B" +i);
            initialMatrix[i-1][3] = scanner.nextDouble();
        }
        System.out.println("Ведите точность :");
        precision = scanner.nextDouble();
        return initialMatrix;
    }
    private static void printMatrix(double[][] initialMatrix) {
        for (int i = 0; i < initialMatrix.length; i++) {
            System.out.println(initialMatrix[i][0] + "x(1)+" + initialMatrix[i][1] + "x(2)+" + initialMatrix[i][2] + "x(3) = " + initialMatrix[i][3]);
        }
        System.out.println();
    }
}
//Тестовые данные
/*double[][] initialMatrix = {
{13.2, 1.9, 2.3, 5.12},
{0.5, -1.4, -9.6, 1.5},
{0.8, -7.3, -0.7, 5.2}

};
double[][] initialMatrix = {
{10, 2, -1, 7},
{1, 8, 3, -4},
{-2, -1, 10, 9}
};*/
