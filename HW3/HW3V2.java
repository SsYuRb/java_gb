package HW3;


import java.util.Scanner;

public class HW3V2 {
    public static int x;
    public static int y;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        long[][] labirint = MakeAnArray();
        long[][] LabWithVals = BuildTheWall(labirint);
        long[][] doneLab = FillArray(LabWithVals);
        long endGame = SearchEndPoint(doneLab);
        scanner.close();
        System.out.print("Количество способов пройти этот путь до конца: ");
        System.out.println(endGame);
    }
    public static long[][] MakeAnArray() {
        System.out.println("Введите количество строк и столбцов по очереди с новой строки:");
        x = scanner.nextInt();
        y = scanner.nextInt();
        long[][] myLab = new long [x][y];
        return myLab;
    }
    public static long[][] FillArray(long[][] Arr) {
        System.out.println("Выберите позицию для старта:");
        int posX = scanner.nextInt();
        int posY = scanner.nextInt();
        Arr[0][0] = 1;
        for (int i = 0; i < x-posX; i++) {
            for (int j = 0; j < y-posY; j++) {
                if (((i == 0) || (j == 0)) && (Arr[i][j] != -1)) {
                    Arr[i][j] = 1;
                } else if ((Arr[i][j]!=-1) && (Arr[i][j-1]!=-1) && (Arr[i-1][j]!=-1)) {
                    Arr[i][j] = Arr[i][j-1] + Arr[i-1][j];
                } else if ((Arr[i][j]!=-1) && (Arr[i][j-1]==-1)) {
                        Arr[i][j] = Arr[i-1][j];
                } else if ((Arr[i][j]!=-1) && (Arr[i-1][j]==-1)) {
                        Arr[i][j] = Arr[i][j-1];
                } else if (Arr[i][j] == -1){
                    Arr[i][j] = -1;
                }
            }
        }
        return Arr;
    }
    public static long SearchEndPoint(long[][] Arr) {
        long max = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (Arr[i][j] > max){
                    max = Arr[i][j];
                }
            }
        }
        return max;
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
    public static long[][] BuildTheWall(long[][]Arr) {
        System.out.print("Сколько стен вы хотите построить: ");
        int countVals = scanner.nextInt(x);
        for (int i = 0; i < countVals; i++) {
            int posX = VallMakerPosX();
            int posY = VallMakerPosY();
            Arr[posX][posY] = -1;
        }
        return Arr;
    }
}