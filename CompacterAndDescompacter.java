import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

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

    public static void compacter(String path) throws IOException{
        String text = ReaderAndWrite.read(path);
        HashMap<String, Integer> wordCounts = new HashMap<>();

        text = text.replaceAll(" ", " S2040 ");
        text = text.replace("\r\n", " BRA12 ");
        
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
          txt_header += word + " : " + Integer.toHexString(wordCounts.get(word)) + "E_O_V";
        }
        txt_header += "E_O_H\n";

        int dot_index = path.indexOf(".");
        String output_path = path.substring(0,dot_index) + ".z" + path.substring(dot_index + 1);
        ReaderAndWrite.write(txt_header + text,output_path);
    }

    public static void descompacter(String path) throws IOException,InterruptedException {
      String text = ReaderAndWrite.read(path);
      int index_header = text.indexOf("E_O_H\n", 0);

      HashMap<String, Integer>wordCounts = new HashMap<>();

      String header = text.substring(0, index_header);
      text = text.substring(index_header+6);

      for (String line: header.split("E_O_V")){
        String key = line.substring(0,line.indexOf(" : "));
        String value_str = line.substring(line.indexOf(" : ")+3); 
        int value = Integer.parseInt(value_str, 16);
        
        wordCounts.put(key,value);
      }

      wordCounts = sortByValue(wordCounts);

      HashMap<String, String> word_binary_txt = HollfmanCoding.return_tree(wordCounts);

      LinkedHashMap<String, String> sorted_word_binary_txt = word_binary_txt.entrySet().stream()
            .sorted(Comparator.comparingInt((Map.Entry<String, String> e) -> e.getValue().length()).reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue, 
                (e1, e2) -> e1, 
                LinkedHashMap::new));

      for (String word: sorted_word_binary_txt.keySet()) {
        int charCode = Integer.parseInt(sorted_word_binary_txt.get(word), 2);
        String bit_str = String.valueOf((char) charCode);

        text = text.replaceAll(bit_str,word);
      }

      text = text.replaceAll( "S2040"," ");
      text = text.replace("BRA12","\r\n");

      path = path.replace(".z",".");
      ReaderAndWrite.write(text,path);
    }
}