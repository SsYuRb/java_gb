package HW4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * HW4
 */
public class HW4 {
    public static int x;
    public static int y;
    public static Scanner scanner = new Scanner(System.in);
    public static Queue<Integer[]> queueNum = new LinkedList<Integer[]>();
    public static Integer[] XYQ = new Integer[2];
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        InitPosition();
        arr[x][y] = 1;
        arr = BuildTheWall(arr);
        arr = MakeExit(arr);
        arr = FindAWay(arr);
        System.out.println(x);
        System.out.println(y);
        System.out.println(arr[x][y]);
        System.out.println(arr[x-1][y]);
        System.out.println(arr[x][y+1]);
        System.out.println(arr[x+1][y]);
        System.out.println(arr[x][y-1]);
    }
    public static int[][] FindAWay(int[][] Arr) {
        while (Arr[x][y] != -2) {
            if (x != 0) {
                if (Arr[x - 1][y] == 0) {
                    Arr[x - 1][y] = Arr[x][y] + 1;
                    XYQ = new Integer[] { x - 1, y };
                    queueNum.add(XYQ);
                }
            }
            if (y != 4) {
                if (Arr[x][y + 1] == 0) {
                    Arr[x][y + 1] = Arr[x][y] + 1;
                    XYQ = new Integer[] { x, y + 1 };
                    queueNum.add(XYQ);
                }
            }
            if (x != 4) {
                if ((Arr[x + 1][y] == 0)) {
                    Arr[x + 1][y] = Arr[x][y] + 1;
                    XYQ = new Integer[] { x + 1, y };
                    queueNum.add(XYQ);
                }
            }
            if (y != 0) {
                if ((Arr[x][y - 1] == 0)) {
                    Arr[x][y - 1] = Arr[x][y] + 1;
                    XYQ = new Integer[] { x, y - 1 };
                    queueNum.add(XYQ);
                }
            }
            XYdinamic();
        }
        return Arr;
    }
    public static void InitPosition() {
        System.out.println("Введите начальную позицию: ");
        x = scanner.nextInt();
        y = scanner.nextInt();
    }
    public static int VallMakerPosX() {
        System.out.print("Введите ряд стены: ");
        int valX = scanner.nextInt();
        return valX;
    }
    public static int VallMakerPosY() {
        System.out.print("Введите столбец стены: ");
        int valY = scanner.nextInt();
        return valY;
    }
    public static int[][] BuildTheWall(int[][] Arr) {
        System.out.print("Сколько стен вы хотите построить: ");
        int countVals = scanner.nextInt();
        for (int i = 0; i < countVals; i++) {
            int posX = VallMakerPosX();
            int posY = VallMakerPosY();
            Arr[posX][posY] = -1;
        }
        return Arr;
    }
    public static int[][] MakeExit(int[][] Arr) {
        System.out.println("Где будет выход: ");
        int xExit = scanner.nextInt();
        int yExit = scanner.nextInt();
        Arr[xExit][yExit] = -2;
        return Arr;
    }
    public static void XYdinamic() {
        Integer[] xy = queueNum.poll();
        x = xy[0];
        y = xy[1];
    }
}