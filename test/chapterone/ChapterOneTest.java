package chapterone;

import edu.princeton.cs.algs4.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ChapterOneTest {

    /**
     * 二分查找(递归)验证
     */
    @Test
    public void rank() {
        int[] intArrays = new int[10];
        for(int i = 0; i < intArrays.length; i++){
            intArrays[i] = 9 - i;
        }
        Arrays.sort(intArrays);
        StdOut.println(ChapterOne.rank(11, intArrays));
    }

    /**
     * 二分查找(非递归)验证
     */
    @Test
    public void rank2() {
        int[] intArrasys = new int[10];
        for(int i = 0; i < intArrasys.length; i++){
            intArrasys[i] = 9 - i;
        }
        Arrays.sort(intArrasys);
        StdOut.println(ChapterOne.rank2(11,intArrasys));
    }

    /**
     * 场景验证
     * @throws FileNotFoundException
     */
    @Test
    public void rank2_Test() throws FileNotFoundException {
        /**
         * 获取白名单
         */
        ArrayList<Integer> whiteListArrays = new ArrayList<>();
        Scanner sc = new Scanner(new File("120TestData\\tinyW.txt"));
        while (sc.hasNext()){
            String input = sc.nextLine();
            whiteListArrays.add(Integer.parseInt(input));
        }
        sc.close();

        /**
         * 获取比较名单
         */
        ArrayList<Integer> inputListArrays = new ArrayList<>();
        Scanner sc2 = new Scanner(new File("120TestData\\tinyT.txt"));
        while(sc2.hasNext()){
            String input = sc2.nextLine();
            inputListArrays.add(Integer.parseInt(input));
        }
        sc2.close();
        //基于ArrayList<Integer>类型的排序
        whiteListArrays.sort(Comparator.naturalOrder());


        //将白名单转换成int[]
        int[] whitelistArrays2 = whiteListArrays.stream().mapToInt(Integer::valueOf).toArray();
        //将比较名单转换成int[]
        int[] inputListArrays2 = inputListArrays.stream().mapToInt(Integer::valueOf).toArray();

        for(int i = 0; i < inputListArrays.size(); i++){
            if (ChapterOne.rank2(inputListArrays2[i], whitelistArrays2) < 0)
            StdOut.println(inputListArrays2[i]);
        }

    }
    /**
     * 欧几里得算法
     */
    @Test
    public void gcd() {
        int gcdInt = ChapterOne.gcd(1234, 4321);
        StdOut.println(gcdInt);
        int gcdInt2 = ChapterOne.gcd(99, 3333);
        StdOut.println(gcdInt2);
    }

    @Test
    public  void draw_one() throws InterruptedException {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N * N);
        StdDraw.setPenRadius(0.01);
        for(int i = 1; i <= N; i++){
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
        Thread.sleep(10000);
    }

    @Test
    public void draw_two() throws InterruptedException {
        int N = 50;
        double[] a = new double[N];
        for(int i = 0; i < N; i++){
            a[i] = StdRandom.uniform();
        }
        for(int i = 0; i < N; i++){
            double x = 1.0 / N * i;
            double y = a[i] / 2.0;
            double rw = 0.5 / N;
            double rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        Thread.sleep(10000);
    }
    @Test
    public void draw_three() throws InterruptedException {
        int N = 50;
        double[] a = new double[N];
        for(int i = 0; i < N; i++){
            a[i] = StdRandom.uniform();
        }
        Arrays.sort(a);
        for(int i = 0; i < N; i++){
            double x = 1.0 / N * i;
            double y = a[i] / 2.0;
            double rw = 0.5 / N;
            double rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
        Thread.sleep(10000);
    }
    /**
     * 课后习题
     */
    @Test
    public void one_one_one() {
        ChapterOne.one_one_one();
    }

    @Test
    public void one_one_two() {
        ChapterOne.one_one_two();
    }

    @Test
    public void one_one_three() throws FileNotFoundException {
        ChapterOne.one_one_three();
    }

    @Test
    public void one_one_five() {
        ChapterOne.one_one_five(0.5, 0.7);
        ChapterOne.one_one_five(-1, 0.7);
        ChapterOne.one_one_five(0, 0.7);
    }

    @Test
    public void one_one_six() {
        ChapterOne.one_one_six();
    }

    @Test
    public void one_one_seven_a() throws InterruptedException {
        ChapterOne.one_one_seven_a();
    }

    @Test
    public void one_one_seven_b() {
        ChapterOne.one_one_seven_b();
    }

    @Test
    public void one_one_seven_c() {
        ChapterOne.one_one_seven_c();
    }

    @Test
    public void one_one_eight() {
        ChapterOne.one_one_eight();
    }

    @Test
    public void one_one_nine() {
        ChapterOne.one_one_nine();
    }

    @Test
    public void one_one_oneone() {
        ChapterOne.one_one_oneone();
    }

    @Test
    public void one_one_onetwo() {
        ChapterOne.one_one_onetwo();
    }

    @Test
    public void one_one_onethree() {
        ChapterOne.one_one_onethree(3,4);
    }

    @Test
    public void one_one_onethree2() {
        ChapterOne.one_one_onethree2(3,4);
    }

    @Test
    public void one_one_onefour() {
        ChapterOne.one_one_onefour(1023);
    }

    @Test
    public void one_one_onefive() {
        int[] a = new int[10];
        int M = 10;
        int[] b = new int[M];
        for(int i = 0; i < 10; i++)
            a[i] = StdRandom.uniform(10);
        b= ChapterOne.one_one_onefive(a, M);
        for(int i = 0; i < a.length; i++)
            StdOut.println(b[i]);
    }

    @Test
    public void exR1() {
        String str = ChapterOne.exR1(6);
        StdOut.println(str);
    }

    @Test
    public void mystery() {
        int num1 = ChapterOne.mystery(2,25);
        int num2 = ChapterOne.mystery(3, 11);
        StdOut.println("myster(2, 25) = " + num1);
        StdOut.println("myster(3, 11) = " + num2);
    }

    @Test
    public void mystery2() {
        int num1 = ChapterOne.mystery2(2,25);
        int num2 = ChapterOne.mystery2(3, 11);
        StdOut.println("myster2(2, 25) = " + num1);
        StdOut.println("myster2(3, 11) = " + num2);
    }

    @Test
    public void f() {
        for(int N = 0; N < 100; N++)
            StdOut.println(N + " " + ChapterOne.F(N));
    }

    @Test
    public void f2() {
        for(int N = 0; N < 100; N++)
            StdOut.println(N + " " + ChapterOne.F2(N));
    }

    @Test
    public void one_one_twozero() {
        int N = 20;
        double num = ChapterOne.one_one_twozero(N);
        StdOut.println("ln(" + N + "!) = " + num);
    }

    @Test
    public void one_one_twoone() throws FileNotFoundException {
        ChapterOne.one_one_twoone(4);
    }

    @Test
    public void one_one_twotwo() {
        int mid = ChapterOne.one_one_twotwo();
        StdOut.println("mid = " + mid);
    }

    @Test
    public void one_one_twothree() throws FileNotFoundException {
        ChapterOne.one_one_twothree("+");
        StdOut.println("===============");
        ChapterOne.one_one_twothree("-");
    }

    @Test
    public void one_one_twofour() {
        ChapterOne.one_one_twofour();
    }

    @Test
    public void one_one_twoseven() {
        ChapterOne.one_one_twoseven();
    }
}