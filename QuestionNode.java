/* Austin Faux
   CSE: 143
   TA: Ken Aragon
   5-30-19
   
   Constructs one question node with passed in data.  
   
*/
import java.util.*;

public class QuestionNode {
    public String data;
    public QuestionNode left;
    public QuestionNode right;
                
    // Post: constructs a leaf node with given data.
    public QuestionNode(String data) {
        this(data, null, null);
    }
                        
    // Post: constructs a branch node with given data, left subtree, right subtree.
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}