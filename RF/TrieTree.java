package train_data_process;

import java.io.*;
import java.util.*;

public class TrieTree {
    static class TrieNode {
        Map<Character, TrieNode> map;
        boolean isLeaf;

        public TrieNode() {
            map = new HashMap<>();
            isLeaf = false;
        }

        public TrieNode insert(char c) {
            TrieNode next = map.get(c);
            if (next == null) {
                next = new TrieNode();
                map.put(c, next);
            }
            return next;
        }

        public TrieNode next(char c) {
            return map.get(c);
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                node = node.insert(s.charAt(i));
                if (i == s.length() - 1) node.isLeaf = true;
            }
        }

        public String returnCandidate(String line) {
            TrieNode node = root;
            String candidate = "";
            String str = "";
            for (int i = 0; i < line.length(); i++) {
                if (node.map.get(line.charAt(i)) != null) {
                    str += line.charAt(i);
                    node = node.map.get(line.charAt(i));
                    if (node.isLeaf) candidate = str;

                } else break;
            }
            return candidate;
        }
    }


    public static void main(String[] args) throws IOException {
//        Set<String> ans = new HashSet<>();
//        ans.add("Extract And Move Method"); // in class
//        ans.add("Change Method Access Modifier"); // from class
//        ans.add("Rename Variable"); // from class
//        ans.add("Add Parameter Annotation"); // from class
//        ans.add("Add Method Annotation"); // from class
//        ans.add("Modify Method Annotation"); // from class
//        ans.add("Rename Method"); // in class
//        ans.add("Remove Parameter"); //注意 Remove Parameter --  Remove Parameter Modifier  -- Remove Parameter Annotation 前缀树搜索到不能就直接停
//        //如果还能搜索到 继续搜索 // from class
//        ans.add("Add Thrown Exception Type"); // from class
//        ans.add("Change Return Type"); // from class
//        ans.add("Add Parameter"); // from class
//        ans.add("Remove Method Modifier"); // from class
//        ans.add("Change Variable Type"); // from class
//        ans.add("Remove Method Annotation"); // from class
//        ans.add("Change Thrown Exception Type"); // from class
//        ans.add("Extract Variable"); // from class
//        ans.add("Rename Parameter"); // from class
//        ans.add("Change Parameter Type"); // from class
//        ans.add("Extract Method"); // in class
//        ans.add("Replace Loop With Pipeline"); // from class
//        ans.add("Inline Variable"); // from class
//        ans.add("Move And Rename Method"); // from class
//        ans.add("Move And Inline Method"); // moved from class
//        ans.add("Remove Thrown Exception Type"); // from class
//        ans.add("Modify Variable Annotation"); // from class
//        ans.add("Reorder Parameter"); // from class
//        ans.add("Parameterize Attribute"); // from class
//        ans.add("Replace Attribute With Variable"); // from class
//        ans.add("Add Method Modifier"); // from class
//        ans.add("Add Variable Modifier"); // from class
//        ans.add("Remove Variable Modifier"); // from class
//        ans.add("Add Parameter Modifier"); // from class
//        ans.add("Localize Parameter"); // from class
//        ans.add("Inline Method"); // in class
//        ans.add("Replace Variable With Attribute"); // from class
//        ans.add("Merge Variable"); // from class
//        ans.add("Merge Parameter"); // from class
//        ans.add("Split Parameter"); //from class
//        ans.add("Replace Anonymous With Lambda"); // from class
//        ans.add("Remove Parameter Modifier"); // from class
//        ans.add("Add Variable Annotation"); // from class
//        ans.add("Remove Variable Annotation"); // from class
//        ans.add("Remove Parameter Annotation"); // from class
//        ans.add("Modify Parameter Annotation"); // from class
//        ans.add("Split Variable"); //from class
//        ans.add("Parameterize Variable"); // from class
//        Trie trie = new Trie();
//        // 初始化字典树
//        for (String dict : ans) {
//            trie.insert(dict);
//        }
//        // 过滤掉1.不在46中类型中的数据 2.去除from class/in class等没用的数据
//        BufferedReader in = new BufferedReader(new FileReader("E:\\pythonReptile\\refactoring_valid_2500-end.txt"));
//        BufferedWriter out = new BufferedWriter(new FileWriter("E:\\pythonReptile\\refactoring_valid_2500-end-2.txt"));
//        String str = "";
//        String candidate = "";
//        int count = 0;
//        while ((str = in.readLine()) != null) {
//            count++;
//            System.out.println(count);
//            if (!str.startsWith("----")) {
//                candidate = trie.returnCandidate(str);
//                if (ans.contains(candidate)) {
//                    int index = str.lastIndexOf("moved from class");
//                    index = index == -1 ? str.lastIndexOf("from class") : index;
//                    index = index == -1 ? str.lastIndexOf("in class") : index;
//
//                    out.write(str.substring(0, index).trim() + "\n");
//                }
//            } else out.write(str + "\n");
//        }
//        in.close();
//        out.close();
////        System.out.println(trie.returnCandidate("Replace Anonymous With Lambda .refactor.thread.new Thread.new Runnable with () -> System.out.println(\"hello\") in attribute package thread : Thread from class refactor"));
//        count = 0;
//        BufferedReader in0 = new BufferedReader(new FileReader("E:\\pythonReptile\\refactoring_valid_2500-end-2.txt"));
//        out = new BufferedWriter(new FileWriter("E:\\pythonReptile\\refactoring_valid_2500-end-3.txt"));
//        while ((str = in0.readLine()) != null) {
//            count++;
//            System.out.println(count);
//            if (!str.startsWith("----")) {
//                candidate = trie.returnCandidate(str);
//                if (ans.contains(candidate)) {
//                    if (candidate.equals("Inline Method")) {
//                        int index1 = str.indexOf(":");
//                        int index2 = str.lastIndexOf(":");
//                        if (index1 == index2) {
//                            int index3 = str.indexOf("inlined to");
//                            String sub1 = str.substring(14, index1).trim();
//                            String sub2 = str.substring(index3 + 11).trim();
//                            out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                            continue;
//                        }
//                        int index3 = str.indexOf("inlined to");
//                        String sub1 = str.substring(14, index1).trim();
//                        String sub2 = str.substring(index3 + 11, index2).trim();
//                        out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                    } else if (candidate.equals("Move And Inline Method")) {
//                        int index = str.indexOf(":");
//                        String sub = str.substring(23, index).trim();
//                        out.write(candidate + "#####" + sub + "\n");
//                    } else if (candidate.equals("Rename Method")) {
//                        try {
//                            int index1 = str.indexOf(":");
//                            int index2 = str.lastIndexOf(":");
//                            int index3 = str.indexOf("renamed to");
//                            String sub1 = str.substring(14, index1).trim();
//                            String sub2 = str.substring(index3 + 11, index2).trim();
//                            out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                        } catch (Exception e) {
//                            System.out.println(e + "-----" + count);
//                        }
//
//                    } else if (candidate.equals("Extract And Move Method")) {
//                        try {
//                            int index1 = str.indexOf(":");
//                            int index2 = str.lastIndexOf(":");
//                            int index3 = str.indexOf("extracted from");
//                            String sub1 = str.substring(24, index1).trim();
//                            String sub2 = str.substring(index3 + 15, index2).trim();
//                            out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                        } catch (Exception e) {
//                            System.out.println(e + "-----" + count);
//
//                        }
//
//                    } else if (candidate.equals("Extract Method")) {
//                        int index1 = str.indexOf(":");
//                        int index2 = str.lastIndexOf(":");
//                        if (index1 == index2) {
//                            int index3 = str.indexOf("extracted from");
//                            String sub1 = str.substring(15, index1).trim();
//                            String sub2 = str.substring(index3 + 15).trim();
//                            out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                            continue;
//                        }
//                        int index3 = str.indexOf("extracted from");
//                        String sub1 = str.substring(15, index1).trim();
//                        String sub2 = str.substring(index3 + 15, index2).trim();
//                        out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                    } else if (candidate.equals("Move And Rename Method")) {
//                        int index1 = str.indexOf("from class");
//                        int index2 = str.lastIndexOf(" to ");
//                        String sub1 = str.substring(23, index1).trim();
//                        String sub2 = str.substring(index2 + 4).trim();
//                        out.write(candidate + "#####" + sub1 + "#####" + sub2 + "\n");
//                    } else {
//                        int index1 = str.indexOf("in method");
//                        if (index1 == -1) {
//                            index1 = str.indexOf("in initializer");
//                            out.write(candidate + "#####" + str.substring(15 + index1) + "\n");
//                            continue;
//                        }
//                        int index2 = str.lastIndexOf(":");
//                        String sub1 = "";
//                        if (index2 == -1) sub1 = str.substring(index1 + 10);
//                        else if (index2 != -1 && index2 > index1) sub1 = str.substring(index1 + 10, index2);
//                        else sub1 = str.substring(index1 + 10);
//                        out.write(candidate + "#####" + sub1 + "\n");
//                    }
//                }
//            } else out.write(str + "\n");
//        }
//        in0.close();
//        out.close();
          String str="";
        BufferedReader in0 = new BufferedReader(new FileReader("E:\\pythonReptile\\refactoring_valid_2500-end-3.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("E:\\pythonReptile\\refactoring_valid_2500-end-4.txt"));
        int    count = 0;
        while ((str = in0.readLine()) != null) {
            count++;
            System.out.println(count+"--3");
            if (str.startsWith("---")) {
                out.write(str + "\n");
                continue;
            }

            String[] split = str.split("#####");
            out.write(split[0]);
            for (int i = 1; i < split.length; i++) {
                String methodSingature = split[i];
                int index1 = methodSingature.indexOf("(");
                int index2 = methodSingature.indexOf(")");
                if (index1 == -1 || index2 == -1) {
                    out.write("#####" + methodSingature + "\n");
                    continue;
                }
                String para = methodSingature.substring(index1 + 1, index2);
                String[] params = para.split(", ");
                String string = "";
                String paramters = "";
                for (int j = 0; j < params.length; j++) {
                    String str0 = params[j];
                    if (str0.equals("")) continue;
                    int i1 = str0.indexOf(" ");
                    String lei = str0.substring(i1 + 1);
                    String bian = str0.substring(0, i1);
                    string = lei + " " + bian;
                    paramters += string + " ";
                }
                paramters = paramters.trim();
                out.write("#####" + methodSingature.substring(0, index1 + 1) + paramters + ")");

            }
            out.write("\n");

        }
        in0.close();
        out.close();

    }
    private static boolean isOne(String type, long num) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("Extract Method", 0);
        hashMap.put("Inline Method", 1);
        hashMap.put("Rename Method", 2);
        hashMap.put("Extract And Move Method", 3);
        hashMap.put("Extract Variable", 4);
        hashMap.put("Inline Variable", 5);
        hashMap.put("Parameterize Variable", 6);
        hashMap.put("Rename Variable", 7);

        hashMap.put("Rename Parameter", 8);
        hashMap.put("Replace Variable With Attribute", 9);
        hashMap.put("Merge Variable", 10);
        hashMap.put("Merge Parameter", 11);
        hashMap.put("Split Variable", 12);
        hashMap.put("Split Parameter", 13);
        hashMap.put("Change Variable Type", 14);
        hashMap.put("Change Parameter Type", 15);

        hashMap.put("Change Return Type", 16);
        hashMap.put("Move And Rename Method", 17);
        hashMap.put("Move And Inline Method", 18);
        hashMap.put("Add Method Annotation", 19);
        hashMap.put("Remove Method Annotation", 20);
        hashMap.put("Modify Method Annotation", 21);
        hashMap.put("Add Parameter Annotation", 22);
        hashMap.put("Remove Parameter Annotation", 23);

        hashMap.put("Modify Parameter Annotation", 24);
        hashMap.put("Add Variable Annotation", 25);
        hashMap.put("Remove Variable Annotation", 26);
        hashMap.put("Modify Variable Annotation", 27);
        hashMap.put("Add Parameter", 28);
        hashMap.put("Remove Parameter", 29);
        hashMap.put("Reorder Parameter", 30);
        hashMap.put("Add Thrown Exception Type", 31);

        hashMap.put("Remove Thrown Exception Type", 32);
        hashMap.put("Change Thrown Exception Type", 33);
        hashMap.put("Change Method Access Modifier", 34);
        hashMap.put("Parameterize Attribute", 35);
        hashMap.put("Replace Attribute With Variable", 36);
        hashMap.put("Add Method Modifier", 37);
        hashMap.put("Remove Method Modifier", 38);
        hashMap.put("Add Variable Modifier", 39);

        hashMap.put("Remove Variable Modifier", 40);
        hashMap.put("Add Parameter Modifier", 41);
        hashMap.put("Remove Parameter Modifier", 42);
        hashMap.put("Localize Parameter", 43);
        hashMap.put("Replace Loop With Pipeline", 44);
        hashMap.put("Replace Anonymous With Lambda", 45);

        if (((num >> hashMap.get(type)) & 1) != 0) {
            return false;
        } else return true;

    }

    private static long typeToValue(String type) {
        long l = 1;
        switch (type) {
            case "Extract Method":
                return 1;
            case "Inline Method":
                return 2;
            case "Rename Method":
                return 4;
            case "Extract And Move Method":
                return 8;
            case "Extract Variable":
                return 16;
            case "Inline Variable":
                return 32;
            case "Parameterize Variable":
                return 64;
            case "Rename Variable":
                return 128;
            case "Rename Parameter":
                return 256;
            case "Replace Variable With Attribute":
                return 512;
            case "Merge Variable":
                return 1024;
            case "Merge Parameter":
                return 2048;
            case "Split Variable":
                return 4096;
            case "Split Parameter":
                return 8192;
            case "Change Variable Type":
                return 16384;
            case "Change Parameter Type":
                return 32768;
            case "Change Return Type":
                return 65536;
            case "Move And Rename Method":
                return 131072;
            case "Move And Inline Method":
                return 262144;
            case "Add Method Annotation":
                return 524288;
            case "Remove Method Annotation":
                return 1048576;
            case "Modify Method Annotation":
                return 2097152;
            case "Add Parameter Annotation":
                return 4194304;
            case "Remove Parameter Annotation":
                return 8388608;
            case "Modify Parameter Annotation":
                return 16777216;
            case "Add Variable Annotation":
                return 33554432;
            case "Remove Variable Annotation":
                return 67108864;
            case "Modify Variable Annotation":
                return 134217728;
            case "Add Parameter":
                return 268435456;
            case "Remove Parameter":
                return 536870912;
            case "Reorder Parameter":
                return 1073741824;
            case "Add Thrown Exception Type":
                return l << 31;
            case "Remove Thrown Exception Type":
                return l << 32;
            case "Change Thrown Exception Type":
                return l << 33;
            case "Change Method Access Modifier":
                return l << 34;
            case "Parameterize Attribute":
                return l << 35;
            case "Replace Attribute with Variable":
                return l << 36;
            case "Add Method Modifier":
                return l << 37;
            case "Remove Method Modifier":
                return l << 38;
            case "Add Variable Modifier":
                return l << 39;
            case "Remove Variable Modifier":
                return l << 40;
            case "Add Parameter Modifier":
                return l << 41;
            case "Remove Parameter Modifier":
                return l << 42;
            case "Localize Parameter":
                return l << 43;
            case "Replace Loop With Pipeline":
                return l << 44;
            case "Replace Anonymous With Lambda":
                return l << 45;

            default:
                return 0;
        }
    }

}


