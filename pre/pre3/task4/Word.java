import java.text.NumberFormat;
import java.util.ArrayList;

public class Word implements java.lang.Comparable<Word> {
    private String wd;
    private int count;
    private String freq;
    private boolean endOfLine;
    private ArrayList<Position> pos;

    Word(String content) {
        this.wd = content;
        this.count = 1;
        this.endOfLine = false;
        this.pos = new ArrayList<>();
    }

    public boolean isEndOfLine() {
        return endOfLine;
    }

    public void setEndOfLine() {
        this.endOfLine = true;
    }

    public ArrayList<Position> getPos() {
        return this.pos;
    }

    public void setPos(int r, int c) {
        Position newpos = new Position(r,c);
        this.pos.add(newpos);
    }

    public void setPos(ArrayList<Position> newPos) {
        this.pos.addAll(newPos);
    }

    public String getWd() { return this.wd; }

    public void setWd(String content) { this.wd = content; }

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    public String getFreq() { return this.freq; }

    public void setFreq(int wordcount) {
        double ratio = ((double)this.count) / wordcount;
        NumberFormat fm = NumberFormat.getPercentInstance();
        fm.setMaximumFractionDigits(2);//设置保留几位小数
        fm.setMinimumFractionDigits(2);//////////////////////////////
        this.freq = fm.format(ratio);
    }

    public int compareTo(Word o) {
        int result = 0;
        result = this.wd.compareTo(o.wd);//添加符号为降序
        return result;
    }
}
