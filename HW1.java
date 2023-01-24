
import java.util.Scanner;

/**
 * HW1
 */
public class HW1 {

    public static void main(String[] args) {
        System.out.print("Введите число: ");
        Scanner numbScanner = new Scanner(System.in);
        int num = numbScanner.nextInt();
        numbScanner.close();
        int x = num*(num+1)/2;
        System.out.printf("Треугольное число %d: %d \n", num, x);
    }
}