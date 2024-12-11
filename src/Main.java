import java.net.URLEncoder;
import java.util.Scanner;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        if (input.isEmpty()){
            System.out.println("Error");
        }
        else{
            request(input, cin);
        }
    }
    static  String encoder(String string){
        try{
            string = URLEncoder.encode(string, "UTF-8");
            string = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + string;
        } catch (Exception error){
            System.out.print("Error");
            string = " ";
        }
        return string;
    }
    static void request(String adress, Scanner cin){
        adress = encoder(adress);
        InputStream input;
        FileOutputStream output;

    }
}