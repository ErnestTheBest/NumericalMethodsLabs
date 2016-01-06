public class MNKLauncher {

    static int initialValueCount = 8;
    /*static double[] xInitialValues;
    static double[] yInitialValues;
    static double[] xSquareValues;
    static double[] xCubicValues;
    static double[] xQuatroValues;
    static int initialValueCount;
*/
    public static void main(String[] args) {

//тестовые данные
        double[] xInitialValues = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8};
        double[] yInitialValues = {3.03, 3.142, 3.358, 3.463, 3.772, 3.251, 3.17, 3.665};
        int initialValueCount = 8;

//начальная матрица
        double[][] initialMatrix = new double [3][4];
        double[] ABCValues = new double[3];

//квадраты, куды, 4-ая степнь, перемножение Х и У, Х^2 * У.
        double[] xSquareValues;
        double[] xCubicValues;
        double[] xQuatroValues;
        double[] xTimesY;
        double[] xSquareTimesY;

//переменные
        double xSum = 0;
        double ySum = 0;
        double xSquareSum = 0;
        double xCubicSum = 0;
        double xQuadSum = 0;
        double xTimesYSum = 0;
        double xSquareTimesYSum = 0;


//создание массива временных переменных
        xSquareValues = new double[initialValueCount];
        xCubicValues = new double[initialValueCount];
        xQuatroValues = new double[initialValueCount];
        xTimesY = new double[initialValueCount];
        xSquareTimesY = new double[initialValueCount];

//подсчет данных
        getXSquareValues(xInitialValues,xSquareValues);
        getXCubicValues(xInitialValues,xCubicValues);
        getXQuadValues(xInitialValues,xQuatroValues);
        getXTimesY(xInitialValues,yInitialValues,xTimesY);
        getXSquareTimesY(xSquareValues,yInitialValues,xSquareTimesY);

//вычесление сумм
        xSum = getSum(xInitialValues);
        ySum = getSum(yInitialValues);
        xSquareSum = getSum(xSquareValues);
        xCubicSum = getSum(xCubicValues);
        xQuadSum = getSum(xQuatroValues);
        xTimesYSum = getSum(xTimesY);
        xSquareTimesYSum = getSum(xSquareTimesY);


//составление матрицы для подсчета
        initialMatrix = getInitialmatrix(xSum,ySum,xSquareSum,xCubicSum,xQuadSum,xTimesYSum,xSquareTimesYSum);

//метод гауса. Прямой ход
        initialMatrix = gaussDirectFlow(initialMatrix);

//метод гауса, обратный ход
        ABCValues = gaussReverseFlow(initialMatrix,ABCValues);

//ТУДУ - распечатка данных
        for (int i=0; i<initialValueCount; i++){

            System.out.println(xInitialValues[i] + " " + xSquareValues[i]+ " " + xCubicValues[i]+ " " +xQuatroValues[i] + " "+  xTimesY[i] + " " + xSquareTimesY[i]);
        }

    }

    private static double[] gaussReverseFlow(double[][] initialMatrix,double[] xValues) {

        double x1=0;
        double x2=0;
        double x3=0;


        x3 = initialMatrix[3-1][3] / initialMatrix[3-1][3-1];

        double temp1;
        double temp2;
        temp1 = x3 * initialMatrix[3-2][3-1];
        temp2 = initialMatrix[3-2][3] - temp1;
        x2 = temp2 / initialMatrix[3-2][3-2];

        double temp3;
        double temp4;
        double temp5;
        temp3 = x3 * initialMatrix[3-3][3-1];
        temp4 = x2 * initialMatrix[3-3][3-2];
        temp5 = initialMatrix[3-3][3] - temp3 - temp4;
        x1 = temp5 / initialMatrix[3-3][3-3];

        xValues[0] = x1;
        xValues[1] = x2;
        xValues[2] = x3;

        return xValues;
    }

    private static double[][] gaussDirectFlow(double[][] initialMatrix) {

        double coefficient = 0;

        for (int row = 1; row < 3; row++) {
            for (int column = row; column < 3; column++) {
                coefficient = initialMatrix[column][row - 1] / initialMatrix[row - 1][row - 1];
                for (int i = 0; i < initialMatrix[column].length; i++) {
                    initialMatrix[column][i] = initialMatrix[column][i] - coefficient * initialMatrix[row - 1][i];
                }
            }
        }

        return initialMatrix;
    }

    private static double[][] getInitialmatrix(double xSum, double ySum, double xSquareSum, double xCubicSum, double xQuadSum, double xTimesYSum, double xSquareTimesYSum) {

        double [][] initialMatrix = {
                {initialValueCount, xSum, xSquareSum, ySum},
                {xSum, xSquareSum, xCubicSum, xTimesYSum},
                {xSquareSum, xCubicSum, xQuadSum, xSquareTimesYSum}
        };

        return initialMatrix;
    }

    private static double getSum(double[] inputValues) {

        double sum = 0;

        for (int i=0; i<initialValueCount;i++){
            sum += inputValues[i];
        }
        return sum;
    }

    private static double[] getXSquareTimesY(double[] xSquareValues, double[] yInitialValues, double[] xSquareTimesY) {
        for (int i=0; i<initialValueCount; i++){
            xSquareTimesY[i] = xSquareValues[i] * yInitialValues[i];
        }

        return xSquareTimesY;
    }

    private static double[] getXTimesY(double[] xInitialValues, double[] yInitialValues, double[] xTimesY) {

        for (int i=0; i<initialValueCount; i++){
            xTimesY[i] = xInitialValues[i] * yInitialValues[i];
        }
        return xTimesY;
    }

    private static double[] getXQuadValues(double[] xInitialValues, double[] xQuatroValues) {

        for (int i = 0; i < initialValueCount; i++){
            xQuatroValues[i] = Math.pow(xInitialValues[i], 4);
        }
        return xQuatroValues;
    }


    private static double[] getXCubicValues(double[] xInitialValues, double[] xCubicValues) {

        for (int i = 0; i < initialValueCount; i++){
            xCubicValues[i] = Math.pow(xInitialValues[i], 3);
        }
        return xCubicValues;
    }
    private static double[] getXSquareValues(double[] xInitialValues, double[] xSquareValues) {

        for (int i = 0; i < initialValueCount; i++){
            xSquareValues[i] = Math.pow(xInitialValues[i], 2);
        }
        return xSquareValues;
    }
}
/*
double[] xInitialValues = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0,7, 0.8};
double[] yInitialValues = {3.03, 3.142, 3.358, 3.463, 3.772, 3.251, 3.17, 3.665};
int initialValueCount = 8;
*/
