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

    public static  void main(String[] args){
    }
}
