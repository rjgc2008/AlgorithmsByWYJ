public class GCD {
    public static int gcd(int p, int q){
        if( q == 0)
            return p;
        return gcd(q, p % q);
    }
    public static void main(String[] args){
        int gcd = GCD.gcd(111350, 2345);
        System.out.println("gcd is " + gcd);
    }
}
