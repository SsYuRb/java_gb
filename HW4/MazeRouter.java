package HW4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeRouter {
    public static int x;
    public static int y;
    public static Scanner scanner = new Scanner(System.in);
    public static Queue<Integer[]> queueNum = new LinkedList<Integer[]>();
    public static Integer[] XYQ = new Integer[2];

    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        initPosition();
        arr[x][y] = 1;
        int[][] arrayWithWall = buildTheWall(arr);
        int[][] arrayWithExit = makeExit(arrayWithWall);
        int[][] arrayWithRoad = findAWay(arrayWithExit);
        scanner.close();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(arrayWithRoad[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static int[][] findAWay(int[][] arr) {
        while ((x != 4) || (y != 4)) {
            if (x != 0) {
                if (arr[x - 1][y] == 0) {
                    arr[x - 1][y] = arr[x][y] + 1;
                    XYQ = new Integer[] { x - 1, y };
                    queueNum.add(XYQ);
                }
            }
            if (y != 4) {
                if (arr[x][y + 1] == 0) {
                    arr[x][y + 1] = arr[x][y] + 1;
                    XYQ = new Integer[] { x, y + 1 };
                    queueNum.add(XYQ);
                }
            }
            if (x != 4) {
                if ((arr[x + 1][y] == 0)) {
                    arr[x + 1][y] = arr[x][y] + 1;
                    XYQ = new Integer[] { x + 1, y };
                    queueNum.add(XYQ);
                }
            }
            if (y != 0) {
                if ((arr[x][y - 1] == 0)) {
                    arr[x][y - 1] = arr[x][y] + 1;
                    XYQ = new Integer[] { x, y - 1 };
                    queueNum.add(XYQ);
                }
            }
            xyDinamic();
        }
        return arr;
    }

    public static void initPosition() {
        System.out.println("Enter starting position: ");
        x = scanner.nextInt();
        y = scanner.nextInt();
    }

    public static int wallMakerPosX() {
        System.out.print("Enter X-coordinate for wall: ");
        return scanner.nextInt();
    }

    public static int wallMakerPosY() {
        System.out.print("Enter Y-coordinate for wall: ");
        return scanner.nextInt();
    }
    
    public static int[][] buildTheWall(int[][] arr) {
        System.out.println("Enter number of walls: ");
        int numWalls = scanner.nextInt();
        for (int i = 0; i < numWalls; i++) {
            int wallX = wallMakerPosX();
            int wallY = wallMakerPosY();
            arr[wallX][wallY] = -1;
        }
        return arr;
    }
    
    public static int[][] makeExit(int[][] arr) {
        System.out.println("Enter exit coordinates: ");
        int exitX = scanner.nextInt();
        int exitY = scanner.nextInt();
        arr[exitX][exitY] = -2;
        return arr;
    }
    
    public static void xyDinamic() {
        if (!queueNum.isEmpty()) {
            XYQ = queueNum.poll();
            x = XYQ[0];
            y = XYQ[1];
        }
    }    
}    
