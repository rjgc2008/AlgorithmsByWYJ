package chapterone;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ChapterOneTest {

    @Test
    public void rank() {
        int[] intArrasys = new int[10];
        for(int i = 0; i < intArrasys.length; i++){
            intArrasys[i] = 9 - i;
        }
        Arrays.sort(intArrasys);
        StdOut.println(ChapterOne.rank(3, intArrasys));
    }

    @Test
    public void gcd() {
        int gcdInt = ChapterOne.gcd(1234, 4321);
        StdOut.println(gcdInt);
        int gcdInt2 = ChapterOne.gcd(99, 3333);
        StdOut.println(gcdInt2);
    }
}