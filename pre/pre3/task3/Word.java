import java.text.NumberFormat;

public class Word implements java.lang.Comparable<Word> {
    private String wd;
    private int count;
    private String freq;
    private boolean endOfLine;

    Word(String content) {
        this.wd = content;
        this.count = 1;
        this.endOfLine = false;
    }

    public boolean isEndOfLine() {
        return endOfLine;
    }

    public void setEndOfLine() {
        this.endOfLine = true;
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
