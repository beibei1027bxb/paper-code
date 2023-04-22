package train_data_process;

import java.io.*;
import java.util.HashSet;

public class rule3 {
    public static void main(String[] args) throws IOException {
        int count = 0;
        BufferedWriter out = new BufferedWriter(new FileWriter("E:\\CUP\\dataset\\valid_new_rule4_rule1_rule3.jsonl"));
        BufferedReader in = new BufferedReader(new FileReader("E:\\paperCode2\\paperCode2\\result.txt"));
        String str;
        HashSet<String> line_nums = new HashSet<>();
        while ((str = in.readLine()) != null) {
            if (str.contains("Use <suggestion>a</suggestion> instead of 'an' if the following") || str.contains("Possible typo: you repeated a word")
                    || str.contains("Use <suggestion>an</suggestion> instead of 'a' if the following word starts with a vowel sound") ||
                    str.contains("Two consecutive commas") || str.contains("Two determiners in a row") || str.contains("Possible spelling mistake")) {
                String[] split = str.split("#####");
                String line_number = split[split.length - 1];
                line_nums.add(line_number);
            }

        }
        BufferedReader in0 = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_new_rule4_rule1.jsonl"));
        while ((str = in0.readLine()) != null) {
            count++;
            System.out.println(count);
            if (!line_nums.contains(String.valueOf(count))) out.write(str+"\n");
        }
        out.close();
        in.close();
        in0.close();

    }




}
