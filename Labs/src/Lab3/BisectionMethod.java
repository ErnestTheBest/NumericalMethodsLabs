import java.util.Scanner;


public class BisectionMethod {
    public static double valueA, valueB, epsilon, valueX = 0;
    public static int iterationsCounter = 0;


    public static void main(String[] args) {

//Главная программа которая выполняет код
//Запросить данные
        getValues();
//Запустить подсчет. Внутри метода есть распечатка промежуточных результатов
        run();
    }

    //Метод считающий результаты
    private static void run() {
        double interval = function(valueA) * function(valueB);
        if (interval <=0 ){
            System.out.println(interval + " Интервал подходит");
            while((valueB - valueA) > epsilon){
                valueX = (valueA+valueB)/2;
                if ((function(valueA)* function(valueX)) <=0){
                    valueB = valueX;
                } else {
                    valueA = valueX;
                }
                iterationsCounter++;
//Промежуточные результаты
                System.out.printf("%d) A : %-8.5f  B : %-8.5f  X : %-8.5f\n ",iterationsCounter, valueA, valueB, valueX);
            }
//Конечные результаты
            System.out.printf("Ответ x = : %-8.6f, с ошибкой : %-8.6f\n", ((valueB+valueA)/2), (valueB-valueA));
            System.out.printf("Количество итераций : %d",iterationsCounter);
        } else {
//В случае если интервал не подходит
            System.out.println(interval + " Интервал не подходит.");
        }
    }
    //Метод запрашивающий ввод переменных
    public static void getValues(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения А и Б (дробные числа разделяются запятой) :");
        valueA = scanner.nextDouble();
        valueB = scanner.nextDouble();
        System.out.println("Введите точность Эпсилон :");
        epsilon = scanner.nextDouble();
    }
    //Метод считающий функцию.
//F(x)=x*e^(0.8*x)-16
    private static double function (double x){
        double result;
        result = x * Math.exp(0.8 * x) - 16;
        return result;
    }
}
