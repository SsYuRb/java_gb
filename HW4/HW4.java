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
    public static Integer[] XYQ = new Integer[2];
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        InitPosition();
        arr[x][y] = 1;
        int[][] arrayWithWall = BuildTheWall(arr);
        int[][] arrayWithExit = MakeExit(arrayWithWall);
        int[][] arrayWithRoad = FindAWay(arrayWithExit);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(arrayWithRoad[i][j]);
            }
            System.out.println("\n");
        }
    }
    public static int[][] FindAWay (int[][] arr) {
        Queue<int[]> queueNum = new LinkedList<int[]>();
        while ((x != 4) && (y != 4)) {
            if((x!=0)&&(arr[x-1][y] != -1)){
                arr[x-1][y] = arr[x][y] + 1;
                int[] kmn = new int[] {(x-1), y};
                queueNum.add(kmn);
            }
            
            if((y!=4)&&(arr[x][y+1]!=-1)){
                arr[x][y+1]=arr[x][y]+1;
                int[] kmn = new int[] {x, (y+1)};
                queueNum.add(kmn);
            }

            if((x!=4)&&(arr[x+1][y]!=4)){
                arr[x+1][y] = arr[x][y] + 1;
                int[] kmn = new int[] {(x+1), y};
                queueNum.add(kmn);
            }

            if((y!=0)&&(arr[x][y-1]!=-1)){
                arr[x][y-1] = arr[x][y] + 1;
                int[] kmn = new int[] {x, (y-1)};
                queueNum.add(kmn);
            }

            int[] khm = queueNum.poll();
            x = khm[0];
            y = khm[1];
        }
        return arr;
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
}