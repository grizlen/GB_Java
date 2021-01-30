package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static Random random;
    public static void main(String[] args) {
	    init(3);
	    start();
    }
    static int scanInt(){
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("требуется число");
        }
        return scanner.nextInt();
    }
    static int scanInt(int range){
        int result;
        while ((result = scanInt()) < 0 || result > range)
            System.out.printf("Требуеся число от %d до %d\n", 0, range);
        return result;
    }
    static int scanInt(String msg, int range){
        System.out.println(msg);
        return scanInt(range);
    }
    static void init(int size){
//        new Map
        map_size = (size > 2 && size < 11) ? size: 3;
        map = new char[map_size][map_size];
        for (int r = 0; r < map_size; r++)
            for (int c = 0; c < map_size; c++)
                map[r][c] = '-';

            scanner = new Scanner(System.in);
            random = new Random();
        System.out.println("Крестики нолики в процедурном стиле");
        map_print();
    }
    static void start(){
        while (move());
        System.out.println("Bye");
    }
    static boolean move(){
        return player1_move() && player2_move();
    }
//    GameMap
    static int map_size;
    static char[][] map;
    static void map_print(){
        for (int r = 0; r < map_size; r++) {
            for (int c = 0; c < map_size; c++)
                System.out.print("" + map[r][c] + " ");
            System.out.println();
        }
    }
    static boolean map_isCellEmpty(int r, int c){
        return map[r][c] == '-';
    }
    static boolean map_putCell(int r, int c, char k){
        if (map_isCellEmpty(r, c)){
            map[r][c] = k;
            return true;
        }
        System.out.println("Поле занято");
        return false;
    }
    static boolean map_isDrow(){
        for (int r = 0; r < map_size; r++)
            for (int c = 0; c < map_size; c++)
                if (map[r][c] == '-') return false;
        System.out.println("ничья");
        return true;
    }
    static boolean map_isWin(char k){
        boolean l1;
        boolean l2 = true, l3 = true;
        for (int i = 0; i < map_size; i++){
            l1 = true;
            for (int j = 0; j < map_size; j++) {
                if (map[i][j] != k) {
                    l1 = false;
                    break;
                }
            }
            if (l1) return true;
            l1 = true;
            for (int j = 0; j < map_size; j++) {
                if (map[j][i] != k) {
                    l1 = false;
                    break;
                }
            }
            if (l1) return true;
            if (map[i][i] != k) l2 = false;
            if (map[i][map_size - i - 1] != k) l3 = false;
        }
        return l2 || l3;
    }
    // тут какой-то бред но кажется работает
    // надо бы довести до ума.
    static int map_getOppositeCount(int r, int c, char k){
        int l = 0, result = 0;
        for (int i = 0; i < map_size; i++) {
            if (map[i][c] == k) {
                l = 0;
                break;
            }
            if ((map[i][c] != k) && (map[i][c]) != '-') l++;
        }
        if (l == map_size - 1) l = map_size;
        result += l;
        l = 0;
        for (int i = 0; i < map_size; i++) {
            if (map[r][i] == k) {
                l = 0;
                break;
            }
            if ((map[r][i] != k) && (map[r][i]) != '-') l++;
        }
        if (l == map_size - 1) l = map_size;
        result += l;
        if (r == c) {
            l = 0;
            for (int i = 0; i < map_size; i++) {
                if (map[i][i] == k) {
                    l = 0;
                    break;
                }
                if ((map[i][i] != k) && (map[i][i]) != '-') l++;
            }
            if (l == map_size - 1) l = map_size;
            result += l;
        }
        if (r == map_size - c - 1) {
            l = 0;
            for (int i = 0; i < map_size; i++) {
                if (map[i][map_size - i - 1] == k) {
                    result = 0;
                    break;
                }
                if ((map[i][map_size - i - 1] != k) && (map[i][map_size - i - 1]) != '-') result++;
            }
            if (l == map_size - 1) l = map_size;
            result += l;
        }
        return result;
    }
//    Player
    static void player1_chooseCell(){
        int r,  c;
        do {
            r = scanInt("Ряд", map_size) - 1;
            c = scanInt("Колонка", map_size) - 1;
        }while (!map_putCell(r, c, 'X'));
    }
    static boolean player1_move(){
        if (map_isDrow()) return false;
        System.out.println("Игрок 1");
        player1_chooseCell();
        map_print();
        if (map_isWin('X')) {
            System.out.println("победа");
            return false;
        }
        return true;
    }
    static boolean player2_move(){
        if (map_isDrow()) return false;
        System.out.println("Игрок 2");
//        playerRnd_chooseCell();
        playerAI_chooseCell();
        map_print();
        if (map_isWin('0')) {
            System.out.println("поражение");
            return false;
        }
        return true;
    }
    static void playerRnd_chooseCell(){
        int r,  c;
        do {
            r = random.nextInt(map_size);
            c = random.nextInt(map_size);
        }while (!map_isCellEmpty(r, c));
        map_putCell(r, c, '0');
        System.out.printf("Ряд %d Колонка %d\n", r, c);
    }
    static void playerAI_chooseCell(){
        int r = 0, c = 0, w = -1;
        for (int i = 0; i < map_size; i++)
            for (int j = 0; j < map_size; j++){
                if (map_isCellEmpty(i, j)){
                    int o = map_getOppositeCount(i, j, '0');
                    if (o > w){
                        w = o;
                        r = i;
                        c = j;
                    }
                }
            }
        map_putCell(r, c, '0');
        System.out.printf("Ряд %d Колонка %d\n", r, c);
    }
}
