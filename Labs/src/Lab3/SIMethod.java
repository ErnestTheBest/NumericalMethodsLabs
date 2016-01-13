import java.util.Scanner;

public class SIMethod {
    public static double nextX = 1;
    public static double valueA, valueB, epsilon, valueX, koefficent, tempFunction, mistake, derivative = 0;
    public static int iterationsCounter = 0;
    public static void main(String[] args) {

//Главная прогламма которая выполняет код
//Запросить данные
        getValues();
//Проверка устойчивости
        stabilityCheck();
//Метод, который считает коэффицент при уравнении
        calculateKoefficent();
//Запустить подсчет. Внутри метода есть распечатка промежуточных результатов
        run();
    }
    private static void stabilityCheck() {
        valueX = (valueA + valueB) / 2;
        tempFunction = Math.abs(calculateDevirative(valueX));
        if (tempFunction < 1) {
            System.out.println(tempFunction + "Система устойчива без коэфицента.");
        } else {
            System.out.printf("Производная от функции равна %6.5f, нужен коэфицент релаксации.\n", tempFunction);
        }
    }
    //Метод считающий результаты
    private static void run() {

        while (Math.abs(nextX - valueX) > epsilon) {
            if (iterationsCounter !=0){
                valueX = nextX;
            }
            nextX = valueX + koefficent * function(valueX);
//Промежуточные результаты
            iterationsCounter++;
            System.out.printf("X%d = %8.6f\n ", iterationsCounter, nextX);
            mistake = Math.abs(nextX - valueX);
        }
//Конечные результаты
        System.out.println("Конечный Х = " + nextX + ", с ошибкой : " + mistake);
        System.out.printf("Количество итераций : %d", iterationsCounter);
    }
    //Метод запрашивающий ввод переменных
    public static void getValues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения А и Б (дробные числа раделяются запятой) :");
        valueA = scanner.nextDouble();
        valueB = scanner.nextDouble();
        System.out.println("Введите точность Эпсилон :");
        epsilon = scanner.nextDouble();
    }
    //Метод считающий функцию.
//F(x)=x*e0.8*x-16
    private static double function(double x) {
        double result;
        result = x * Math.exp(0.8 * x) - 16;
        return result;

    }

    //В этом методе храниться производная посчитанная ручками
    private static double calculateDevirative(double x) {
//Производная
        double result;
        result = 1 + (0.8 * valueX + 1) * Math.exp(0.8 * valueX);
        return result;
    }
    //подсчёт коэфицента
//в ИФ - индивидуальная ф-ия
    private static void calculateKoefficent() {
        koefficent = 0.5 / tempFunction;
        if (Math.abs(1 + koefficent*tempFunction) > 1){
            koefficent = 0 - koefficent;
        }
        System.out.println("Коэфицент : " + koefficent);
    }
}
