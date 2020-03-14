import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private String blank = "([ \t]{0,60})";
    private String number = "([+-]?[0-9]{1,60})";
    private String deg = "(\\*\\*" + blank + number + ")";
    private String sin = "(sin" + blank + "\\(" + blank + "x" + blank + "\\)"
            + "(" + blank + deg + ")?)";
    private String cos = "(cos" + blank + "\\(" + blank + "x" + blank + "\\)"
            + "(" + blank + deg + ")?)";
    private String pow = "(x" + "(" + blank + deg + ")?)";
    private String variable = "(" + pow + "|" + sin + "|" + cos + ")";
    private String factor = "(" + variable + "|" + number + ")";
    private String item = "(([+-]" + blank + ")?" + factor
            + "(" + blank + "\\*" + blank + factor + "){0,50})";
    private String expression = blank + "(([+-]" + blank + ")?" + item + blank
            + "([+-]" + blank + item + blank + "){0,50})";

    private String newsin = "(sin" + blank + "\\(" + blank + "[x@]" + blank + "\\)"
            + "(" + blank + deg + ")?)";
    private String newcos = "(cos" + blank + "\\(" + blank + "[x@]" + blank + "\\)"
            + "(" + blank + deg + ")?)";
    private String newvariable = "(" + pow + "|" + newsin + "|" + newcos + ")";
    // add @, # into factor
    //notice here is the original expression

    private String newfactor = "(" + newvariable + "|" + number + "|\\(" + expression + "\\)|@|#)";
    //private String newfactor = "(" + newvariable + "|" + number + "|@|#)";
    private String newitem = "(([+-]" + blank + ")?" + newfactor
            + "(" + blank + "\\*" + blank + newfactor + "){0,50})";
    private String newexpression = blank + "(([+-]" + blank + ")?" + newitem + blank
            + "([+-]" + blank + newitem + blank + "){0,50})";

    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);
    private boolean showJudgeInfo;
    private boolean showParseInfo;

    Input(boolean judge, boolean parse) {
        this.showJudgeInfo = judge;
        this.showParseInfo = parse;
    }

    public Term read(String s) {
        String sentence = s;
        if (!sentence.matches("[0-9xsinco\\*\\(\\-\\+\\) \\t]{1,100}")) {
            if (showJudgeInfo) {
                System.out.println("Illegal character!");
                for (int i = 0; i < sentence.length(); i++) {
                    if (!"0123456789xsinco*(-+) \t\n".contains(sentence.charAt(i) + "")) {
                        System.out.print(sentence.charAt(i));
                    }
                }
                System.out.println();
            }
            return null;
        }
        if (!judgeExp(sentence.replaceAll("sin[ \\t]{0,60}\\(","sin(").replaceAll(
                "cos[ \\t]{0,60}\\(","cos("))) {
            return null;
        }
        sentence = sentence.replaceAll("[ \\t]","");
        return parseExp(sentence);    ///////
    }

    public boolean judgeExp(String sentence) {
        Object[] opResult;
        //sin(factor) -> sin(@), (poly) -> #
        //Object[] ans = new Object[3];
        try {
            opResult = operateFactorPoly(sentence);
        } catch (RuntimeException e) {
            if (showJudgeInfo) {
                System.out.println("Para mismatch!");
            }
            return false;
        }
        String s = (String) opResult[0];
        if (showJudgeInfo) {
            System.out.println("judging EXP: " + s);
        }
        Queue<String> factorList = (Queue<String>) opResult[1];
        Queue<String> polyList = (Queue<String>) opResult[2];

        if (s.contains("@") || s.contains("#")) {
            if (!s.matches(newexpression)) {
                return false;
            }
            for (Object f:factorList) {
                if (!judgeFactor((String)f)) {
                    return false;
                }
            }
            for (Object p:polyList) {
                if (!judgeExp((String)p)) {
                    return false;
                }
            }
        }
        return s.matches(newexpression);
    }

    public boolean judgeFactor(String sentence) {
        Object[] opResult;
        try {
            opResult = operateFactorPoly(sentence);
        } catch (RuntimeException e) {
            if (showJudgeInfo) {
                System.out.println("Para mismatch!");
            }
            return false;
        }
        String s = (String) opResult[0];
        if (showJudgeInfo) {
            System.out.println("judging FAC: " + s);
        }
        Queue<String> factorList = (Queue<String>) opResult[1];
        Queue<String> polyList = (Queue<String>) opResult[2];

        if (s.contains("@") || s.contains("#")) {
            if (!judgeExp(s)) {
                return false;
            }
            for (Object f:factorList) {
                if (!judgeFactor((String) f)) {
                    return false;
                }
            }
            for (Object p:polyList) {
                if (!judgeExp((String) p)) {
                    return false;
                }
            }
        }
        return s.matches(blank + newfactor + blank);
    }

    public Object[] operateFactorPoly(String sen) {
        Queue<String> factorList = new LinkedList<>();
        Queue<String> polyList = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        if (!sen.contains("(")) {
            return new Object[]{sen,factorList,polyList};
        }

        ArrayList<String> stack = new ArrayList<>();
        ArrayList<Integer> stack2 = new ArrayList<>();
        int stackCount = 0;
        int extraCount = 0;
        for (int i = 0; i < sen.length(); i++) {
            if (i < sen.length() - 4 && (sen.substring(i,i + 4).equals("sin(")
                    || sen.substring(i,i + 4).equals("cos("))) {
                if (stackCount != 0) {
                    extraCount++;
                    i += 3;
                    continue;
                }
                stack.add(sen.substring(i,i + 4));
                stack2.add(i + 4);
                stackCount++;
                s.append(sen, i, i + 4);
                i += 3;     //3+1=4
            } else if (sen.charAt(i) == '(') {
                if (stackCount != 0) {
                    extraCount++;
                    continue;
                }
                stack.add("(");
                stack2.add(i + 1);
                stackCount++;
            }
            else if (sen.charAt(i) == ')') {
                if (extraCount > 0) {
                    extraCount--;
                    continue;
                }
                int index = stack2.get(stackCount - 1);
                stack2.remove(stackCount - 1);
                String pop = stack.get(stackCount - 1);
                stack.remove(stackCount - 1);
                stackCount--;
                if (pop.equals("sin(") || pop.equals("cos(")) {
                    s.append("@)");
                    factorList.add(sen.substring(index,i));
                } else if (pop.equals("(")) {
                    s.append("#");
                    polyList.add(sen.substring(index,i));
                }
            }
            else if (stackCount == 0) {
                s.append(sen.charAt(i));
            }
        }

        return new Object[]{s.toString(),factorList,polyList};
    }

    public Term parseExp(String sentence) {
        Term ans = c(BigInteger.valueOf(233));

        Object[] opResult = operateFactorPoly(sentence);
        String s = (String) opResult[0];
        if (showParseInfo) {
            System.out.println("parsing EXP: " + s);
        }
        Queue<String> fl = ((Queue<String>) opResult[1]);
        Queue<String> pl = ((Queue<String>) opResult[2]);

        boolean firstMark = sentence.startsWith("-");
        if (firstMark) {
            s = s.substring(1);
        }
        Pattern r = Pattern.compile("[+-]?" + newitem);
        Matcher m = r.matcher(s);
        boolean firstLoop = true;
        while (m.find()) {
            if (showParseInfo) {
                System.out.println("parsing ITEM: " + m.group());
            }
            if (firstLoop) {
                String word = m.group().replaceAll("\\+","");
                if (word.startsWith("-")) {
                    ans = parseItem(word.replaceFirst("-",""), !firstMark, fl, pl);
                } else {
                    ans = parseItem(word, firstMark, fl, pl);
                }
                firstMark = false;
                firstLoop = false;
            }
            else {
                String word = m.group().replaceAll("\\+", "");
                if (word.startsWith("-")) {
                    ans = ans.add(parseItem(word.replaceFirst("-", ""), true, fl, pl));
                } else {
                    ans = ans.add(parseItem(word, false, fl, pl));
                }
            }
        }
        return ans;
    }

    public Term parseItem(String item, boolean neg1, Queue<String> fl,Queue<String> pl) {
        Term ans = c(BigInteger.valueOf(233));
        boolean neg2 = neg1;
        String itemPro = item;
        if (item.startsWith("-")) {
            itemPro = item.replaceFirst("-","");
            neg2 = !neg2;
        }
        Pattern pf = Pattern.compile(newfactor);
        Matcher mf = pf.matcher(itemPro);
        BigInteger coeff;
        BigInteger deg;
        boolean firstLoop = true;
        while (mf.find()) {
            String fac = mf.group(0);
            if (firstLoop) {
                ans = parseInside(fac,fl,pl);
                firstLoop = false;
            } else {
                ans = ans.mult(parseInside(fac,fl,pl));
            }
        }
        if (neg2) {
            ans = c(one.negate()).mult(ans);
        }
        return ans;
    }

    public Term parseInside(String fac, Queue<String> fl,Queue<String> pl) {
        BigInteger coeff;
        BigInteger deg;
        if (fac.matches(number)) {
            coeff = new BigInteger(fac);
            return c(coeff);
        }
        else if (fac.matches(pow)) {
            coeff = parseCoeff(fac,"x");
            deg = parseDegree(fac,"x");
            return c(coeff).mult(p(deg));
        }
        else if (fac.matches(newsin)) {
            coeff = parseCoeff(fac,"sin");
            deg = parseDegree(fac,"sin");
            if (fac.matches(sin)) {     //sin(x)
                return c(coeff).mult(sin(deg,p(one)));
            } else {
                return c(coeff).mult(sin(deg,parseExp(fl.poll())));
            }
        }
        else if (fac.matches(newcos)) {
            coeff = parseCoeff(fac,"cos");
            deg = parseDegree(fac,"cos");
            if (fac.matches(cos)) {     //sin(x)
                return c(coeff).mult(cos(deg,p(one)));
            } else {
                return c(coeff).mult(cos(deg,parseExp(fl.poll())));
            }
        }
        else if (fac.equals("@")) {
            return parseItem(fl.poll(),false,fl,pl);
        }
        else if (fac.equals("#")) {
            return parseExp(pl.poll());
        }
        else if (fac.matches(expression)) {
            return parseExp(fac);
        }
        System.out.println("ParseInside Error!");
        return null;
    }

    public Const c(BigInteger newnumber) {
        return new Const(newnumber);
    }

    public BigInteger parseCoeff(String fac, String type) {
        BigInteger ans;
        if (fac.startsWith(type)) {
            ans = one;
        } else {
            ans = new BigInteger(fac.split("\\*" + type)[0]);
        }
        return ans;
    }

    public BigInteger parseDegree(String fac, String type) {
        BigInteger ans;
        if (type.equals("x") && fac.endsWith("x")) {
            ans = one;
        } else if ((type.equals("sin") || type.equals("cos"))
                && (fac.endsWith("x)") || fac.endsWith("@)"))) {
            ans = one;
        } else {
            String[] sl = fac.split("\\*\\*");
            ans = new BigInteger(sl[sl.length - 1]);
        }
        if (ans.abs().compareTo(BigInteger.valueOf(50)) > 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        return ans;
    }

    public Pow p(BigInteger deg) {
        return new Pow(deg);
    }

    public Tri sin(BigInteger deg, Term content) {
        return new Tri("sin",deg,content);
    }

    public Tri cos(BigInteger deg, Term content) {
        return new Tri("cos",deg,content);
    }

}
