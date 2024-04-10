import java.util.HashMap;

class ContTextChar{
    public static void main(String args[]){
        String text = "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Tempora eaque possimus rerum architecto vero impedit necessitatibus adipisci voluptate, quia perferendis libero labore facere aliquid corrupti illo suscipit corporis ea quae.";
        HashMap<String, Integer> charCounts = new HashMap<>();
        
        for (String c : text.split(" ")) {
            if (charCounts.containsKey(c)) {
              charCounts.put(c, charCounts.get(c) + 1);
            } else {
              charCounts.put(c, 1);
            }
          }

        charCounts.forEach((key,value) -> System.out.println(key + " : " + value));

        HashMap<String, String> char_binary_txt = HollfmanCoding.return_tree(charCounts);

        System.out.println(charCounts);
        System.out.println(char_binary_txt);
      }
}