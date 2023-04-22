package train_data_process;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class rule2 {

    public static void main(String[] args) throws IOException {
        int count=0;
        int sum=0;
        BufferedWriter out = new BufferedWriter(new FileWriter("train.txt"));
        BufferedReader in = new BufferedReader(new FileReader("E:\\pythonReptile\\text.txt"));
        String str;
        ArrayList<Integer> line_nums=new ArrayList<>();
        while ((str = in.readLine()) != null) {
            count++;
            String[] methods = str.split("#####");
            diff_match_patch dmp = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> diff = dmp.diff_main(methods[0],methods[1]);
            // Result: [(-1, "Hell"), (1, "G"), (0, "o"), (1, "odbye"), (0, " World.")]
            dmp.diff_cleanupSemantic(diff);

            // Result: [(-1, "Hello"), (1, "Goodbye"), (0, " World.")]
            boolean flag = false;
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.clear();
            for (diff_match_patch.Diff d :
                    diff) {
                if (d.operation == diff_match_patch.Operation.DELETE) {
                    flag = false;
                    arrayList.clear();
                    break;
                }
                if (d.operation == diff_match_patch.Operation.INSERT) {
                    arrayList.add(d.text);
                }

            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (!arrayList.get(i).trim().startsWith("@")) {
                    flag = false;
                    break;
                }
                if (arrayList.get(i).trim().startsWith("@")) {
                    flag = true;
                    sum++;
                    line_nums.add(count);
                    System.out.println(count);
                }
            }
            if (flag == false) {
                out.write(methods[0]+"#####"+methods[1]+"\n");

            }


        }
        out.close();
        in = new BufferedReader(new FileReader("E:\\JIT dataset\\dataset\\train.jsonl"));
        BufferedWriter out0 = new BufferedWriter(new FileWriter("E:\\JIT dataset\\dataset\\train_rule2.jsonl"));
        count=0;
        int m=0;
        while ((str = in.readLine()) != null) {
            count++;
            if (!line_nums.contains(count)) out0.write(str+"\n");
            else m++;

        }

        out0.close();
        in.close();
        System.out.println(sum);
        System.out.println(line_nums.size());

    }

}
