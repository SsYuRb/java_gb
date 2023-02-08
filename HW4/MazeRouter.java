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
    public static Queue<Integer[]> queueRet = new LinkedList<Integer[]>();
    public static Integer[] XYQ = new Integer[2];
    public static int[] exiter = new int[2];

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
        int[][] arrayWithTheRoad = ShortestWay(arrayWithRoad);
        PrintArray(arrayWithTheRoad);
    }

    public static int[][] findAWay(int[][] arr) {
        Boolean exit = true;
        while (exit) {
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
            if (queueNum.isEmpty()) {
                exit = false;
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
        exiter = new int[] { exitX, exitY };
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
                System.out.printf("%4d", arr[i][index]);
            }
            System.out.println();
        }
    }

    public static void fillExit(int[][] arr) {

    }

    public static void MakeQuere(int x, int y) {
        if (x == 0) {
            if (y == 0) {
                Integer[] kk1 = new Integer[] { x, y + 1 };
                Integer[] kk2 = new Integer[] { x + 1, y };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk2);

            } else if (y == n - 1) {
                Integer[] kk1 = new Integer[] { x + 1, y };
                Integer[] kk2 = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk2);

            } else if ((y != 0) && (y != n - 1)) {
                Integer[] kk1 = new Integer[] { x, y + 1 };
                Integer[] kk2 = new Integer[] { x + 1, y };
                Integer[] kl = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kl);
                queueRet.add(kl);

            }
        } else if (x == n - 1) {
            if (y == 0) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x, y + 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk2);

            } else if (y == n - 1) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk2);

            } else if ((y != 0) && (y != n - 1)) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x, y + 1 };
                Integer[] kk3 = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk3);
                queueRet.add(kk3);
            }
        } else if (x != 0 && x != n - 1) {
            if (y == 0) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x, y + 1 };
                Integer[] kk3 = new Integer[] { x + 1, y };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk3);
                queueRet.add(kk3);
            } else if (y == n - 1) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x + 1, y };
                Integer[] kk3 = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk3);
                queueRet.add(kk3);
            } else if ((y != 0) && (y != n - 1)) {
                Integer[] kk1 = new Integer[] { x - 1, y };
                Integer[] kk2 = new Integer[] { x, y + 1 };
                Integer[] kk3 = new Integer[] { x + 1, y };
                Integer[] kk4 = new Integer[] { x, y - 1 };
                queueRet.add(kk1);
                queueRet.add(kk2);
                queueRet.add(kk3);
                queueRet.add(kk4);
                queueRet.add(kk4);
            }
        }
    }

    public static int[][] ShortestWay(int[][] arr) {
        int x = exiter[0];
        int y = exiter[1];
        int min = 1000;
        boolean ex = true;
        arr[x][y] = 100;
        while (ex) {
            queueRet.clear();
            MakeQuere(x, y);
            for (int i = 0; i < queueRet.size() + 1; i++) {
                Integer[] lop = queueRet.poll();
                if (lop[0] == initX && lop[1] == initY) {
                    ex = false;
                    x = lop[0];
                    y = lop[1];
                    arr[x][y] = 100;
                    break;
                }
                if (arr[lop[0]][lop[1]] < min && arr[lop[0]][lop[1]] != -1 && arr[lop[0]][lop[1]] != 0) {
                    min = arr[lop[0]][lop[1]];
                    x = lop[0];
                    y = lop[1];
                }
            }
            arr[x][y] = 100;
        }
        return arr;
    }
}
