import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        ContentCreator contentCreator = new ContentCreator();

//        src/main/resources/first.md
//        src/main/resources/second.md
        System.out.println("Enter the path to the md file");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathToRead = reader.readLine();
        String res = contentCreator.createContent(pathToRead);

        if (res.length() <= 1) {
            System.out.println("Произошла ошибка");
            return;
        }

        System.out.println(res);

        System.out.println("\nSave the result to a file? (y/n)");
        String s = reader.readLine();
        while (!s.equals("y") && !s.equals("n")) {
            System.out.println("Enter \"y\" or \"n\"!");
            s = reader.readLine();
        }

        if (s.equals("y")) {
            System.out.println("Enter the path to the file");
            String pathToWrite = reader.readLine();

            try (FileWriter writer = new FileWriter(pathToWrite, false)) {
                writer.write(res);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            System.out.println("Success!");
        }
    }
}
