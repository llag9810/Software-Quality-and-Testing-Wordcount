import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Counter {

    private String fileName;

    public Counter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Count the number of chars in the input stream.
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    public void countChar(InputStream is, PrintStream ps) {
        try {
            int count = is.available();
            String note = ", " + "字符数: ";
            ps.println(fileName + note + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Count the number of lines in the input stream.
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    public void countLines(InputStream is, PrintStream ps) {
        Scanner scanner = new Scanner(is);
        int line = 0;
        while (scanner.hasNextLine()) {
            line++;
            scanner.nextLine();
        }
        String note = ", " + "行数: ";
        ps.println(fileName + note + line);
    }


    /**
     * Count words without StopList.
     *
     * @see #countWordsWithStopList(InputStream, PrintStream, Set)
     */
    public void countWords(InputStream is, PrintStream ps) {
        countWordsWithStopList(is, ps, null);
    }

    /**
     * Count the number of words in the input stream.
     * According to the requirements, the only 4 legal delimiters are:
     * space, comma, tab and line break;
     *
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     * @param stopSet A set of words that shall not be included into the count.
     */
    public void countWordsWithStopList(InputStream is, PrintStream ps, Set<String> stopSet) {
        Scanner scanner = new Scanner(is);
        int words = 0;

        Pattern pattern = Pattern.compile("\\n+|\\t+|\\s+|,");
        scanner.useDelimiter(pattern);
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (stopSet == null || !stopSet.contains(word)) {
                words++;
            }
        }

        String note = ", " + "单词数: ";
        ps.println(fileName + note + words);
    }

    /**
     * Count the lines of code, comment and blank.
     */
    public void countCodeAndComment(InputStream is, PrintStream ps) {
        Scanner scanner = new Scanner(is);
        int code = 0;
        int blank = 0;
        int comment = 0;
        String blankPattern = "[\\s\\t\\n]*[(){}]?";
        String commentPattern = "([\\s\\t]*([})]?)((/\\*|//).*))|(\\*/)";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (Pattern.matches(blankPattern, line)) {
                blank++;
            } else if (Pattern.matches(commentPattern, line)) {
                comment++;
            } else {
                code++;
            }
        }
        String note = ", " + "代码行/空行/注释行: ";
        ps.println(fileName + note + code + "/" + blank + "/" + comment);
    }
}