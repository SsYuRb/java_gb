

package HW2;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * HW2
 */
public class HW2 {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("java_gb/HW2/input.txt");
        LinkedList<Integer> ll = new LinkedList<Integer>();
        Scanner sc = new Scanner(fr);
        int a = 1;
        while (sc.hasNextLine()){
            if (sc.next()==" "); // Не знаю почему, но без этого условия не работает.
                ll.add(sc.nextInt());
        } 
        if (ll.get(0) == 0){
            System.out.println(1);
        } else {
            for (int i = 0; i < ll.get(0); i++) {
                a *= ll.getLast();
            }
            System.out.println(a);
        }
        fr.close();
        sc.close();
    }
}