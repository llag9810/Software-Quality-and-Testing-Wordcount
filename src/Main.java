import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    /**
     *  Booleans to determine which counter to use.
     */
    private static boolean charCounter = false;
    private static boolean wordCounter = false;
    private static boolean lineCounter = false;
    private static boolean codeCounter = false;
    private static boolean recursive = false;
    private static HashSet<String> stopSet;
    private static String fileName;
    private static PrintStream ps = System.out;

    public static void main(String[] args) {
        parseArgument(args);
        Counter counter = new Counter(fileName);
        if (recursive) {

        } else {
            processFile(counter, fileName, ps);
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

            // fuck the stupid teacher who didn't use standard ASCII character.
            // So I make a replace to solve the problem.
            args[i] = args[i].replace("–", "-");

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
                case "-a":
                    codeCounter = true;
                    break;
                case "-s":
                    recursive = true;
                    break;
                case "-o":
                    try {
                        ps = new PrintStream(args[i + 1]);
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Usage: wc.exe [parameter] [input_file_name]");
                        System.exit(0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "-e":
                    try {
                        genarateStopSet(args[i + 1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Usage: wc.exe [parameter] [input_file_name]");
                        System.exit(0);
                    }
                    i++;
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
     * Genarate a collection of words which will be ignored by the word counter from file.
     * @param path the path of word file.
     */
    public static void genarateStopSet(String path) {
        stopSet = new HashSet<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            stopSet.add(scanner.next());
        }
    }

    /**
     * Process file according to the flags.
     *
     * @param counter the class to count words, lines, etc.
     * @param path File path.
     * @param ps Output stream to control where to write the result.
     */
    public static void processFile(Counter counter, String path, PrintStream ps) {
        if (charCounter) {
            counter.countChar(openInputStream(path), ps);
        }

        if (wordCounter) {
            counter.countWordsWithStopList(openInputStream(path), ps, stopSet);
        }

        if (lineCounter) {
            counter.countLines(openInputStream(path), ps);
        }

        if (codeCounter) {
            counter.countCodeAndComment(openInputStream(path), ps);
        }
    }

    public static InputStream openInputStream(String path) {
        InputStream is = null;

        try {
            is = new BufferedInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }

}