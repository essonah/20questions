import java.util.Scanner;

/**
 * A guessing game based on 20 questions.
 * 
 * @author blerner
 *
 */
public class GuessingGame {
    // The decision tree holding the questions and answers
    protected GameTree gameTree;

    /** The scanner to read keyboard input */
    protected Scanner in = new Scanner(System.in);

    /**
     * Creates a new guessing game
     * 
     * @param filename the name of the file containing the questions and answers
     */
    public GuessingGame(String filename) {
        gameTree = new GameTree(filename);
    }

    /**
     * Prints just the answers contained in the game tree
     */
    protected void printAnswers() {
        System.out.println("The possible answers are:");
        gameTree.inorderTraversal(gameTree.getRoot());
    }

    /**
     * Repeatedly plays multiple rounds of the guessing game until the user
     * wants to quit.
     */
    protected void playGame() {
        System.out.println("Let's play 20 questions!");

        // Keep playing rounds of the game until the user does not want
        // to play any more.
        while (true) {
            playRound();
            System.out.print("Play again? (yes/no) ");
            String answer = getYesNo();
            if (answer.equals("no")) {
                return;
            }
        }
    }

    /**
     * Get input from the user. Keep asking until the user either enters yes, y, no,
     * or n.
     * Case does not matter
     * 
     * @return "yes" or "no"
     */
    protected String getYesNo() {
        // Keep asking until the user provides a yes or no answer.
        while (true) {
            String answer = in.nextLine().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                return "yes";
            }
            if (answer.equals("no") || answer.equals("n")) {
                return "no";
            } else {
                System.out.print("Please answer yes or no. ");
            }
        }
    }

    /**
     * Play one round of 20 questions
     */
    private void playRound() {

        GameTreeNode currentNode = gameTree.getRoot();

        while (!currentNode.isAnswer()) {
            System.out.println(currentNode.getData() + "(yes/no)");
            String userResponse = getYesNo(); // takes in the user's response.
            if (userResponse.equalsIgnoreCase("yes")) {
                currentNode = currentNode.getYesChild(); // the answer is yes, go to the yesleaf of the currentNode
                // the user enters no, go to the noChild of the current node
            } else if (userResponse.equalsIgnoreCase("no")) {
                currentNode = currentNode.getNoChild();
            }

        }
        endRound(currentNode);

    }
/**
 * this method controls what happens when the computer/user reaches the leaf
 * @param currentNode
 */
    protected void endRound(GameTreeNode currentNode) {

        System.out.println("Is it " + currentNode.getData() + "? (yes/no)");  // asks a question, to know if the current leaf, is what you want
        String userResponse = getYesNo();
        if (userResponse.equalsIgnoreCase("yes")) {
            System.out.println("Right!!");     // if yes, the computer gueessed right.
        } else {
            System.out.println("Oh no, I guessed wrong.");  // if no, the computer guessed wrong.
        }
    }

    /**
     * Play 20 questions
     * 
     * @param args the name of the file containing the questions and answers
     */
    public static void main(String[] args) {
        // If the user does not provide a filename on the command line, use cats.txt
        String filename;
        if (args.length == 0) {
            filename = "cats.txt";
        } else {
            filename = args[0];
        }
        System.out.println("Using file " + filename);
        GuessingGame game = new GuessingGame(filename);
        game.printAnswers();
        game.playGame();

    }

}
