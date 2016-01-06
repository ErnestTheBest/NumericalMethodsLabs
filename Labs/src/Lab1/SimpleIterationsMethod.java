import java.util.Scanner;


public class SimpleIterationsMethod {
    static double[][] initialMatrix;
    static double[] xValues;
    static final int height = 3;
    static final int length = 3;
    static double coefficient;
    static double x1;
    static double x2;
    static double x3;
    static boolean done;
    static double temp1;
    public static void main(String[] arg) {
        xValues = new double[3];

//запросить ввод матрицы
        initialMatrix = new double [3][4];
        getMatrix(initialMatrix);

//Распечатать матрицу
        System.out.println("Начальная матрица : ");
        printMatrix(initialMatrix);

//Выбор ведущего элемента
        leadingElement(initialMatrix);

//Прямой ход Гауса
        gaussDirectFlow(initialMatrix);

        System.out.println();
        System.out.println("Треугольная матрица : ");
        printMatrix(initialMatrix);

//Обратный ход Гауса
        gaussReverseFlow(initialMatrix, xValues);

//Распечатать значения
        System.out.println();
        for (int i=0; i<xValues.length; i++){
            System.out.println("x"+(i+1)+" = " + xValues[i]);
        }
    }
    private static double[][] leadingElement(double[][] initialMatrix) {
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

            if ((initialMatrix[1][0] == initialMatrix[2][0])){
                if (initialMatrix[1][1] < initialMatrix[2][1]){
                    for (int j=0;j<4;j++){
                        temp1 = initialMatrix[1][j];
                        initialMatrix [1][j] = initialMatrix[2][j];
                        initialMatrix[2][j] = temp1;
                    }
                }
            }
            if (initialMatrix[0][0] > initialMatrix[1][0] && initialMatrix[1][0] >= initialMatrix[2][0]){
                done = true;
                temp1 =0;
            }
        }
        return initialMatrix;
    }
    private static double[] gaussReverseFlow(double[][] initialMatrix,double[] xValues) {
        x3 = initialMatrix[height-1][length] / initialMatrix[height-1][length-1];
        double temp1;
        double temp2;
        temp1 = x3 * initialMatrix[height-2][length-1];
        temp2 = initialMatrix[height-2][length] - temp1;
        x2 = temp2 / initialMatrix[height-2][length-2];
        double temp3;
        double temp4;
        double temp5;
        temp3 = x3 * initialMatrix[height-3][length-1];
        temp4 = x2 * initialMatrix[height-3][length-2];
        temp5 = initialMatrix[height-3][length] - temp3 - temp4;
        x1 = temp5 / initialMatrix[height-3][length-3];
        xValues[0] = x1;
        xValues[1] = x2;
        xValues[2] = x3;
        return xValues;
    }
    private static double[][] gaussDirectFlow(double[][] initialMatrix) {
        for (int row = 1; row < height; row++) {
            for (int column = row; column < height; column++) {
                coefficient = initialMatrix[column][row - 1] / initialMatrix[row - 1][row - 1];
                for (int i = 0; i < initialMatrix[column].length; i++) {
                    initialMatrix[column][i] = initialMatrix[column][i] - coefficient * initialMatrix[row - 1][i];
                }
            }
        }
        return initialMatrix;
    }
    private static void printMatrix(double[][] initialMatrix) {
        System.out.println();
        for (int i = 0; i < initialMatrix.length; i++) {
            System.out.println(initialMatrix[i][0] + "x(1)+" + initialMatrix[i][1] + "x(2)+" + initialMatrix[i][2] + "x(3) = " + initialMatrix[i][3]);
        }
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
        return initialMatrix;
    }
}
//Тестовые данные
/*double[][] initialMatrix = {
{2, 1, -1, 8},
{-3, -1, 2, -11},
{-2, 1, 2, -3}
};
double[][] initialMatrix = {
{1, 1, 1, 6},
{1, -1, 2, 5},
{2, -1, -1, -3}
};*/