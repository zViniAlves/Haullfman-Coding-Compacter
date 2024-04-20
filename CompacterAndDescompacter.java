import java.io.IOException;
import java.util.*;

class CompacterAndDescompacter{
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
              new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, 
                              Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void main(String args[]) throws IOException{
        String text = ReaderAndWrite.read("input.txt");
        HashMap<String, Integer> wordCounts = new HashMap<>();

        text = text.replaceAll(" ", " S2040 ");
        text = text.replaceAll(" ", " BRA12 ");
        
        for (String c : text.split(" ")) {
            if (wordCounts.containsKey(c)) {
              wordCounts.put(c, wordCounts.get(c) + 1);
            } else {
              wordCounts.put(c, 1);
            }
          }

        wordCounts = sortByValue(wordCounts);

        text = text.replaceAll(" ", "");

        // wordCounts.forEach((key,value) -> System.out.println(key + " : " + value));

        HashMap<String, String> word_binary_txt = HollfmanCoding.return_tree(wordCounts);

        for (String word: word_binary_txt.keySet()) {
          int charCode = Integer.parseInt(word_binary_txt.get(word), 2);
          String bit_str = String.valueOf((char) charCode);

          text = text.replaceAll(word, bit_str);
        }

        String txt_header = new String();
        for (String word: wordCounts.keySet()){
          txt_header += word + " : " + wordCounts.get(word) + "\n";
        }

        ReaderAndWrite.write(txt_header + text,"output.txt");
    }
}