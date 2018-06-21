 /*
        Студент: Чепёлкин Иван
        ДЗ №4 по курсу Java. Уровень 1
        Преподователь: Артём Евдокимов
*/

/*
В данной программе я добавил возможность играть в игру c
полем 5x5 и длинной выйгрыша 4 знака. Эти методы выбирают из игрового поля
проверочную матрицу 4x4 и проверяют выйгрышные кмбинации.После этого делается
сдвиг проверочной матрицы по оси/осям и опять идет проверка.Метод долгий,
но вроде рабочий)))
Для этого были написаны 3 метода:
chekDiag
chekLanes
checkWin.
*/
package geekBrains.lesson4;


import java.util.Random;
import java.util.Scanner;

public class Main {

    // 3. Определяем размеры массива
    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;
    static final int SIZE_CHIP = 4; //длинна выйгрышной комбинации

    // 1. Создаем двумерный массив
    static char[][] field = new char[SIZE_Y][SIZE_X];
    static int sizeFish = 3;

    // 2. Обозначаем кто будет ходить какими фишками
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';

    // 8. Создаем сканер
    static Scanner scanner = new Scanner(System.in);
    // 12. Создаем рандом
    static final Random rand = new Random();

    // 4. Заполняем на массив
    private static void initField() {
        for(int i = 0; i < SIZE_Y; i++) {
            for(int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // 5. Выводим на массив на печать
    private static void printField() {
        //6. украшаем картинку
        System.out.println("-------");
        for(int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for(int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        //6. украшаем картинку
        System.out.println("-------");
    }

    // 7. Метод который устанавливает символ
    private static void setSym(int y, int x, char sym){
        field[x][y] = sym;
    }

    // 9. Ход игрока
    private static void playerStep() {
        // сначала написать вот так
//        System.out.println("Введите координаты: X Y (1-3)");
//        int x = scanner.nextInt() - 1;
//        int y = scanner.nextInt() - 1;
//        setSym(y, x, PLAYER_DOT);
//
        // 11. переписываем с проверкой
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-5)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y, x, PLAYER_DOT);

    }

    // 13. Ход ПК
    private static void aiStep() {
        int x;
        int y;
        do{
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        } while(!isCellValid(y,x));
        setSym(y, x, AI_DOT);
    }

  // Проверка диагоналей матрицы 4x4

    public static boolean chekDiag(char sym,int shiftX,int shiftY){
        boolean right = true;
        boolean left = true;

        for (int i = 0; i < SIZE_CHIP; i++) {
            right = right & (field[i+shiftX][i+shiftY] == sym); //проверка на выйгрыш диаганали 1:1 ->5:5
           left = left & (field[(SIZE_CHIP - i - 1)+shiftX][i+shiftY] == sym);//проверка на выйгрыш диаганали 5:1 ->1:5
        }

        if(left || right){
            return true;
        }
        return false;
    }

   // Проверка вертикалей и горизонталей матрицы 4x4

    public static boolean chekLanes(char sym,int shiftX,int shiftY) {
        boolean horizon ;
        boolean vertical ;

        for (int i = shiftX; i < shiftX + SIZE_CHIP; i++) {
            horizon = true;
            vertical = true;

            for (int j = shiftY; j < shiftY + SIZE_CHIP; j++) {
                horizon = horizon & (field[i][j] == sym); //проверка горизонтали
                vertical = vertical & (field[j][i] == sym); // проверка вертикали
            }
            if (horizon || vertical) {
                return true;
            }
        }

        return false;

    }

    // 14. Проверка победы
    public static boolean checkWin(char sym) {
        for (int shiftX = 0; shiftX < 2; shiftX++) { //сдвиг проверочной матрицы 4x4 на 1 влево
            for (int shiftY = 0; shiftY < 2; shiftY++) {//сдвиг проверочной матрицы 4x4 на 1 вниз
                if (chekDiag(sym, shiftX, shiftY) || chekLanes(sym, shiftX, shiftY)) {//сдвиги передаем методам
                                                                                // поиска выйгрышной комбинации
                    return true;
                }
            }

        }
        return false;
    }

    // 16. Проверка полное ли поле? возможно ли ходить?
    private static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for(int j = 0; j < SIZE_X; j++) {
                if(field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Проверяем возможен ли ход
    private static boolean isCellValid(int y, int x) {
        // если вываливаемся за пределы возвращаем false
        if(x < 0 || y < 0 || x > SIZE_X -1 || y > SIZE_Y - 1) {
            return false;
        }
        // если не пуcтое поле тоже false
        return (field[x][y] == EMPTY_DOT);
    }

    public static void main(String[] args) {
        // 1 - 1 иницируем и выводим на печать
        initField();
        printField();
        // 1 - 1 иницируем и выводим на печать

        // 15 Основной ход программы

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT)) {
                System.out.println("Win SkyNet!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }

    }
}