import java.net.URLEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        input = encoder(input);
        System.out.print(input);
    }

    static  String encoder(String str){
        try{
            str = URLEncoder.encode(str, "UTF-8");
        } catch (Exception error){
            System.out.print("Error");
            str = " ";
        }
        return str;
    }
}