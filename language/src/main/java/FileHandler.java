import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public static List<String> handle(String fileName) {
        List<String> files = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        String content = "";
        String share = "";
        boolean flag = true;
        try {
        BufferedWriter bw = null;
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("interface.py"));
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
            do {
                content = bf.readLine();
                if (content == null) {
                    if (bw != null){
                        bw.write(sb.toString());
                        bw.close();
                    }
                    break;
                }
                if (content.startsWith("@agent")) {
                    if (flag) {
                        flag = false;
                        share = sb.toString();
                    }
                    if (bw != null){
                        bw.write(sb.toString());
                        bw.close();
                    }
                    String name = content.substring(content.indexOf("(") + 1, content.indexOf(")"));
                    files.add(name);
                    bw = new BufferedWriter(new FileWriter(name + ".krd"));
                    sb = new StringBuffer(share + "\n");
                    continue;
                }
                sb.append(content).append("\n");
            }while (true);

        } catch (IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return files;
    }
}
