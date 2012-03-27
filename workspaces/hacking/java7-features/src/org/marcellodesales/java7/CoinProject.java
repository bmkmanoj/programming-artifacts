package org.marcellodesales.java7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * The coin project was used for language improvements...
 * 
 * Other enhancements to the APIs are documented at
 * http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 26, 2012 11:07:12 PM 
 *
 */
public class CoinProject {
    
    /**
     * The Observer is this class...
     * http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html
     *
     * @author Marcello de Sales (marcello.desales@gmail.com)
     * Mar 26, 2012 11:06:26 PM 
     *
     * @param <T> is the type to be observed.
     */
    static final class Observer<T> {
        private T value;
        public Observer(T newValue) {
            this.value = newValue;
        }
        public T getValue() {
            return this.value;
        }
    }

    public static Map<Integer, String> getUsersByAgeDiamond() {
        // http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html
        Map<Integer, String> userByAge = new HashMap<>();
        userByAge.put(32, "Marcello");
        userByAge.put(30, "Leandro");
        userByAge.put(26, "Thiago");

        Observer<Integer> inter = new Observer<>(34);

        Map<Observer<Integer>, String> crazies = new HashMap<>();
        crazies.put(new Observer<>(23), "23");
        crazies.put(inter, "nullsss");

        return userByAge;
    }

    /**
     * http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html
     * 
     * Mar 26, 2012 11:06:07 PM
     */
    public static void printWithSwitch() {

        Map<Integer, String> users = getUsersByAgeDiamond();
        for(String name : users.values()) {
            switch (name) {
            case "Marcello":
                System.out.println("It's me...");
                break;
            case "Leandro":
                System.out.println("Boco do Leo...");
                break;
            }
        }
    }

    static final public class BadException extends Exception {
    }

    static final public class SuperBadException extends Exception {
    }

    /**
     * 
     * http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
     *
     * Mar 26, 2012 11:04:15 PM
     */
    public static void multipleCatchesOfException() {
        printWithSwitch();

        try {
            int rand = new Random().nextInt();
            if (rand % 2 == 0) {
                throw new BadException();
            } else throw new SuperBadException();

            // the catch with multiple exceptions
        } catch (BadException | SuperBadException badException) {
            System.out.println("Exception happened..." + badException.getClass().getSimpleName());
        }
    }

    /**
     * http://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html
     *
     * Mar 26, 2012 11:04:03 PM
     */
    public static void tryWithResourcesWithAutoClosing() {
        multipleCatchesOfException();
        // try with resources with automatic finally that closes it.
        try (BufferedReader reader = new BufferedReader(new FileReader("/home/marcello/.bash_aliases/"))) {
            String line = null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("marcello")) {
                    throw new IllegalStateException("The name is contained...");
                }
            }

        } catch (FileNotFoundException whereIsTheFile) {
            whereIsTheFile.printStackTrace();

        } catch (IllegalStateException | IOException errorWithFile) {
            errorWithFile.printStackTrace();
        }
    }

    /**
     * names http://docs.oracle.com/javase/7/docs/technotes/guides/language/non-reifiable-varargs.html
     * 
     * @param a  
     */
    @SafeVarargs
    public static <T> List<T> asUnsortedList(T ... a) {
        // http://docs.oracle.com/javase/7/docs/technotes/guides/language/non-reifiable-varargs.html
        return Arrays.asList(a);
    }

    public static void main(String ... args) {
        // names http://docs.oracle.com/javase/7/docs/technotes/guides/language/non-reifiable-varargs.html
        List<String> names = asUnsortedList("Marcello", "Leandro");
        for (String string : names) {
            System.out.println(string);
        }

        // http://docs.oracle.com/javase/7/docs/technotes/guides/language/binary-literals.html
        Integer bin = 0B101;
        Integer hex = 0X12BF;
        Integer dec = 291;
        List<Integer> integers = asUnsortedList(hex, bin, dec);
        for (Integer integer : integers) {
            System.out.println(integer);
        }

        // http://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html
        Long phone = 415_676_0189L;
        Long ssn = 182_333_1234L;
        Long zip = 94404_123L;
        List<Long> codes = asUnsortedList(phone, ssn, zip);
        for (Long long1 : codes) {
            System.out.println(long1);
        }

        printWithSwitch();

    }
}
