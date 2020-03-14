import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<>();
        getWords(words, sn);
        Collections.sort(words);
        removeRepeat(words);
        categorize(words);

        for (Word word:words) {
            System.out.println(word.getCateNum() + " "
                    + word.getCategory());
        }
    }

    public static void categorize(ArrayList<Word> words) {
        for (Word word : words) {
            String a = "a{2,3}b{2,4}a{2,4}c{2,3}";
            Pattern ra = Pattern.compile(a);
            Matcher ma = ra.matcher(word.getWd());
            if (ma.find()) {
                word.setCategory("A");
            }
            String b = "a{2,3}(ba){0,100000000}(bc){2,4}";
            Pattern rb = Pattern.compile(b);
            Matcher mb = rb.matcher(word.getWd());
            if (mb.find()) {
                word.setCategory("B");
            }
            Matcher mc = rb.matcher(word.getWd().toLowerCase());
            if (mc.find()) {
                word.setCategory("C");
            }
            String d1 = "^a{0,3}b{1,1000000}c{2,3}";
            Pattern rd1 = Pattern.compile(d1);
            Matcher md1 = rd1.matcher(word.getWd());
            if (md1.find()) {
                String d2 = "b{1,2}a{1,2}c{0,3}$";
                Pattern rd2 = Pattern.compile(d2);
                Matcher md2 = rd2.matcher(word.getWd().toLowerCase());
                if (md2.find()) {
                    word.setCategory("D");
                }
            }
            String e = "a.*b.*b.*c.*b.*c.*c";
            Pattern re = Pattern.compile(e);
            Matcher me = re.matcher(word.getWd());
            if (me.find()) {
                word.setCategory("E");
            }
        }
    }

    public static void removeRepeat(ArrayList<Word> words) {
        if (words.size() <= 1) {
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = words.size() - 1; j > i; j--) {
                if (words.get(i).getWd().equals(words.get(j).getWd())) {
                    Word nwi = new Word(words.get(i).getWd());
                    words.set(i, nwi);
                    words.remove(j);
                }
            }
        }
    }

    public static void getWords(ArrayList<Word> words, Scanner sn) {
        String pattern = "[a-zA-Z-]+";
        Pattern patternCompiled = Pattern.compile(pattern);
        while (sn.hasNext()) {
            String word = sn.next();
            Matcher matcher = patternCompiled.matcher(word);
            if (matcher.find()) {
                String found = matcher.group(0);
                while (found.charAt(found.length() - 1) == '-') {
                    String neP = sn.next();
                    Matcher neMatcher = patternCompiled.matcher(neP);
                    if (neMatcher.find()) {
                        String nextFound = neMatcher.group(0);
                        found = found.substring(0, found.length() - 1).
                                concat(nextFound);
                    }
                }
                words.add(new Word(found));
            }
        }
    }

}
