package HW2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
// import java.nio.file.Paths;
/**
 * main
 */
public class HW {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader("/Users/estaban/Desktop/Прогерство/java_gb/HW2/input.txt");
        Scanner sc = new Scanner(fr);
        StringBuilder ch = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ch.append(line);
        }
        sc.close();
        String ch1 = ch.toString();
        char[] array = ch1.toCharArray();
        int b = Character.getNumericValue(array[2]);
        String a = Character.toString(array[5]);
        a = a + Character.toString(array[6]);
        int c = Integer.parseInt(a);
        System.out.println(Math.pow(c, b));
    }
}