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
                if (words.get(i).getWd().equals(words.get(j).getWd())) {
                    Word nwi = new Word(words.get(i).getWd());
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
    }
    /*
    public static void posSort(ArrayList<String> pos) {
        for (int i = 0;i < pos.size() - 1;i++)
        {
            for (int j = 0;j < pos.size() - 1 - i;j++)
            {
                String[] chj = pos.get(j).split(", ");
                int rj = Integer.parseInt(chj[0].replace("\t(",""));
                int cj = Integer.parseInt(chj[1].replace(")",""));
                String[] chjp = pos.get(j + 1).split(", ");
                int rjp = Integer.parseInt(chjp[0].replace("\t(",""));
                int cjp = Integer.parseInt(chjp[1].replace(")",""));

                if (rj > rjp || (rj == rjp && cj > cjp))
                {
                    String temp = pos.get(j);
                    pos.set(j, pos.get(j + 1));
                    pos.set(j + 1, temp);
                }

            }
        }
    }*/

}
