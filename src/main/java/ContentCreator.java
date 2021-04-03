import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContentCreator {
    private String text = "";

    private ArrayList<String> readFile(String path) {
        ArrayList<String> headers = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path));) {
            int c;
            String s = "";
            boolean isHeader = false;
            while ((c=reader.read()) != -1) {
                text += (char) c;
                if ((char) c == '#') isHeader = true;
                if ((char) c == '\n') {
                    if (isHeader) headers.add(s);
                    isHeader = false;
                    s = "";
                }
                if (isHeader) {
                    s += (char) c;
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return headers;
    }

    private ArrayList<Integer> countHeader(ArrayList<String> headers) {
        ArrayList<Integer> headNum = new ArrayList<>();

        for (String s : headers) {
            char[] charTxt = s.toCharArray();
            int counter = 0;
            for (char c : charTxt) {
                if (c == ' ') {
                    break;
                }
                counter++;
            }
            headNum.add(counter);
        }

        return headNum;
    }

    private String getTabulations(int num) {
        String res = "";

        for (int i = 0; i < num; i++) {
            res += "\t";
        }
        return res;
    }

    public String createContent(String pathToFile) {
        ArrayList<String> headers;
        ArrayList<Integer> headNum;

        headers = readFile(pathToFile);
        headNum = countHeader(headers);

        String result = "";
        int j = 1;
        Map<Integer, Integer> prevNumForHeaders = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            if (i > 0) {
                if (prevNumForHeaders.containsKey(headNum.get(i))) {
                    prevNumForHeaders.replace(headNum.get(i), j);
                } else prevNumForHeaders.put(headNum.get(i), j);

                if (headNum.get(i).equals(headNum.get(i - 1))) {
                    j++;
                } else{
                    if (headNum.get(i) > headNum.get(i - 1)) j = 1;
                    else j = prevNumForHeaders.get(headNum.get(i)) + 1;
                }
            }

            String textWithoutHash = headers.get(i).substring(headNum.get(i) + 1, headers.get(i).length() - 1);
            boolean isExtraInfo = false;
            String textWithoutExtraInfo = "";
            if (textWithoutHash.charAt(0) == '[') {
                isExtraInfo = true;
                int k = 1;
                char c = textWithoutHash.charAt(k);
                while (c != ']') {
                    textWithoutExtraInfo += c;
                    k++;
                    c = textWithoutHash.charAt(k);
                }
            }

            String tmp = (textWithoutHash.toLowerCase()).replace(' ', '-');
            if (isExtraInfo) {
                result += getTabulations(headNum.get(i) - 1) + j + ". [" +
                        textWithoutExtraInfo + "]" + "(#" + tmp + ")\n";
            }
            else {
                result += getTabulations(headNum.get(i) - 1) + j + ". [" +
                        textWithoutHash + "]" + "(#" + tmp + ")\n";
            }
        }

        result += "\n" + text;
        return result;
    }
}
