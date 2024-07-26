/**
 * A GameTreeNode represents either a question or an answer.  If it is a question, it
 * has two children, one for the yes path, and one for the no path.  If it is an answer,
 * it is a leaf.
 */
public class GameTreeNode {
    // The question or answer string for this node
    private String data;
    
    // If a question, this is the path to follow on a yes answer
    private GameTreeNode yesChild;
    
    // If a question, this is the path to follow on a no answer.
    private GameTreeNode noChild;

    /**
     * Constructor creates a node with data.
     * @param data the value for the node
     */
    public GameTreeNode(String data) {
        this.data = data;
    }

    /**
     * @return the data stored at this node.
     */
    public String getData() {
        return data;
    }

    /**
     * Set the data stored at this node.
     * @param data the value to put in the node
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return Get the child corresponding to the yes answer.  Return null
     *   if the node corresponds to an answer instead of a question.
     */
    public GameTreeNode getYesChild() {
        return yesChild;
    }

    /**
     * @return Get the child corresponding to the no answer.  Return null
     *   if the node corresponds to an answer instead of a question.
     */
    public GameTreeNode getNoChild() {
        return noChild;
    }

    /**
     * Set the yes answer.
     * @param gameTreeNode the node corresponding to the yes answer.
     */
    public void setYesChild(GameTreeNode gameTreeNode) {
        yesChild = gameTreeNode;
    }

    /**
     * Set the no answer.
     * @param noNode the node corresponding to the no answer.
     */
    public void setNoChild(GameTreeNode noNode) {
        noChild = noNode;
    }

    /**
     * Tests if this node is an answer
     * @return true if this node corresponds to an answer.
     */
    public boolean isAnswer() {
        return yesChild == null;
    }

    /**
     * @return a string representation of the data stored in the node
     */
    public String toString() {
        return data;
    }
    
   
}
