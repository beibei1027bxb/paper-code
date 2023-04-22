import java.io.*;
import java.util.ArrayList;

public class changeOfOneMethod {
    public static void main(String[] args) throws IOException {
        String file_content = "MethodLineNuminOneFile.txt";

        File file0 = new File(file_content);
        FileReader fr0 = new FileReader(file0);
        BufferedReader br0 = new BufferedReader(fr0);
        String line0="";
        ArrayList<ArrayList<Integer>> arrayLists=new ArrayList<>();
        String last="";
        ArrayList<String> file_names=new ArrayList<>();
        while ((line0 = br0.readLine()) != null){
                String file_path=line0.substring(0,line0.indexOf("-->"));
                if (!file_path.equals(last)){
                    arrayLists.add(new ArrayList<Integer>());
                    file_names.add(file_path);
                }
                last=file_path;
                ArrayList<Integer> arrays=arrayLists.get(arrayLists.size()-1);
                String[] split1 = line0.split("\t");
                String start_line_num=split1[split1.length-1];
                line0 = br0.readLine();
                String[] split2 = line0.split("\t");
                String end_line_num=split2[split2.length-1];
                arrays.add(Integer.valueOf(start_line_num));
                arrays.add(Integer.valueOf(end_line_num));
        }
        File changeOfMethods =new File("changeOfMethods.txt");
        if(!changeOfMethods.exists()){
            changeOfMethods.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(changeOfMethods.getName(),false);
        BufferedWriter out_fileWritter = new BufferedWriter(fileWritter);
        for (int i = 0; i < arrayLists.size(); i++) {
            String file_content_delete = "delete.txt";
            File file00 = new File(file_content_delete);
            FileReader fr00 = new FileReader(file00);
            BufferedReader br00 = new BufferedReader(fr00);
            String line00="";
            while ((line00 = br00.readLine()) != null){
                String tmp=line00.substring(0,line00.indexOf("-->"));
                String file_path=tmp.substring(tmp.lastIndexOf("/")+1);

                int index1=line00.indexOf("-->");
                int index2=line00.indexOf(":");
                float line_num=Float.valueOf(line00.substring(index1+3,index2));
                String content=line00.substring(index2+1);
                for (int j = 0; j < arrayLists.get(i).size(); j++) {
                    if (!file_path.equals(file_names.get(i))) break;
                    if (line_num<arrayLists.get(i).get(j)&&file_path.equals(file_names.get(i))) {
                        j++;
                        break;
                    }
                    if (line_num>=arrayLists.get(i).get(j)&& line_num<=arrayLists.get(i).get(j+1) && file_path.equals(file_names.get(i))){
                        out_fileWritter.write(file_path+"-->"+"delete:"+arrayLists.get(i).get(j)+"\t"+line_num+"\t"+content+"\n");

                        j++;
                    }

                }

            }
            br0.close();
            String file_content_replace = "replace.txt";
            File file1 = new File(file_content_replace);
            FileReader fr1 = new FileReader(file1);
            BufferedReader br1 = new BufferedReader(fr1);
            String line1="";

            while ((line1 = br1.readLine()) != null){
                int index1=line1.indexOf("-->");
                int index2=line1.indexOf(":");
                String[] split = line1.substring(0, index1).split("\t");
                String oldContent=split[0];
                String newContent = line1.substring(index2 + 1);
                float line_num=Float.valueOf(line1.substring(index1+3,index2));
                String tmp=line1.substring(0,line1.indexOf("-->"));
                String file_path=tmp.substring(tmp.lastIndexOf("/")+1);
                for (int j = 0; j < arrayLists.get(i).size(); j++) {
                    if (!file_path.equals(file_names.get(i))) break;
                    if (line_num<arrayLists.get(i).get(j)) {
                        j++;
                        break;
                    }
                    if (line_num>=arrayLists.get(i).get(j)&&line_num<=arrayLists.get(i).get(j+1)){
                        out_fileWritter.write(file_path+"-->"+"replace:"+arrayLists.get(i).get(j)+"\t"+line_num+"\t"+oldContent+"\t"+newContent+"\n");
                        j++;
                    }

                }

            }
            br1.close();
            String file_content_add = "add.txt";
            File file2 = new File(file_content_add);
            FileReader fr2 = new FileReader(file2);
            BufferedReader br2 = new BufferedReader(fr2);
            String line2="";
            while ((line2 = br2.readLine()) != null) {
                int index1=line2.indexOf("-->");
                int index2=line2.indexOf(":");
                float line_num=Float.valueOf(line2.substring(index1+3,index2));
                String content=line2.substring(index2+1);
                String tmp=line2.substring(0,line2.indexOf("-->"));
                String file_path=tmp.substring(tmp.lastIndexOf("/")+1);
                for (int j = 0; j < arrayLists.get(i).size(); j++) {
                    if (!file_path.equals(file_names.get(i))) break;
                    if (line_num<arrayLists.get(i).get(j)) {
                        j++;
                        break;
                    }
                    if (line_num>=arrayLists.get(i).get(j)&&line_num<=arrayLists.get(i).get(j+1)){
                        out_fileWritter.write(file_path+"-->"+"add:"+arrayLists.get(i).get(j)+"\t"+line_num+"\t"+content+"\n");
                        j++;
                    }

                }

            }
        }
        out_fileWritter.close();

    }
}
