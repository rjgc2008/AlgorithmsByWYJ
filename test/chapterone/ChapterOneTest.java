package chapterone;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ChapterOneTest {

    /**
     * 二分查找(递归)验证
     */
    @Test
    public void rank() {
        int[] intArrasys = new int[10];
        for(int i = 0; i < intArrasys.length; i++){
            intArrasys[i] = 9 - i;
        }
        Arrays.sort(intArrasys);
        StdOut.println(ChapterOne.rank(11, intArrasys));
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
    public void one_one_one() {
        ChapterOne.one_one_one();
    }
}