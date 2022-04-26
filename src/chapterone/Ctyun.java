package chapterone;

public class Ctyun {
    public static void main(String[] args){

        GaussDbUtil ctyun = new GaussDbUtil("Ctyun");
        ctyun.printInfo();
        ctyun.closeAll();
    }
}
