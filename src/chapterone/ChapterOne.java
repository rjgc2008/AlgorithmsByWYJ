package chapterone;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChapterOne {
    /**
     * 欧几里得算法，求最大公约数
     * gcd(greatest common divisor)
     * 递归实现方式
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q){
        if( q == 0)   return p;
        return gcd(q, p % q);
    }

    /**
     * 二分查找法
     * 递归实现
     * 重要：数组a是升序数组
     * @param key
     * @param a
     * @return 如果key存在于数组a中，则返回key存在于数组a中的索引号；如果key不存在于数组a中，则返回-1
     */
    public static int rank(int key, int[] a){
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(int key, int[] a, int lo, int hi){
        if(lo > hi) return -1;

        int mid = (lo + hi) / 2;
        if(key > a[mid]) return rank(key, a, mid + 1, hi);
        if(key < a[mid]) return rank(key, a, lo, hi -1);
        return mid;
    }

    /**
     * 二分查找法
     * 非递归实现
     * 数组a为升级数组
     * @param key
     * @param a
     * @return
     */
    public static int rank2(int key, int[] a) {
        int lo = 0;
        int hi = a.length -1 ;
        int mid = -1;
        while (lo <= hi){
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
    public static void one_one_one(){
        StdOut.println("(0+15)/2= " + ((0 + 15) / 2) );
        StdOut.println("2.0e-6 * 100000000.1= " + (2.0e-6 * 100000000.1) );
        // && 的优先级高于||的优先级，而不是同级
        StdOut.println("true && false || true && false = " + (true && false || true && false));
    }

    public static void one_one_two(){
        StdOut.println("(1 + 2.236) / 2) = " + ((1 + 2.236) / 2));
        StdOut.println("1 + 2 + 3 + 4.0 = " + (1 + 2 + 3 + 4.0));
        StdOut.println("4.1 >= 4 = " + (4.1 >= 4));
        //下面这段语句先执行加法，再转成字符串
        StdOut.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));
    }

    public static void one_one_three() throws FileNotFoundException {
        int a = 1,b = 0,c = -1;
        Scanner sc = new Scanner(new File("120TestData\\tinyT.txt"));
        if (sc.hasNext())
            a = Integer.parseInt(sc.next());
        if (sc.hasNext())
            b = Integer.parseInt(sc.next());
        if (sc.hasNext())
            c = Integer.parseInt(sc.next());
        sc.close();
        if(a == b && b == c)
            StdOut.println("equal!");
        else
            StdOut.println("not equal!");
    }

    public static void one_one_five(double x, double y){
        if((x < 1.0 && x > 0.0) && ( y < 1.0 && y > 0.0))
            StdOut.println("YES");
            else
                StdOut.println("NO");
    }

    public  static  void one_one_six(){
        int f = 0;
        int g = 1;
        for(int i = 0; i < 15; i++){
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }
    }

    /**
     * 想告诉我们是死循环
     * @throws InterruptedException
     */
    public  static void one_one_seven_a() throws InterruptedException {
        double t = 9.0;
        while(Math.abs(t - 9.0 / 5) > .001){
            t = (9.0 / t + t) / 2.0;
            StdOut.println(t);
            Thread.sleep(1000);
        }
        StdOut.printf("%.5f\n",t);
    }

    /**
     * 经典的1+2+...+100
     */
    public static void one_one_seven_b(){
        int sum = 0;
        for(int i = 0; i< 1000; i++)
            for(int j = 0; j < i; j++)
                sum++;
        StdOut.println(sum);
    }

    /**
     * 应该是为了与上题做比较
     */
    public static void one_one_seven_c(){
        int sum = 0;
        for(int i = 1; i < 1000; i++)
            for(int j = 0; j < 1000; j++)
                sum++;
        StdOut.println(sum);
    }

    public static void one_one_eight(){
        //两个char型运算时，自动转换为int型；当char与别的类型运算时，也会先自动转换为int型的，再做其它类型的自动转换
        StdOut.println('b');
        StdOut.println('b' + 'c');
        StdOut.println((char)('a' + 4));
    }

    public static void one_one_nine(){
        int N = 16;

        String s = "";
        int n = N;
        while (n > 0){
                s = n % 2 + s;
                n /=2;
            }
        StdOut.println(s);

        String s2 = "";
        for(int i = N; i > 0; i /=2)
            s2 = (i % 2) + s2;
        StdOut.println(s2);

        String s3 = "";
        s3 = Integer.toBinaryString(N);
        StdOut.println(s3);
    }

    public static  void main(String[] args){
    }
}
