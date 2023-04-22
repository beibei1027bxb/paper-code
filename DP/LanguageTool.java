package train_data_process;


import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.*;
import java.util.List;

public class LanguageTool {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("E:\\CUP\\dataset\\valid_text2.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        String str;
        int count=0;
        while ((str = in.readLine()) != null) {
            count++;
            System.out.println(count);
            String[] docs = str.split("#####");
            List<RuleMatch> matches0 = langTool.check(docs[0]);
            for (RuleMatch match : matches0) {
                if (match.getMessage().equals("Possible spelling mistake found.")||
                    match.getMessage().equals("Add a space between sentences.")||
                    match.getMessage().equals("Possible typo: you repeated a whitespace")||
                    match.getMessage().equals("Don't put a space before the closing parenthesis.")||
                    match.getMessage().equals("This word is normally spelled as one.")||
                    match.getMessage().equals("Don't put a space after the opening parenthesis.")||
                    match.getMessage().equals("Don't put a space before the full stop.")||
                    match.getMessage().startsWith("A comma may be missing after the conjunctive/linking adverb")||
                    match.getMessage().startsWith("This abbreviation for")||
                    match.getMessage().startsWith("Use a comma before")||
                    match.getMessage().contains("needs to be capitalized")||
                    match.getMessage().equals("Put a space after the comma.")||
                    match.getMessage().equals("Do you wish to insert an arrow?")||
                    match.getMessage().equals("This sentence does not start with an uppercase letter.")||
                    match.getMessage().equals("This word is normally spelled with a hyphen.")||
                    match.getMessage().equals("File types are normally capitalized.")||
                    match.getMessage().startsWith("Unpaired symbol:")||
                    match.getMessage().equals("This expression is usually spelled with a hyphen.")||
                    match.getMessage().equals("Two consecutive dots")||
                    match.getMessage().contains("is British English.")||
                    match.getMessage().contains("Unpaired symbol:")||
                    match.getMessage().equals("Put a space after the comma, but not before the comma.")||
                    match.getMessage().contains("is spelled as two words.")||
                    match.getMessage().startsWith("Consider shortening this phrase to just")||
                    match.getMessage().equals("Possible typo detected.")||
                    match.getMessage().contains("successive sentences begin with the same word.")||
                    match.getMessage().equals("If a new sentence starts here, add a space and start with an uppercase letter.")||
                    match.getMessage().startsWith("This phrase is redundant.")) continue;
                out.write("Potential error at characters " +
                        match.getFromPos() + "-" + match.getToPos() + ": " +
                        match.getMessage()+"#####text2 line num:"+count+"#####"+docs[2]+"\n");

                out.write("Suggested correction(s): " +
                        match.getSuggestedReplacements()+"\n");
            }

            List<RuleMatch> matches1 = langTool.check(docs[1]);
            for (RuleMatch match : matches1) {
                if (match.getMessage().equals("Possible spelling mistake found.")||
                        match.getMessage().equals("Add a space between sentences.")||
                        match.getMessage().equals("Possible typo: you repeated a whitespace")||
                        match.getMessage().equals("Don't put a space before the closing parenthesis.")||
                        match.getMessage().equals("This word is normally spelled as one.")||
                        match.getMessage().equals("Don't put a space after the opening parenthesis.")||
                        match.getMessage().equals("Don't put a space before the full stop.")||
                        match.getMessage().startsWith("A comma may be missing after the conjunctive/linking adverb")||
                        match.getMessage().startsWith("This abbreviation for")||
                        match.getMessage().startsWith("Use a comma before")||
                        match.getMessage().contains("needs to be capitalized")||
                        match.getMessage().equals("Put a space after the comma.")||
                        match.getMessage().equals("Do you wish to insert an arrow?")||
                        match.getMessage().equals("This sentence does not start with an uppercase letter.")||
                        match.getMessage().equals("This word is normally spelled with a hyphen.")||
                        match.getMessage().equals("File types are normally capitalized.")||
                        match.getMessage().startsWith("Unpaired symbol:")||
                        match.getMessage().equals("This expression is usually spelled with a hyphen.")||
                        match.getMessage().equals("Two consecutive dots")||
                        match.getMessage().contains("is British English.")||
                        match.getMessage().contains("Unpaired symbol:")||
                        match.getMessage().equals("Put a space after the comma, but not before the comma.")||
                        match.getMessage().contains("is spelled as two words.")||
                        match.getMessage().startsWith("Consider shortening this phrase to just")||
                        match.getMessage().equals("Possible typo detected.")||
                        match.getMessage().contains("successive sentences begin with the same word.")||
                        match.getMessage().equals("If a new sentence starts here, add a space and start with an uppercase letter.")||
                        match.getMessage().startsWith("This phrase is redundant.")) continue;
                out.write("Potential error at characters " +
                        match.getFromPos() + "-" + match.getToPos() + ": " +
                        match.getMessage()+"#####text2 line num:"+count+"#####"+docs[2]+"\n");
                out.write("Suggested correction(s): " +
                        match.getSuggestedReplacements()+"\n");
            }
        }

        // comment in to use statistical ngram data:
        //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));

    }
}
