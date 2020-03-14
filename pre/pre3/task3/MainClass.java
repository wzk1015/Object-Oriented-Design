import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<>();
        while (sn.hasNextLine()) {
            String nl = sn.nextLine();
            if (nl.equals("")) {
                continue;
            }
            String[] newline = nl.split(" ");

            for (int i = 0; i < newline.length - 1; i++) {
                words.add(new Word(newline[i]));
            }
            Word lineEnd = new Word(newline[newline.length - 1]);
            lineEnd.setEndOfLine();
            words.add(lineEnd);
        }

        getWords(words);

        Collections.sort(words);
        int wordcount = words.size();
        removeRepeat(words);

        for (Word word:words) {
            word.setFreq(wordcount);
            System.out.println(word.getWd() + " " +
                    word.getCount() + " " + word.getFreq());
        }
    }

    public static void removeRepeat(ArrayList<Word> words) {
        if (words.size() <= 1) {
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            //int minIndex = i;
            for (int j = words.size() - 1; j > i; j--) {
                if (words.get(i).getWd().equals(words.get(j).getWd())) {
                    Word wordi = new Word(words.get(i).getWd());
                    wordi.setCount(words.get(i).getCount() + 1);
                    words.set(i, wordi);
                    words.remove(j);
                }
            }
        }
    }

    public static void getWords(ArrayList<Word> words) {
        for (int i = 0; i < words.size(); i++) {
            String pattern = "[a-zA-Z-]+";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(words.get(i).getWd());
            if (m.find()) {
                Word nw = new Word(m.group().toLowerCase());
                if (words.get(i).isEndOfLine()) {
                    nw.setEndOfLine();
                }
                words.set(i, nw);
            }
        }
        for (int i = 0; i < words.size(); i++) {
            String ws = words.get(i).getWd();

            if (ws.endsWith("-") && words.get(i).isEndOfLine()) {
                StringBuilder cut = new StringBuilder(ws.replaceAll("-$", ""));
                while (words.get(i + 1).getWd().endsWith("-")) {
                    cut.append(words.get(i + 1).getWd().replaceAll("-$", ""));
                    words.remove(i + 1);
                }
                cut.append(words.get(i + 1).getWd().replaceAll("-$", ""));
                words.remove(i + 1);
                words.set(i, new Word(cut.toString()));
            }
        }
    }
}
