import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class leet_1 {
    public static void main(String[] args) {
        leet_1 a = new leet_1();
        String name = "FLASH_BUY_GROUP";
        System.out.println(transformToTitleCase(name));
    }

    public static String transformToTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] words = input.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1));

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return null;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())return false;
        Map<String, Integer> anaList = new HashMap<>();
        String[] strings =  s.split("");
        for(String c : strings){
            int num = anaList.getOrDefault(c,0);
            anaList.put(c,num+1);
        }
        String[] stringt =  t.split("");
        for (String a : stringt){
            if (!anaList.containsKey(a))return false;
            int numA = anaList.getOrDefault(a,0);
            if(numA == 0) return false;
            anaList.remove(a);
            anaList.put(a,numA-1 );
        }
        return true;
    }

    public boolean isAnagram_MethodB(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);

        return Arrays.equals(charS,charT);
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> uniques = new HashSet<>();

        for (int i = 0; i < nums.length; i++ ){
            if(uniques.contains(nums[i])) return true;
            uniques.add(nums[i]);
        }
        return false;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> mag = new HashMap();

        for(int i = 0; i < magazine.length(); i++){
            char _charmag = magazine.charAt(i);
            Integer value = mag.getOrDefault(_charmag,0);
            mag.put(_charmag, value + 1 );
        }
        System.out.println(mag);

        for(int i = 0; i < ransomNote.length(); i++){
            char _char = ransomNote.charAt(i);
            Integer value = mag.getOrDefault(_char,0);
            if(value == 0 ){
                System.out.println("character not available: " + _char );
                return false;
            }
            mag.put(_char, value - 1);
        }
        System.out.println("all characters are available" );
        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] hold = new int[2];

        for(int i = 0; i < nums.length; i++ ){
            int tempA = nums[i];

            for(int j = i + 1; j < nums.length; j++  ){
                int tempB = nums[j];
                int result = tempA + tempB;
                if(target == result ){
                    hold[0] = i;
                    hold[1] = j;
                    return hold;
                }
            }

        }
        return hold;
    }

    public boolean isPalindrome(int x) {
        String item = String.valueOf(x);
        char[] chars = item.toCharArray();
        char[] newitem = new char[chars.length];
        int size = chars.length;

        for(int i = 0; i < chars.length; i++){
            newitem[size - 1] = chars[i];
            System.out.println(newitem[i]);
            size = size - 1;
        }
        String _newitem = String.copyValueOf(newitem);
        System.out.println(_newitem);


        return item.equalsIgnoreCase(_newitem);
    }

    public boolean isPalindromeA(int x) {
        if(x < 0){return false;}
        int num = x;
        int rem = 0;
        int s = 0;
        while (x > 0){
            rem = x % 10;
            x = x / 10;
            s = s * 10 + rem;
        }
        System.out.println(s);
        System.out.println(num);
        return num  == s;

    }

    public static int[] runningSum(int[] nums) {
        int[] working = new int[nums.length];
        int[] result = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < nums.length; i++ ){
            working[i] = nums[i];

            for(int j = 0; j < working.length; j++){
                sum = sum + working[j];
            }
            result[i] = sum;
            sum = 0;
        }
        return result;
    }

    public static int pivotIndexA(int[] nums) {
        int leftsum = 0; int rightsum = 0; int pivotindex = -1;
        int[] working = new int[nums.length];
        for(int i = 0; i < nums.length; i++ ){
            if(i == 0 && i == nums.length-1 ){
                return 0;
            }
            if(i == 0){
                leftsum = 0;
            }else{
                leftsum = Arrays.stream(working).sum();
            }
            if(i == nums.length-1){
                rightsum = 0;
            }else{
                for(int j = i + 1 ; j < nums.length; j++ ){
                    rightsum = rightsum + nums[j];
                }
            }
            if(leftsum == rightsum){
                return i;
            }
            working[i] = nums[i];
            rightsum = 0; leftsum = 0;

        }
        return pivotindex;
    }

    public static int pivotIndexB(int[] nums) {
        int pivotindex = -1;
        int leftsum = 0;
        int rightsum = Arrays.stream(nums).sum();
        for(int i = 0; i < nums.length; i++ ){
            if(i > 0){
                leftsum = leftsum + nums[i -1];
            }
            rightsum = rightsum - nums[i];
            if(leftsum == rightsum){
                return i;
            }

        }

        return pivotindex;
    }

    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length() ){
            return false;
        }

        HashMap<Character, Character> _sMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++ ){
            if(_sMap.containsKey(s.charAt(i))){
                if (_sMap.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else if (!_sMap.containsValue(t.charAt(i))) {
                _sMap.put(s.charAt(i),t.charAt(i));
            }else{
                return false;
            }
        }

        return true;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0; int j = 0;
        while(i < s.length() && j < t.length()){
            if (s.charAt(i) == t.charAt(j) ){
                i += 1;
            }
            j += 1;
        }

        return i == s.length() ? true : false;
    }

    public boolean checkifIhrHasPasses(LocalDateTime localDateTime, LocalDateTime now){
        System.out.println(now);
        System.out.println(localDateTime);

        LocalDateTime addedtime = localDateTime.plusHours(1);
        System.out.println("new 1hr added is: " + addedtime);


        Duration durationA = Duration.between(localDateTime, now);
        Duration durationB = Duration.between(localDateTime, addedtime);
        System.out.println("duration A: " + durationA);
        System.out.println("duration B: " + durationB);
        Boolean check = durationA.equals(Duration.ofHours(1));

        System.out.println("Check " + check);

        //Boolean compare = localDateTime >= startDate && now <= addedtime;
        //System.out.println("Is Date After 1hr: " + compare);

        LocalDateTime after = localDateTime.plusHours(1);
        LocalDateTime before = localDateTime.plusHours(2);




        return true;
    }


}
