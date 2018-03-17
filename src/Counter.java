import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
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
    private void countLines(InputStream is, PrintStream ps) {
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
     * Count the number of words in the input stream.
     * According to the requirements, the only 4 legal delimiters are:
     * space, comma, tab and line break;
     *
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    public void countWords(InputStream is, PrintStream ps) {
        Scanner scanner = new Scanner(is);
        int word = 0;

        Pattern pattern = Pattern.compile("\\n|\\t|\\s+|,");
        scanner.useDelimiter(pattern);
        while (scanner.hasNext()) {
            word++;
            System.out.println(scanner.next());
        }

        String note = ", " + "单词数: ";
        ps.println(fileName + note + word);
    }
}