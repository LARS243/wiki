import java.net.URLEncoder;
import java.util.Scanner;
import java.net.URI;
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
    static void request(String adress, Scanner cin) {
        adress = encoder(adress);
        InputStream input = null;
        FileOutputStream output = null;
        try {
            URL url = new URL(adress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                input = httpURLConnection.getInputStream();

                File file = new File("file.json");
                output = new FileOutputStream(file);
                int byteRead = -1;
                byte[] buffer = new byte[2048];
                while ((byteRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, byteRead);
                }
            }
        } catch (IOException error) {
            System.out.println("Error");
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException error) {
            }

        }
        Parser parser = new Parser();
        Root resualt_parsing = parser.parse();
        System.out.println("Результаты: ");
        if (resualt_parsing.json_array.elemnet.isEmpty()) {
            System.out.println("Error");
            return;
        }
        for (int i = 0; i < resualt_parsing.json_array.elemnet.size(); i++) {
            System.out.printf("%d - %s\n", i, resualt_parsing.json_array.elemnet.get(i).title);
        }
        System.out.print("Выберите страницу: ");
        int command = cin.nextInt();
        openPage(command, resualt_parsing);
    }
    static void openPage(int command, Root resualt_parsing){
        if (command < resualt_parsing.json_array.elemnet.size()){
            try{
                URI page = new URI("https://ru.wikipedia.org/w/index.php?curid=" + resualt_parsing.json_array.elemnet.get(command).pageid);
                java.awt.Desktop.getDesktop().browse(page);
            }catch (Exception error){
                System.out.println("Error");
            }
        }
        else{
            System.out.println("Error");
        }
    }
}

