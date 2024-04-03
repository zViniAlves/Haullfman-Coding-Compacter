import java.util.HashMap;

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
    
        char[] array_chars = new char[charCounts.size()];
        HashMap<Character, String> char_binary_txt = new HashMap<>(); 

        int cont = charCounts.size()-1;
        for (char c : charCounts.keySet()){
            array_chars[cont] = c;
            char_binary_txt.put(c,"");
            cont --;
        }
    }
}