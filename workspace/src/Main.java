import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String result = "";
        for (int i = 0; i < str.length();i++){
            if (Character.isLowerCase(str.charAt(i))){
                result += Character.toUpperCase(str.charAt(i));
            }
            if (str.charAt(i)>='0'&&str.charAt(i)<='9'){
                result += str.charAt(i);
            }
            if (Character.isUpperCase(str.charAt(i))){
                result += Character.toLowerCase(str.charAt(i));
            }
        }
        System.out.println(result);
    }
}
