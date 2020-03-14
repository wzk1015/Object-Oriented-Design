import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        while (sn.hasNext()) {
            words.add(sn.next());
        }
        for (int i = 0; i < words.size(); i++) {
            String pattern = "[a-zA-Z]+";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(words.get(i));
            if (m.find()) {
                words.set(i,m.group().toLowerCase());
            }
        }
        Collections.sort(words);
        for (int i = 0; i < words.size(); i++) {
            for (int j = words.size() - 1; j > i; j--) {
                if (words.get(i).equals(words.get(j))) {
                    words.remove(j);
                }
            }
        }
        for (String word:words) {
            System.out.println(word);
        }
    }
}
