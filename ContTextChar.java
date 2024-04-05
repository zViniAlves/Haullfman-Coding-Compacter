import java.util.HashMap;
import HollfmanCoding;

class ContTextChar{
    public static void main(String args[]){
        String text = "AAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBCCCCCCCCCCDDDDEEEE";
        HashMap<Character, Integer> charCounts = new HashMap<>();
        
        for (char c : text.toCharArray()) {
            if (charCounts.containsKey(c)) {
              charCounts.put(c, charCounts.get(c) + 1);
            } else {
              charCounts.put(c, 1);
            }
          }

        charCounts.forEach((key,value) -> System.out.println(key + " : " + value));

        HashMap<Character, String> char_binary_txt = HollfmanCoding.return_tree(charCounts);

        System.out.println(char_binary_txt);
      }
}