package chapterone;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChapterOne {
    /**
     * 欧几里得算法，求最大公约数
     * gcd(greatest common divisor)
     * 递归实现方式
     *
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }

    /**
     * 欧几里得算法，求最大公约数
     * 改进：打印出每次调用方法时的p,q值
     * gcd(greatest common divisor)
     * 递归实现方式
     *
     * @param p
     * @param q
     * @return
     */
    public static int gcd2(int p, int q) {
        StdOut.println(p + "," + q);
        if (q == 0) return p;
        return gcd2(q, p % q);
    }

    /**
     * 二分查找法
     * 递归实现
     * 重要：数组a是升序数组
     *
     * @param key
     * @param a
     * @return 如果key存在于数组a中，则返回key存在于数组a中的索引号；如果key不存在于数组a中，则返回-1
     */
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = (lo + hi) / 2;
        if (key > a[mid]) return rank(key, a, mid + 1, hi);
        if (key < a[mid]) return rank(key, a, lo, mid - 1);
        return mid;
    }

    /**
     * 二分查找法
     * 递归实现，记录调用次数及每次调用时的游标
     * 重要：数组a是升序数组
     *
     * @param key
     * @param a
     * @return 如果key存在于数组a中，则返回key存在于数组a中的索引号；如果key不存在于数组a中，则返回-1
     */
    public static int rank(int key, int[] a, int depth) {
        return rank(key, a, 0, a.length - 1, 1);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth) {
        if (lo > hi) return -1;
        StdOut.println("current depth is " + depth + ", lo is " + lo + ", hi is " + hi);

        int mid = (lo + hi) / 2;
        if (key > a[mid]) return rank(key, a, mid + 1, hi, depth + 1);
        if (key < a[mid]) return rank(key, a, lo, mid - 1, depth + 1);
        return mid;
    }

    /**
     * 二分查找法
     * 非递归实现
     * 数组a为升级数组
     *
     * @param key
     * @param a
     * @return
     */
    public static int rank2(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = -1;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else if (key == a[mid]) return mid;
        }
        return -1;
    }

    /**
     * 课后习题
     */
    public static void one_one_one() {
        StdOut.println("(0+15)/2= " + ((0 + 15) / 2));
        StdOut.println("2.0e-6 * 100000000.1= " + (2.0e-6 * 100000000.1));
        // && 的优先级高于||的优先级，而不是同级
        StdOut.println("true && false || true && false = " + (true && false || true && false));
    }

    public static void one_one_two() {
        StdOut.println("(1 + 2.236) / 2) = " + ((1 + 2.236) / 2));
        StdOut.println("1 + 2 + 3 + 4.0 = " + (1 + 2 + 3 + 4.0));
        StdOut.println("4.1 >= 4 = " + (4.1 >= 4));
        //下面这段语句先执行加法，再转成字符串
        StdOut.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));
    }

    public static void one_one_three() throws FileNotFoundException {
        int a = 1, b = 0, c = -1;
        Scanner sc = new Scanner(new File("120TestData\\tinyT.txt"));
        if (sc.hasNext())
            a = Integer.parseInt(sc.next());
        if (sc.hasNext())
            b = Integer.parseInt(sc.next());
        if (sc.hasNext())
            c = Integer.parseInt(sc.next());
        sc.close();
        if (a == b && b == c)
            StdOut.println("equal!");
        else
            StdOut.println("not equal!");
    }

    public static void one_one_five(double x, double y) {
        if ((x < 1.0 && x > 0.0) && (y < 1.0 && y > 0.0))
            StdOut.println("YES");
        else
            StdOut.println("NO");
    }

    public static void one_one_six() {
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }
    }

    /**
     * 想告诉我们是死循环
     *
     * @throws InterruptedException
     */
    public static void one_one_seven_a() throws InterruptedException {
        double t = 9.0;
        while (Math.abs(t - 9.0 / 5) > .001) {
            t = (9.0 / t + t) / 2.0;
            StdOut.println(t);
            Thread.sleep(1000);
        }
        StdOut.printf("%.5f\n", t);
    }

    /**
     * 经典的1+2+...+100
     */
    public static void one_one_seven_b() {
        int sum = 0;
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < i; j++)
                sum++;
        StdOut.println(sum);
    }

    /**
     * 应该是为了与上题做比较
     */
    public static void one_one_seven_c() {
        int sum = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                sum++;
        StdOut.println(sum);
    }

    public static void one_one_eight() {
        //两个char型运算时，自动转换为int型；当char与别的类型运算时，也会先自动转换为int型的，再做其它类型的自动转换
        StdOut.println('b');
        StdOut.println('b' + 'c');
        StdOut.println((char) ('a' + 4));
    }

    public static void one_one_nine() {
        int N = 16;

        String s = "";
        int n = N;
        while (n > 0) {
            s = n % 2 + s;
            n /= 2;
        }
        StdOut.println(s);

        String s2 = "";
        for (int i = N; i > 0; i /= 2)
            s2 = (i % 2) + s2;
        StdOut.println(s2);

        String s3 = "";
        s3 = Integer.toBinaryString(N);
        StdOut.println(s3);
    }

    public static void one_one_oneone() {
        int[][] a = new int[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                a[i][j] = StdRandom.uniform(1, 100) - 50;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (a[i][j] > 0)
                    StdOut.println("a[" + i + "][" + j + "] = +");
                else if (a[i][j] < 0)
                    StdOut.println("a[" + i + "][" + j + "] = -");
            }
    }

    public static void one_one_onetwo() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        for (int i = 0; i < 10; i++)
            StdOut.println("a[" + i + "] = " + a[i]);
    }

    public static void one_one_onethree(int M, int N) {
        int[][] a = new int[M][N];
        int[][] b = new int[N][M];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = StdRandom.uniform(100);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                b[i][j] = a[j][i];
            }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                StdOut.println("a[" + j + "][" + i + "] = " + a[j][i] + "; b[" + i + "][" + j + "] = " + b[i][j]);
    }

    public static void one_one_onethree2(int M, int N) {
        int[][] a = new int[M][N];
        int[][] b = new int[N][M];
        String[] stra = new String[M];
        String[] strb = new String[N];
        for (int i = 0; i < M; i++)
            stra[i] = "";
        for (int i = 0; i < N; i++)
            strb[i] = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.uniform(100);
                stra[i] = stra[i] + "\t" + a[i][j];
            }
            stra[i] = stra[i] + "\n";
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = a[j][i];
                strb[i] = strb[i] + "\t" + b[i][j];
            }
            strb[i] = strb[i] + "\n";
        }
        StdOut.println("initial arrays:");
        for (int i = 0; i < M; i++)
            StdOut.println(stra[i]);
        StdOut.println("transpose arrays:");
        for (int i = 0; i < N; i++)
            StdOut.println(strb[i]);
    }

    public static void one_one_onefour(int N) {
        int M = 0;
        while (N / 2 > 0) {
            M++;
            N /= 2;
        }
        StdOut.println(M);
    }

    public static int[] one_one_onefive(int[] a, int M) {
        int[] b = new int[M];
        for (int num : a) {
            if (num > -1 && num < M)
                b[num]++;
        }
        return b;
    }

    /**
     * one_one_onesix
     *
     * @param n
     * @return
     */
    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    /**
     * one_one_oneeight
     *
     * @param a
     * @param b
     * @return
     */
    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery2(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a * a, b / 2);
        return mystery2(a * a, b / 2) * a;
    }

    /**
     * one_one_inenine
     *
     * @param N
     * @return
     */
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long F2(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        long[] num = new long[N];
        num[0] = 0;
        num[1] = 1;
        for (int i = 2; i < N; i++)
            num[i] = num[i - 1] + num[i - 2];
        return num[N - 1];
    }

    public static double one_one_twozero(int N) {
        if (N <= 0) return -1;
        long multiply = 1;
        for (int i = 1; i <= N; i++) {
            multiply *= i;
            StdOut.println("N = " + i + ",multiply = " + multiply);
        }
        return Math.log(multiply);
    }

    public static void one_one_twoone(int N) throws FileNotFoundException {
        String[][] scores_array = new String[N][4];
        Scanner sc = new Scanner(new File("120TestData\\one_one_twoone.txt"));
        int i = 0;//行游标
        int j = 0;//列游标
        String[] tmp_array;//临时数组，记录读取每行，分割后的数组值
        while (sc.hasNextLine()) {
            j = 0;//列数游标归零
            tmp_array = sc.nextLine().split(" ");//读取每行并分割成数组
            for (String str : tmp_array) {
                scores_array[i][j++] = str;
            }
            scores_array[i][j] = String.valueOf(Double.parseDouble(tmp_array[1]) / Double.parseDouble(tmp_array[2]));
            i++;//行游标加一
        }

        for (String strs[] : scores_array)
            StdOut.printf("%10s,%10s,%10s,%10.3f\n", strs[0], strs[1], strs[2], Double.parseDouble(strs[3]));
    }

    public static int one_one_twotwo() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++)
            a[i] = StdRandom.uniform(100);
        Arrays.sort(a);
        return ChapterOne.rank(33, a, 1);
    }

    public static void one_one_twothree(String sign_String) throws FileNotFoundException {
        ArrayList<Integer> whitelist_ArrayList = new ArrayList<>();
        int[] whitelist_IntArray;

        Scanner whitelist_sc = new Scanner(new File("120TestData\\tinyW.txt"));
        while (whitelist_sc.hasNext())
            whitelist_ArrayList.add(Integer.parseInt(whitelist_sc.next()));
        whitelist_sc.close();

        whitelist_IntArray = new int[whitelist_ArrayList.size()];
        whitelist_IntArray = whitelist_ArrayList.stream().mapToInt(Integer::valueOf).toArray();

        Arrays.sort(whitelist_IntArray);//对int数组进行排序

        ArrayList<Integer> compare_ArrayList = new ArrayList<>();
        int[] compare_IntArray;
        Scanner compare_sc = new Scanner(new File("120TestData\\tinyT.txt"));
        while (compare_sc.hasNext())
            compare_ArrayList.add(Integer.parseInt(compare_sc.next()));
        compare_sc.close();
        compare_IntArray = compare_ArrayList.stream().mapToInt(Integer::valueOf).toArray();

        for (int i : compare_IntArray) {
            int result = rank(i, whitelist_IntArray);
            if ((result == -1) && (sign_String.equals("+"))) StdOut.println(i);
            else if ((result != -1) && (sign_String.equals("-"))) StdOut.println(i);
        }

    }

    public static void one_one_twofour() {
        int a1_int = gcd2(105, 24);
        int a2_int = gcd2(1111111, 1234567);
    }

    public static double binomial(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    public static double[][] value_doubleArray = new double[101][51];

    /**
     * 逻辑：
     * @param N
     * @param k
     * @param p
     * @return
     */
    public static double binomialArray(int N, int k, double p) {
        if (N == 0 && k == 0) {
            if (ChapterOne.value_doubleArray[N][k] != -1) return value_doubleArray[N][k];
            else {
                ChapterOne.value_doubleArray[N][k] = 1.0;
                return value_doubleArray[N][k];
            }
        }
        if (N < 0 || k < 0) return 0.0;
        if (ChapterOne.value_doubleArray[N][k] != -1 ) return ChapterOne.value_doubleArray[N][k];
        else ChapterOne.value_doubleArray[N][k] = (1.0 - p) * binomialArray(N - 1, k, p) + p * binomialArray(N -1, k - 1, p);
        return ChapterOne.value_doubleArray[N][k];


    }

    public static void one_one_twoseven() {
//        double d_double = binomial(10, 5, 0.25);
//        double d_double = binomial(100, 50, 0.25);
//        StdOut.println(d_double);

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 51; j++) {
                ChapterOne.value_doubleArray[i][j] = -1;
            }
        double d_double = binomialArray(100, 50, 0.25);
            StdOut.println(d_double);
    }



    public static void main(String[] args) {
    }
}
