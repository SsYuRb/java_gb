package HW4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeRouter {
    public static int x;
    public static int y;
    public static int initX;
    public static int initY;
    public static int n;
    public static Scanner scanner = new Scanner(System.in);
    public static Queue<Integer[]> queueNum = new LinkedList<Integer[]>();
    public static Integer[] XYQ = new Integer[2];
    public static Integer[] exiter = new Integer[2];

    public static void main(String[] args) {
        int[][] arr = InitArr();
        initPosition();
        arr[x][y] = 1;
        int[][] arrayWithWall = buildTheWall(arr);
        int[][] arrayWithExit = makeExit(arrayWithWall);
        int[][] arrayWithRoad = findAWay(arrayWithExit);
        scanner.close();
        System.out.println("The array of roads");
        PrintArray(arrayWithRoad);
        System.out.println("The shortest way");
        int[][] arrayWithTheRoad = ShortWay(arrayWithRoad);
        PrintArray(arrayWithTheRoad);
    }

    public static int[][] findAWay(int[][] arr) {
        while ((x != exiter[0]) || (y != exiter[1] - 1)) {
            if ((x != 0) && (x - 1 >= 0)) {
                if (arr[x - 1][y] == 0) {
                    arr[x - 1][y] = arr[x][y] + 1;
                    XYQ = new Integer[] { x - 1, y };
                    queueNum.add(XYQ);
                }
            }
            if ((y != exiter[1]) && (y + 1 <= n - 1)) {
                if (arr[x][y + 1] == 0) {
                    arr[x][y + 1] = arr[x][y] + 1;
                    XYQ = new Integer[] { x, y + 1 };
                    queueNum.add(XYQ);
                }
            }
            if ((x != exiter[0]) && (x + 1 <= n - 1)) {
                if ((arr[x + 1][y] == 0)) {
                    arr[x + 1][y] = arr[x][y] + 1;
                    XYQ = new Integer[] { x + 1, y };
                    queueNum.add(XYQ);
                }
            }
            if ((y != 0) && (y - 1 >= 0)) {
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
        initX = x;
        initY = y;
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
        exiter = new Integer[] { exitX, exitY };
        return arr;
    }

    public static void xyDinamic() {
        if (!queueNum.isEmpty()) {
            XYQ = queueNum.poll();
            x = XYQ[0];
            y = XYQ[1];
        }
    }

    public static int[][] InitArr() {
        System.out.println("Какого размера должен быть массив?");
        n = scanner.nextInt();
        int[][] arr = new int[n][n];
        return arr;
    }

    public static void PrintArray(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int index = 0; index < n; index++) {
                System.out.printf("  ,%d , ", arr[i][index]);
            }
            System.out.println();
        }
    }

    public static int[][] ShortWay(int[][] arr) {
        x = exiter[0];
        y = exiter[1];
        arr[x][y] = 100;
        while ((x != initX) || (y != initY)) {
            if (x == 0){
                if (y == 0){
                    if ((arr[x+1][y] < arr[x][y+1]) && (arr[x+1][y]!=-1)) {
                        arr[x+1][y] = 100;
                        x += 1;
                    } else {
                        arr[x][y+1] = 100;
                        y += 1;
                    }
                }else if (y == exiter[1]){
                    if ((arr[x+1][y] < arr[x][y-1]) && (arr[x+1][y]!=-1)) {
                        arr[x+1][y] = 100;
                        x += 1;
                    } else {
                        arr[x][y-1] = 100;
                        y -= 1;
                    }
                }else{
                    if ((arr[x][y+1] <= arr[x+1][y]) && (arr[x][y+1] <= arr[x][y-1]) &&(arr[x][y+1]!=-1)) {
                        arr[x][y+1] = 100;
                        y += 1;
                    } else if ((arr[x+1][y] <= arr[x][y-1]) && (arr[x+1][y]!=-1)) {
                        arr[x+1][y] = 100;
                        x += 1;
                    } else {
                        arr[x][y-1] = 100;
                        y -= 1;
                    }
                }
            } else if (x == exiter[0]) {
                if (y == 0){
                    if ((arr[x-1][y] <= arr[x][y+1]) && (arr[x-1][y] != -1)){
                        arr[x-1][y] = 100;
                        x -= 1;
                    } else {
                        arr[x][y+1] = 100;
                        y += 1;
                    }
                }else if (y == exiter[1]){
                    if ((arr[x-1][y] <= arr[x][y-1]) && (arr[x-1][y] != -1)) {
                        arr[x-1][y] = 100;
                        x -= 1;
                    } else {
                        arr[x][y-1] = 100;
                        y -= 1;
                    }
                }else{
                    if ((arr[x-1][y] <= arr[x][y+1]) && (arr[x-1][y] <= arr[x][y-1]) && (arr[x-1][y] !=-1)) {
                        arr[x-1][y] = 100;
                        x -= 1;
                    } else if ((arr[x][y + 1] <= arr[x][y-1])&&(arr[x][y + 1]!=-1)) {
                        arr[x][y + 1] = 100;
                        y += 1;
                    } else {
                        arr[x][y-1] = 100;
                        y -= 1;
                    }
                }
            } else if ((y == 0) && (x!= 0) && (x != exiter[0])) {
                if ((arr[x-1][y] <= arr[x][y+1]) && (arr[x-1][y] <= arr[x+1][y])&&(arr[x-1][y]!=-1)) {
                    arr[x-1][y] = 100;
                    x -= 1;
                } else if ((arr[x][y+1] <= arr[x+1][y]) && (arr[x][y+1] != -1)) {
                    arr[x][y+1] = 100;
                    y += 1;
                } else {
                    arr[x+1][y] = 100;
                    x += 1;
                }
            } else if ((y == exiter[1]) && (x!= 0) && (x != exiter[0])) {
                if ((arr[x-1][y] <= arr[x+1][y]) && (arr[x-1][y] <= arr[x][y-1]) && (arr[x-1][y] != -1)) {
                    arr[x-1][y] = 100;
                    x -= 1;
                } else if ((arr[x+1][y] <= arr[x][y-1]) && (arr[x+1][y]!=-1)) {
                    arr[x+1][y] = 100;
                    x += 1;
                } else {
                    arr[x][y-1] = 100;
                    y -= 1;
                }
            } else {
                if ((arr[x-1][y] <= arr[x][y+1]) && (arr[x-1][y] <= arr[x+1][y]) && (arr[x-1][y] <= arr[x][y-1]) && (arr[x-1][y] != -1)) {
                    arr[x-1][y] = 100;
                    x -= 1;
                } else if ((arr[x][y+1] <= arr[x+1][y]) && (arr[x][y+1] <= arr[x][y-1])&& (arr[x][y+1]!=-1)) {
                    arr[x][y+1] = 100;
                    y += 1;
                } else if ((arr[x+1][y] <= arr[x][y-1])&&(arr[x+1][y]!=-1)) {
                    arr[x+1][y] = 100;
                    x += 1;
                } else {
                    arr[x][y-1] = 100;
                    y -= 1;
                }
            }
        }
        return arr;
    }
}
