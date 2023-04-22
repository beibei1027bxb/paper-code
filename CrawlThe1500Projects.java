package 方法调用检测;

import java.io.*;

public class CrawlThe1500Projects {
    public static void main(String[] args) throws IOException {
        String file_content = "C:\\Users\\admin\\Desktop\\1\\1500.txt";

        File file0 = new File(file_content);
        FileReader fr0 = new FileReader(file0);
        BufferedReader br0 = new BufferedReader(fr0);
        br0.readLine();

        File file1 = new File("C:\\Users\\admin\\Desktop\\1\\1500-1.txt");
        FileWriter fr1 = new FileWriter(file1);
        BufferedWriter br1 = new BufferedWriter(fr1);
        String str;
        while ((str=br0.readLine())!=null){
            String[] parts = str.split(",");
            String url = parts[1].replace("\"","").replace("https://github.com/","git@github.com:") ;
            br1.write(url+"\n");

        }
        br0.close();
        br1.close();
    }
}
