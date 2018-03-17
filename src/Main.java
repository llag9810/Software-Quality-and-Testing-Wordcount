import java.io.*;
import java.util.Scanner;

public class Main {

    /**
     *  Booleans to determine which counter to use.
     */
    private static boolean charCounter = false;
    private static boolean wordCounter = false;
    private static boolean lineCounter = false;
    private static String fileName;
    private static PrintStream ps = System.out;

    public static void main(String[] args) {
        parseArgument(args);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (charCounter) {
            countChar(fis, ps);
        }

        if (wordCounter) {

        }

    }

    /**
     * Set boolean variables by parsing parameters
     *
     * @param args: Arguments that needs to be parsed.
     */
    private static void parseArgument(String[] args) {

        boolean hasInputFileName = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    charCounter = true;
                    break;
                case "-w":
                    wordCounter = true;
                    break;
                case "-l":
                    lineCounter = true;
                    break;
                case "-o":
                    try {
                        File file = new File(args[i + 1]);
                        ps = new PrintStream(args[i + 1]);
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Usage: wc.exe [parameter] [input_file_name]");
                        System.exit(0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    if (hasInputFileName) {
                        System.out.println("Usage: wc.exe [parameter] [input_file_name]");
                        System.exit(0);
                    } else {
                        fileName = args[i];
                        hasInputFileName = true;
                    }
                    break;
            }
        }
    }

    /**
     * Count the number of chars in the input stream.
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    private static void countChar(InputStream is, PrintStream ps) {
        try {
            int count = is.available();
            String note = ", " + "字符数: ";
            ps.println(fileName + note + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count the number of words in the input stream.
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    private static void countWords(InputStream is, PrintStream ps) {
        //TODO: Implement this method.
    }

    /**
     * Count the number of lines in the input stream.
     * @param is the InputStream to count.
     * @param ps the PrintStream to write the result.
     */
    private static void countLines(InputStream is, PrintStream ps) {
        Scanner scanner = new Scanner(is);
        int line = 0;
        while (scanner.hasNextLine()) {
            line++;
            scanner.nextLine();
        }
        String note = ", " + "行数: ";
        ps.println(fileName + note + line);
    }

}