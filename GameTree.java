import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * GameTree reads a file containing a tree of questions with answers at the leaves.
 * The tree is used to play a 20 Questions guessing game.  A left branch in the tree
 * corresponds to a yes answer.  A right branch in the tree corresponds to a no answer.
 * Tab indentation in the file is used to identify the level of a node in the tree.
 * For example, the question at the root will not be indented at all.  The lines containin
 * its yes and no answers are indented one tab.  The lines in the file should correspond 
 * to a pre-order traversal of the tree.  That is the question should appear first, followed 
 * by a pre-order traversal of the yes answer, and then a pre-order traversal of the right
 * tree.
 * 
 * Here is an example of what a file might contain:
 * 
 * Is it one of Tayloe’s cats?
 *      Is the cat black?
 *          Sasha
 *          Is the cat white?
 *              Lexicon
 *              Mercury
 *      Garfield
 *
 * @author Tayloe
 */
public class GameTree {
    //  The root of the tree
    private GameTreeNode root;
    
    // The lines in the file
    private List<String> lines = new ArrayList<>();
    
    // The current line being processed
    private int curLineNum = 0;
    
    /**
     * Constructor creates a game tree based on the file passed in
     * @param filename the name of the .txt file containing the tree
     */
    public GameTree(String filename) {
        root = buildTree(filename);
    }

    /**
     * Get the root node for this tree.
     *
     * @return root or null if tree is empty.
     */
    public GameTreeNode getRoot() {
        return root;
    }

    /**
     * Set the root node for this tree.
     * @param root the root of the game tree
     */
    public void setRoot(GameTreeNode root) {
        this.root = root;
    }

    /**
     * Builds a tree based on the passed-in filename.  Does nothing if the file
     * does not exist.
     * @param filename name of the .txt file that holds tree data
     */
    private GameTreeNode buildTree(String filename){
        try {
            //put all lines of file into a list
            File infile = new File(filename);
            Scanner dataIn = new Scanner(infile);

            lines = new ArrayList<>();
            curLineNum = 0;
            
            //If there is next line, continuing running this loop
            while (dataIn.hasNext()) {
                //Add the new value, trimming the new lines at the end
                lines.add(dataIn.nextLine().stripTrailing());
            }
            dataIn.close();

            // Walk the lines to build the tree.
            return parseListRecursively();

        } catch (FileNotFoundException e) {
            //If file isn't found
            System.out.println(filename + " not found");
            return null;
        }
    }


    /**
     * Creates a subtree beginning with the current line in the file.  curLineNum
     * is incremented as each line is processed.
     * @return the subtree beginning at the current line.
     */
    private GameTreeNode parseListRecursively(){
        // Get the next line to process
        String curLine = lines.get(curLineNum);

        // See how far it is indented
        int spaceCount = countSpaces(curLine);
        
        // Remove the leading tabs and create a node for this line
        GameTreeNode curnode = new GameTreeNode(curLine.trim());
        curLineNum++;
        
        // Base case:  we are at the end of the file.
        if (curLineNum == lines.size()) {
            return curnode;
        }
        
        // Base case:  The next line is not indented further than the current line.
        // That means the current node is an answer.  Since answers are leaves in the
        // tree, we just return the new node.
        String nextLine = lines.get(curLineNum);
        if (countSpaces(nextLine) <= spaceCount){
            return curnode;
        }
        
        // Recursive case:  The next line is indented further.  That means the
        // current line is a question, so we need to recurse to build the 
        // subtrees for the yes and no answers.
        curnode.setYesChild(parseListRecursively());
        curnode.setNoChild(parseListRecursively());
        
        // Return the complete subtree corresponding to this line.
        return curnode;
    }

    /**
     * Count how many spaces start the string
     * @param s string to check
     * @return how many spaces start the string
     */
    private int countSpaces(String s){

        char space = ' ';
        int count = 0;
        
        for (int i = 0; i < s.length() && s.charAt(i) == space; i++) {
            count++;
        }

        return count;
    }
	
	/**
	 * Returns a string containing an pre-order traversal of the tree
	 * @return a string representing the tree
	 */
	public String toString(){
		return preOrderTraversal(root);
	}

    /**
     * Recursively build the pre-order traversal of the tree
     * @param node the root of the subtree 
     * @return the pre-order traversal 
     */
    private String preOrderTraversal(GameTreeNode node)
    {
        // Answer.  Just return its data.
        if (node.isAnswer()) {
            return node.getData()+"\n";
        }
        
        // Question.  Recursively build the in-order traversal
        String res = "";
        res = res + node+"\n";
        res = res + preOrderTraversal(node.getYesChild());
        res = res + preOrderTraversal(node.getNoChild());
        return res;
    }

    public void inorderTraversal(GameTreeNode node){
        if (node == null) {
            return; // Base case: if the current node is null, just return.
        }
    
        inorderTraversal(node.getYesChild()); // Recurse on the left child (yesChild)
        if (node.isAnswer()) {
            System.out.println(node.getData()); // Only print the node data if it is an answer
        }
        inorderTraversal(node.getNoChild()); // Recurse on the right child (noChild)
    }
    
}

