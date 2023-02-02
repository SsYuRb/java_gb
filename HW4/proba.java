package HW4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * proba
 */
public class proba {
    public static int x;
    public static int y;
    public static Queue<Integer[]> queueNum = new LinkedList<Integer[]>();
    public static void main(String[] args) {
        Integer[] xy = new Integer[] {3, 4};
        queueNum.add(xy);
        XYdinamic();
        System.out.println(x);
        System.out.println(y);
        xy = new Integer[] {6, 7};
        queueNum.add(xy);
        XYdinamic();
        System.out.println(x);
        System.out.println(y);
    }
    
    public static void XYdinamic() {
        Integer[] xy = queueNum.poll();
        x = xy[0];
        y = xy[1];
    }
}