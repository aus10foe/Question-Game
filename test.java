import java.util.*;
public class test {

public static void main(String []args) {
   int[][] values = {{8, 4, 3, 6, 3 }, {0, 2, 4, 6, 8},
         {1, 3, 5, 3, 5 }, {7, 2, 7, 1, 1}, {4, 8, 7, 3, 7}}; 
   System.out.println([["a", "b"], ["c", "d", "e"], ["f"]]);
   System.out.println(mystery([["y", "a", "y"], ["b", "r", "e", "a", "k"]]	));
   System.out.println(mystery([["n", "e", "v", "e", "r"], ["o", "d", "d"], ["o", "r"], ["e", "v", "e", "n"]]));
}

   public static Map<String, Integer> mystery(String[][] a) {
       Map<String, Integer> result = new TreeMap<String, Integer>();
       for (int i = 0; i < a.length; i++) {
           for (int j = 0; j < a[i].length; j++) {
               result.put(a[i][j], j);
           }
       }
       return result;
   }


}