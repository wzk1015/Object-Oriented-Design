import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<>();
        int rowCount = 0;
        while (sn.hasNextLine()) {
            rowCount++;
            int columnCount = 1;
            String nl = sn.nextLine();
            if (nl.equals("")) {
                continue;
            }
            String[] newline = nl.split(" ");

            for (int i = 0; i < newline.length - 1; i++) {
                if (newline[i].equals("")) {
                    columnCount++;
                    continue;
                }
                Word newWord = new Word(newline[i]);
                newWord.setPos(rowCount, columnCount);
                words.add(newWord);
                columnCount += newline[i].length() + 1;
            }
            Word lineEnd = new Word(newline[newline.length - 1]);
            lineEnd.setEndOfLine();
            lineEnd.setPos(rowCount, columnCount);
            words.add(lineEnd);
        }

        getWords(words);

        Collections.sort(words);
        int wordCount = words.size();
        removeRepeat(words);

        for (Word word:words) {
            word.setFreq(wordCount);
            System.out.println(word.getWd() + " " +
                    word.getCount() + " " + word.getFreq());
            ArrayList<Position> pos = word.getPos();
            Collections.sort(pos);
            for (Position p:pos) {
                System.out.println("\t(" + p.getRow() +
                        ", " + p.getCol() + ")");
            }
        }
    }

    public static void removeRepeat(ArrayList<Word> words) {
        if (words.size() <= 1) {
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = words.size() - 1; j > i; j--) {
                String wi = words.get(i).getWd();
                String wj = words.get(j).getWd();
                String revj = new StringBuffer(wj).reverse().toString();
                if (wi.equals(wj)) {
                    Word nwi = new Word(wi);
                    nwi.setCount(words.get(i).getCount() + 1);
                    nwi.setPos(words.get(i).getPos());
                    nwi.setPos(words.get(j).getPos());
                    words.set(i, nwi);
                    words.remove(j);
                }

                else if (wi.equals(revj)) {
                    String nwiwd;
                    if (wi.compareTo(revj) < 0) {
                        nwiwd = wi;
                    } else {
                        nwiwd = revj;
                    }
                    Word nwi = new Word(nwiwd);
                    nwi.setCount(words.get(i).getCount() + 1);
                    nwi.setPos(words.get(i).getPos());
                    nwi.setPos(words.get(j).getPos());
                    words.set(i, nwi);
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
                /*
                String rev = new StringBuffer(nw.getWd()).
                    reverse().toString();
                if (nw.getWd().compareTo(rev) > 0) {
                    nw.setWd(rev);
                }*/
                nw.setPos(words.get(i).getPos());
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
                Word newi = new Word(cut.toString());
                newi.setPos(words.get(i).getPos());
                words.set(i, newi);
            }
        }

        for (int i = 0; i < words.size(); i++) {
            String str = words.get(i).getWd();
            String rev = new StringBuffer(str).reverse().toString();
            if (str.compareTo(rev) > 0) {
                Word nw = new Word(rev);
                nw.setPos(words.get(i).getPos());
                words.set(i,nw);
            }
        }


    }
}
