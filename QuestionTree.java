/* Austin Faux
   CSE: 143
   TA: Ken Aragon
   5-30-19
   
   Constructs a guessing game, where the computer asks a series of yes/no questions, 
   until it thinks it has narrowed down to one option, where it then makes a guess. 
   If the computer guesses wrong, the user is prompted for the name of the object, and
   a question that distinguishes between the users answer and the comp. guess. 
   Then updates the game to hold the user's new answer and question.  
   
*/

import java.util.*;
import java.io.*;

public class QuestionTree {
   private Scanner console;
   private QuestionNode overallRoot;
   
   // Post: Constucts the game with the default answer being "computer". 
   public QuestionTree() {
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");
   }
   
   // Post: reads in a file and creates a QuestionTree with the given file data,  
   //       consisting of questions and answers. 
   public void read(Scanner input) {
      overallRoot = read(input, overallRoot);
   }
   
   // Post: creates each question node and answer node of read in file.  
   private QuestionNode read(Scanner input, QuestionNode root) {
      if (input.nextLine().equals("Q:")) {
         root = new QuestionNode(input.nextLine());
         root.left  = read(input, root.left);
         root.right = read(input, root.right);
      } else {
         root = new QuestionNode(input.nextLine());
      }  
      return root;
   }
   
   // Post: Overwrites the game file to include the user's
   //       question and answer, when the computer guesses wrong.
   //       Prints in preorder.  
   public void write(PrintStream output) {
      write(output, overallRoot);
   }
   
   // Post: Prints to output file each node in PreOrder condition. 
   private void write(PrintStream output, QuestionNode root) {
      if (root.left == null && root.right == null) {
         output.println("A:");
         output.println(root.data);
      } else {
         output.println("Q:");
         output.println(root.data);
         write(output, root.left);
         write(output, root.right);
      }
   }
   
   // Post: Runs the question game. The computer asks questions and based on the users
   //       answers, it will determine what other questions to ask until it narrows down to 
   //       one possible guess. If the guess is wrong, the comp. will update 
   //       its question and answer database to include the users object, and a question to
   //       differentiate between comp's answer and user answer. Prints directions
   //       as user plays the game. 
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // Post: Runs the question game until comp. guesses correctly, if not, 
   //       updates the question tree. 
   private QuestionNode askQuestions(QuestionNode root) {
      if (root.left == null && root.right == null) { // answer node
         if (yesTo("Would your object happen to be " + root.data + "?")) {
            System.out.println("Great, I got it right!");
         } else { // computer guessed wrong
            System.out.print("What is the name of your object? ");
            String answer = console.nextLine();
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            if (yesTo("And what is the answer for your object? ")) { // left  
               root = new QuestionNode(question, new QuestionNode(answer), root);
            } else { // right
               root = new QuestionNode(question, root, new QuestionNode(answer));
            }
         }
      } else { // not an answer node
         if (yesTo(root.data)) {
            root.left = askQuestions(root.left);
         } else {
            root.right = askQuestions(root.right);
         }
      }
      return root; 
       
   }
   
   // Post: returns true if the user answers yes to the given question, false otherwise. 
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
        System.out.println("Please answer y or n.");
        System.out.print(prompt + " (y/n)? ");
        response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   } 
}

