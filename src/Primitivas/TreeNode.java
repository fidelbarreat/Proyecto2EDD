package Primitivas;

/**
 * @author Yasmin Hammoud
 */
public class TreeNode {
    
    private Citizen person;
    private String color;
    private TreeNode parent;
    private TreeNode right;
    private TreeNode left;
    

    public TreeNode(Citizen person, String color, TreeNode right, TreeNode left) {
        this.person = person;
        this.color = color;
        this.parent = null;
        this.right = right;
        this.left = left;
    }
    
    public Citizen getPerson() {
        return person;
    }

    public void setPerson(Citizen person) {
        this.person = person;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }
    
}

