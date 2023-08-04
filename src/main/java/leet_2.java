import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class leet_2 {
    public static void main(String[] args) {
        leet_2.splitWords("homes");
    }

    public static void splitWords(String name){
        String[] words = name.split("\\b(s|es|ies)\\b");
        List<String> list = Arrays.stream(words).collect(Collectors.toList());
        list.add(name);

        String regrex = String.join("|", list);
        System.out.println(words);
    }


}
