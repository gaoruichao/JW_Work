//201802104068高睿超
package util;

public class Helper {
    public static String quote(String str){
        return "\"" + str + "\"";
    }

    public static void main(String[] args) {
        System.out.println(Helper.quote("schools:"));
    }
}
