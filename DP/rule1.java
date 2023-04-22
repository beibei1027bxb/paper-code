package train_data_process;

import java.io.*;
import java.util.HashSet;

public class rule1 {
    public static void main(String[] args) throws IOException {
        int count=0;
        BufferedWriter out = new BufferedWriter(new FileWriter("E:\\CUP\\dataset\\valid_new_rule4_rule1.jsonl"));
        BufferedReader in1 = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_le==1.txt"));
        BufferedReader in2 = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_le==1_justInsertOneSpace.txt"));
        BufferedReader in3 = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_le==2.txt"));
        BufferedReader in = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_new_rule4.jsonl"));
        String str;
        HashSet<String> line_nums=new HashSet<>();
        while ((str = in1.readLine()) != null) {
            String[] split = str.split("#####");
            String line_number=split[split.length-1];
            line_nums.add(line_number);
        }

        while ((str = in2.readLine()) != null) {
            String[] split = str.split("#####");
            String line_number=split[split.length-1];
            line_nums.add(line_number);
        }
        while ((str = in3.readLine()) != null) {
            String[] split = str.split("#####");
            String line_number=split[split.length-1];
            line_nums.add(line_number);
        }
        while ((str = in.readLine()) != null) {
            count++;
            if (!line_nums.contains(String.valueOf(count))){
                out.write(str+"\n");
            }
        }
        out.close();
        in.close();
        in1.close();
        in2.close();
        in3.close();

    }
}
