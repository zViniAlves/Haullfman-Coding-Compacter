import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class HollfmanCoding {
    
    public static HashMap<Character, String> return_tree(HashMap<Character, Integer> charCounts) {
        List<Character> lista_chars = new ArrayList<>();
        HashMap<Character, String> char_binary_txt = new HashMap<>(); 

        for (char c : charCounts.keySet()){
            lista_chars.add(0, c);
            char_binary_txt.put(c,"");
        }
        
        List<Integer> binary_tree = new ArrayList<>();

        while (lista_chars.size() > 0) {
          if (binary_tree.size() == 0) {        
            char char1 = lista_chars.get(0);
            char char2 = lista_chars.get(1);

            char_binary_txt.replace(char2,"1");
            char_binary_txt.replace(char1,"0");

            binary_tree.add(0, charCounts.get(char1) + charCounts.get(char2));

            lista_chars.removeFirst();
            lista_chars.removeFirst();
          }else{
            int leaf = binary_tree.get(0);
            char char1 = lista_chars.get(0);

            if (charCounts.get(char1) >= leaf) {
              for (char c:charCounts.keySet()){
                if (char_binary_txt.get(c) != ""){
                  char_binary_txt.replace(c, char_binary_txt.get(c) + "0");
                }
              }
              char_binary_txt.replace(char1, char_binary_txt.get(char1) + "1");

              binary_tree.set(0, leaf + charCounts.get(char1));

              lista_chars.removeFirst();
            }else {
              lista_chars.removeFirst();
              int sum_cont_chars = charCounts.get(char1);
              List<Character> list_chars_increment = new ArrayList<>();
              list_chars_increment.add(0,char1);
              while (sum_cont_chars <= leaf) {
                if (lista_chars.size() > 0) {
                  char char2 = lista_chars.get(0);

                  if (charCounts.get(char2) >= sum_cont_chars) {
                    for (char c : list_chars_increment){
                      char_binary_txt.replace(c, char_binary_txt.get(c) + "0");
                    }
                    char_binary_txt.replace(char2, char_binary_txt.get(char2) + "1");
                  }else {
                    for (char c : list_chars_increment){
                      char_binary_txt.replace(c, char_binary_txt.get(c) + "1");
                    }
                    char_binary_txt.replace(char2, char_binary_txt.get(char2) + "0");
                  }

                  list_chars_increment.add(0,char2);

                  sum_cont_chars += charCounts.get(char2);

                  lista_chars.removeFirst();
                }else {
                  break;
                }
              }

              for (char c:charCounts.keySet()){
                if (list_chars_increment.contains(c)){
                  char_binary_txt.replace(c, char_binary_txt.get(c) + "1");
                }else {
                  if (char_binary_txt.get(c) != ""){
                    char_binary_txt.replace(c, char_binary_txt.get(c) + "0");
                  }
                }
              }

              binary_tree.set(0, leaf + sum_cont_chars);
            }
          }
          System.out.println(binary_tree.get(0) + " : " + char_binary_txt + " : " + lista_chars);
        }
        return char_binary_txt;
    }
}
