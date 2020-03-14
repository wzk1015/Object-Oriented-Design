import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        while (sn.hasNext()) {
            words.add(sn.next());
        }
        System.out.println(words.size());
    }
}
