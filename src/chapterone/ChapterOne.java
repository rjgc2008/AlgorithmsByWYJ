package chapterone;

import edu.princeton.cs.algs4.StdOut;

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
    public static void one_one_one(){
        StdOut.println("(0+15)/2= " + ((0 + 15) / 2) );
        StdOut.println("2.0e-6 * 100000000.1= " + (2.0e-6 * 100000000.1) );
        // && 的优先级高于||的优先级，而不是同级
        StdOut.println("true && false || true && false = " + (true && false || true && false));
    }

    public static  void main(String[] args){
    }
}
