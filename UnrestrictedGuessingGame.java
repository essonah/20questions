
public class UnrestrictedGuessingGame extends GuessingGame {

    public UnrestrictedGuessingGame(String filename) {
        super(filename);
    }

    /**
     * this method controls what happens when the computer reaches the leaf.
     * this method calls the learn method after it guesses wrong on the leaf
     */
    @Override
    protected void endRound(GameTreeNode currentNode) {

        System.out.println("Is it " + currentNode.getData() + "? (yes/no)");
        String userResponse = getYesNo();
        if (userResponse.equalsIgnoreCase("yes")) {
            System.out.println("Right!!");
        } else {
            System.out.println("Oh no, Sorry.");
            train(currentNode);
        }

    }

    /**
     * this method trains the computer on new information.
     * it allows the user to enter a question/ answer and puts it at the proper
     * location.
     * 
     * @param currentNode
     */
    private void train(GameTreeNode currentNode) {
        System.out.println("What were you thinking of?");
        String userAnswer = in.nextLine();

        System.out.println("Please enter a yes/no question should lead to that answer");
        String userQuestion = in.nextLine();

        System.out.println("Is the answer to your question yes?");
        String answer = getYesNo();

        GameTreeNode newAnswerNode = new GameTreeNode(userAnswer); // create new node to house the user's answer
        GameTreeNode newQuestionNode = new GameTreeNode(userQuestion); // create a new node to house the user's question

        // if the answer to the user's question is yes, adds the element to the yesChild
        // of the question.
        // puts node that doesn't answer the user's question to the noChild of the
        // user's question.
        if (answer.equalsIgnoreCase("yes")) {
            newQuestionNode.setYesChild(newAnswerNode);
            newQuestionNode.setNoChild(currentNode);
        } else {
            newQuestionNode.setNoChild(newAnswerNode);
            newQuestionNode.setYesChild(currentNode);
        }

        currentNode.setData(userQuestion);
        currentNode.setYesChild(newQuestionNode.getYesChild());
        currentNode.setNoChild(newQuestionNode.getNoChild());
 
    }

    public static void main(String[] args) {
        // If the user does not provide a filename on the command line, use cats.txt
        String filename;
        if (args.length == 0) {
            filename = "cats.txt";
        } else {
            filename = args[0];
        }
        System.out.println("Using file " + filename);
        UnrestrictedGuessingGame game = new UnrestrictedGuessingGame(filename);
        game.printAnswers();
        game.playGame();

    }

}
