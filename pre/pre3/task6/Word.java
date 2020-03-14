public class Word implements java.lang.Comparable<Word> {
    private String wd;
    private String category;

    Word(String content) {
        this.wd = content;
        this.category = "";
    }

    public void setCategory(String cate) {
        this.category += cate;
    }

    public String getCategory() {
        return this.category;
    }

    public int getCateNum() {
        return this.category.length();
    }

    public String getWd() { return this.wd; }

    public void setWd(String content) { this.wd = content; }

    public int compareTo(Word o) {
        int result = 0;
        result = this.wd.compareTo(o.wd);//添加符号为降序
        return result;
    }
}
