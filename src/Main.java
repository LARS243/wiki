import java.net.URLEncoder;
import java.util.Scanner;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;


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
    static String encoder(String string){
        try{
            string = URLEncoder.encode(string, "UTF-8");
            string = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + string;
        } catch (Exception error){
            System.out.print("Error");
            string = "";
        }
        return string;
    }
    static void request(String adress, Scanner cin){
        adress = encoder(adress);
        InputStream input = null;
        FileOutputStream output = null;
        try{
            URL url = new URL(adress);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                input = httpURLConnection.getInputStream();

                File file = new File("file.json");
                output = new FileOutputStream(file);
                int byteRead = -1;
                byte[] buffer = new byte[2048];
                while((byteRead = input.read(buffer)) != -1){
                    output.write(buffer, 0, byteRead);
                }
            }
        }catch(IOException e){
            System.out.println("ERROR" + e.toString());
        }finally {
            try {
                input.close();
                output.close();
            } catch (IOException e){}

        }
    }
}

