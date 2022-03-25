package chapterone;

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

    public void oneOneOne(){
        System.out.println("( 0 + 15) / 2 = " + ((0 + 15) / 2) );
        System.out.println((0 + 15) / 2);
    }

    public static  void main(String[] args){
        new ChapterOne().oneOneOne();
    }
}
