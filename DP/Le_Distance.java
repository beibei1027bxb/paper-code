package train_data_process;

import java.io.*;

public class Le_Distance {
    public static int getStringDistance(String s1, String s2) {

        int distance[][];// 定义距离表
        int s1_len = s1.length();
        int s2_len = s2.length();

        if (s1_len == 0) {
            return s2_len;
        }
        if (s2_len == 0) {
            return s1_len;
        }
        distance = new int[s1_len + 1][s2_len + 1];

        // 二维数组第一行和第一列放置自然数
        for (int i = 0; i <= s1_len; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= s2_len; j++) {
            distance[0][j] = j;
        }
        // 比较，若行列相同，则代价为0，否则代价为1；
        for (int i = 1; i <= s1_len; i++) {
            char s1_i = s1.charAt(i - 1);
            // 逐一比较
            for (int j = 1; j <= s2_len; j++) {
                char s2_j = s2.charAt(j - 1);
                // 若相等，则代价取0；直接取左上方值
                if (s1_i == s2_j) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    // 否则代价取1，取左上角、左、上 最小值 + 代价（代价之和便是最终距离）
                    distance[i][j] = getMin(distance[i - 1][j], distance[i][j - 1], distance[i - 1][j - 1]) + 1;
                }
            }
        }
        // 取二位数组最后一位便是两个字符串之间的距离
        return distance[s1_len][s2_len];
    }

    // 求最小值
    public static int getMin(int a, int b, int c) {
        int min = a;
        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }
        return min;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int count = 0;
        BufferedReader in = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_text.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("E:\\CUP\\dataset\\valid_text2.txt"));
//       BufferedWriter out0 = new BufferedWriter(new FileWriter("E:\\CUP\\dataset\\valid_le==1_justInsertOneSpace.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            count++;
            String[] docs = str.split("#####");
            System.out.println(count);
//            if (getStringDistance(docs[0], docs[1]) == 2) {
//                String str1 = le3(docs[0], docs[1]);
////                if (str1.equals("insert   "))
////                    out0.write(docs[0] + "#####" + docs[1] + "#####" + str1 + "#####" + count + "\n");
//                out.write(docs[0] + "#####" + docs[1] + "#####" + str1 + "#####" + count + "\n");
//
//            }

            if (getStringDistance(docs[0], docs[1]) >= 3 && getStringDistance(docs[0], docs[1]) <= 5)
                out.write(str + "#####" + count + "\n");
        }
//        out0.close();
            in.close();
            out.close();


        }



    public static String le2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        String[][] operations = new String[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = Math.max(i, j);
                    if (i == j) operations[i][j] = "";
                    else if (i > j) operations[i][j] = operations[i - 1][j] + "insert" + str1.charAt(i - 1) + "  ";
                    else operations[i][j] = operations[i][j - 1] + "insert" + str2.charAt(j - 1) + "  ";
                } else {


                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                        operations[i][j] = operations[i - 1][j - 1];
                    } else {
                        //min = Math.min( dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                        int direction = 0;
                        int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                        if (min == dp[i - 1][j]) direction = 1;
                        else if (min == dp[i][j - 1]) direction = -1;
                        else direction = 0;
                        dp[i][j] = min + 1;
                        switch (direction) {
                            case 1:
                                operations[i][j] = operations[i - 1][j] + "insert " + str1.charAt(i - 1) + " ";
                                break;
                            case -1:
                                operations[i][j] = operations[i][j - 1] + "insert " + str2.charAt(j - 1) + " ";
                                break;
                            case 0:
                                operations[i][j] = operations[i - 1][j - 1] + "replace " + str1.charAt(i - 1) + "###" + str2.charAt(j - 1) + " ";
                                break;
                        }

                    }
                }
            }
        }
        return operations[len1][len2];
    }

    public static String le3(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[] dp = new int[len2 + 1];
        String[] operations = new String[len2 + 1];
        int zuoshang_dp = 0;
        String zuoshang_str = "";
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[j] = Math.max(i, j);
                    if (i == j) operations[j] = "";

                    else operations[j] = operations[j - 1] + "insert " + str2.charAt(j - 1) + " ";
                } else if (j == 0) {
                    zuoshang_dp = dp[j];
                    zuoshang_str = operations[j];
                    dp[j] = Math.max(i, j);
                    operations[j] = operations[j] + "insert " + str1.charAt(i - 1) + " ";
                } else {


                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                        int zuoshang_dp_temp = dp[j];
                        dp[j] = zuoshang_dp;
                        zuoshang_dp = zuoshang_dp_temp;
                        String zuoshang_str_temp = operations[j];
                        operations[j] = zuoshang_str;
                        zuoshang_str = zuoshang_str_temp;
                    } else {
                        //min = Math.min( dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                        int direction = 0;
                        int min = Math.min(zuoshang_dp, Math.min(dp[j], dp[j - 1]));
                        if (min == dp[j]) direction = 1;
                        else if (min == dp[j - 1]) direction = -1;
                        else direction = 0;

                        switch (direction) {
                            case 1:
                                zuoshang_dp = dp[j];
                                dp[j] = min + 1;
                                zuoshang_str = operations[j];
                                operations[j] = operations[j] + "insert " + str1.charAt(i - 1) + " ";
                                break;
                            case -1:
                                zuoshang_dp = dp[j];
                                dp[j] = min + 1;
                                zuoshang_str = operations[j];
                                operations[j] = operations[j - 1] + "insert " + str2.charAt(j - 1) + " ";
                                break;
                            case 0:
                                zuoshang_dp = dp[j];
                                dp[j] = min + 1;
                                String zuoshang_str_temp = operations[j];
                                operations[j] = zuoshang_str + "replace " + str1.charAt(i - 1) + "###" + str2.charAt(j - 1) + " ";
                                zuoshang_str = zuoshang_str_temp;
                                break;
                        }

                    }
                }
            }
        }
        return operations[len2];
    }

}
