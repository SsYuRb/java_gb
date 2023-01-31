package HW2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * HW3
 */
public class HW3 {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("java_gb/HW2/input.txt");
        Scanner sc = new Scanner(fr);
        System.out.println(sc.next("/n"));
    }
}