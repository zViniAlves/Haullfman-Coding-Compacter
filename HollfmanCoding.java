import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class HollfmanCoding {
    
    public static HashMap<String, String> return_tree(HashMap<String, Integer> wordCount) {
        List<String> lista_words = new ArrayList<>();
        HashMap<String, String> word_binary_txt = new HashMap<>(); 

        for (String c : wordCount.keySet()){
            lista_words.addLast(c);
            word_binary_txt.put(c,"");
        }
        
        List<Integer> binary_tree = new ArrayList<>();

        while (lista_words.size() > 0) {
          if (binary_tree.size() == 0) {        
            String word1 = lista_words.get(0);
            String word2 = lista_words.get(1);

            word_binary_txt.replace(word2,"1");
            word_binary_txt.replace(word1,"0");

            binary_tree.add(0, wordCount.get(word1) + wordCount.get(word2));

            lista_words.removeFirst();
            lista_words.removeFirst();
          }else{
            int leaf = binary_tree.get(0);
            String word1 = lista_words.get(0);

            if (wordCount.get(word1) >= leaf) {
              for (String c:wordCount.keySet()){
                if (word_binary_txt.get(c) != ""){
                  word_binary_txt.replace(c, word_binary_txt.get(c) + "0");
                }
              }
              word_binary_txt.replace(word1, word_binary_txt.get(word1) + "1");

              binary_tree.set(0, leaf + wordCount.get(word1));

              lista_words.removeFirst();
            }else {
              lista_words.removeFirst();
              int sum_cont_words = wordCount.get(word1);
              List<String> list_words_increment = new ArrayList<>();
              list_words_increment.add(0,word1);
              while (sum_cont_words <= leaf) {
                if (lista_words.size() > 0) {
                  String word2 = lista_words.get(0);

                  if (wordCount.get(word2) >= sum_cont_words) {
                    for (String c : list_words_increment){
                      word_binary_txt.replace(c, word_binary_txt.get(c) + "0");
                    }
                    word_binary_txt.replace(word2, word_binary_txt.get(word2) + "1");
                  }else {
                    for (String c : list_words_increment){
                      word_binary_txt.replace(c, word_binary_txt.get(c) + "1");
                    }
                    word_binary_txt.replace(word2, word_binary_txt.get(word2) + "0");
                  }

                  list_words_increment.add(0,word2);

                  sum_cont_words += wordCount.get(word2);

                  lista_words.removeFirst();
                }else {
                  break;
                }
              }

              for (String c:wordCount.keySet()){
                if (list_words_increment.contains(c)){
                  word_binary_txt.replace(c, word_binary_txt.get(c) + "1");
                }else {
                  if (word_binary_txt.get(c) != ""){
                    word_binary_txt.replace(c, word_binary_txt.get(c) + "0");
                  }
                }
              }

              binary_tree.set(0, leaf + sum_cont_words);
            }
          }
          System.out.println(binary_tree.get(0) + " : " + word_binary_txt + " : " + lista_words);
        }
        return word_binary_txt;
    }
}
