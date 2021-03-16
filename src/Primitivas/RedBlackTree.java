package Primitivas;

import javax.swing.JOptionPane;

/**
 * @author Yasmin Hammoud
 */
public class RedBlackTree {

    private TreeNode treeRoot;
    private TreeNode treeNIL;

    // -------------------  Constructor ------------------- //
    public RedBlackTree() {
        this.treeNIL = new TreeNode(null, "black", null, null);   // - > Hay que verificar esto
        this.treeRoot = treeNIL;
    }
    
    // -------------------  Constructor 2 ------------------- //
    public RedBlackTree(String name, String surname, int identity) {
        Citizen newCitizen = new Citizen(name, surname, identity);
        this.treeNIL = new TreeNode(null, "black", null, null);   // - > Creo un nodo 
        TreeNode newNode = new TreeNode(newCitizen, "red", this.treeNIL, this.treeNIL);
        this.treeRoot = newNode;
    }

    //  ------------------ Main methods ------------------ //
    
    public void add(String name, String surname, int identity) {
        Citizen newCitizen = new Citizen(name, surname, identity);
        TreeNode newNode = new TreeNode(newCitizen, "red", this.treeNIL, this.treeNIL);

        TreeNode temp = null;
        TreeNode aux = this.treeRoot;

        while (aux != treeNIL) {
            temp = aux;
            if (newNode.getPerson().getIdentity() < aux.getPerson().getIdentity()) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }

        newNode.setParent(temp);
        if (temp == null) {
            treeRoot = newNode;
        } else if (newNode.getPerson().getIdentity() < temp.getPerson().getIdentity()) {
            temp.setLeft(newNode);
        } else {
            temp.setRight(newNode);
        }

        if (newNode.getParent() == null) {
            newNode.setColor("black");
            return;
        }

        if (newNode.getParent().getParent() == null) {
            return;
        }
        adjustAdd(newNode);
    }

    public void remove(int identity) {
        
        TreeNode auxRoot = this.treeRoot;
        TreeNode aux = treeNIL;
        TreeNode child, min;

        while (auxRoot != treeNIL) {
            if (auxRoot.getPerson().getIdentity() == identity) {
                aux = auxRoot;
            }
            if (auxRoot.getPerson().getIdentity() <= identity) {
                auxRoot = auxRoot.getRight();
            } else {
                auxRoot = auxRoot.getLeft();
            }
        }

        if (aux == treeNIL) {
            JOptionPane.showMessageDialog(null, "La cédula introducida no fue localizada");
            return;
        }

        min = aux;
        String minOriginalColor = min.getColor();
        if (aux.getLeft() == treeNIL) {
            child = aux.getRight();
            nodeTransfer(aux, aux.getRight());
        } else if (aux.getRight() == treeNIL) {
            child = aux.getLeft();
            nodeTransfer(aux, aux.getLeft());
        } else {
            min = findMin(aux.getRight());
            minOriginalColor = min.getColor();
            child = min.getRight();
            if (min.getParent() == aux) {
                child.setParent(min);
            } else {
                nodeTransfer(min, min.getRight());
                min.setRight(aux.getRight());
                min.getRight().setParent(min);
            }

            nodeTransfer(aux, min);
            min.setLeft(aux.getLeft());
            min.getLeft().setParent(min);
            min.setColor(aux.getColor());
        }
        if (minOriginalColor.equals("black")) {
            adjustRemove(child);
        }
    }

// -------------- Adjust Tree Methods -------------- //
    
    private void adjustAdd(TreeNode node) {

        TreeNode aux;

        while (node.getParent().getColor().equals("red")) {

            if (node.getParent() == node.getParent().getParent().getRight()) {
                aux = node.getParent().getParent().getLeft();

                if (aux.getColor().equals("red")) {
                    aux.setColor("black");
                    node.getParent().setColor("black");
                    node.getParent().getParent().setColor("red");
                    node = node.getParent().getParent();

                } else {

                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotationRight(node);
                    }
                    node.getParent().setColor("black");
                    node.getParent().getParent().setColor("red");
                    rotationLeft(node.getParent().getParent());
                }

            } else {

                aux = node.getParent().getParent().getRight();

                if (aux.getColor() == "red") {
                    aux.setColor("black");
                    node.getParent().setColor("black");
                    node.getParent().getParent().setColor("red");
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotationLeft(node);
                    }
                    node.getParent().setColor("black");
                    node.getParent().getParent().setColor("red");

                    rotationRight(node.getParent().getParent());
                }
            }
            if (node == treeRoot) {
                break;
            }
        }
        treeRoot.setColor("black");
    }

    private void adjustRemove(TreeNode node) {

        TreeNode sibling;

        while (node != treeRoot && node.getColor().equals("black")) {

            if (node == node.getParent().getLeft()) {
                sibling = node.getParent().getRight();

                if (node.getColor().equals("red")) {
                    node.setColor("black");
                    node.getParent().setColor("red");
                    rotationLeft(node.getParent());
                    sibling = node.getParent().getRight();
                }

                if (sibling.getLeft().getColor().equals("black") && sibling.getRight().getColor().equals("black")) {
                    sibling.setColor("red");
                    node = node.getParent();

                } else {
                    if (sibling.getLeft().getColor().equals("black")) {
                        sibling.getLeft().setColor("black");
                        sibling.setColor("red");
                        rotationRight(sibling);
                        sibling = node.getParent().getRight();
                    }

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor("black");
                    sibling.getRight().setColor("black");
                    rotationLeft(node.getParent());
                    node = treeRoot;
                }

            } else {
                sibling = node.getParent().getLeft();

                if (node.getColor().equals("red")) {
                    sibling.setColor("black");
                    node.getParent().setColor("red");
                    rotationRight(node.getParent());
                    sibling = node.getParent().getLeft();
                }

                if ("black".equals(sibling.getRight().getColor()) && sibling.getRight().getColor().equals("black")) {
                    sibling.setColor("red");
                    node = node.getParent();

                } else {

                    if (sibling.getLeft().getColor().equals("black")) {
                        sibling.getRight().setColor("black");
                        sibling.setColor("red");
                        rotationLeft(sibling);
                        sibling = node.getParent().getLeft();
                    }

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor("black");
                    sibling.getLeft().setColor("black");
                    rotationRight(node.getParent());
                    node = treeRoot;
                }
            }
        }
        node.setColor("black");
    }
    
// ---------- Complementary Methods ------------  //
    public void rotationLeft(TreeNode node) {
        TreeNode auxNode = node.getRight();
        node.setRight(auxNode.getLeft());
        if (auxNode.getLeft() != treeNIL) {
            auxNode.getLeft().setParent(node);    // auxNode.left.parent = newNode; -> Verficar 
        }
        auxNode.setParent(node.getParent());
        if (node.getParent() == null) {
            this.treeRoot = auxNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(auxNode);   // newNode.parent.left = auxNode;  -> Verficar 
        } else {
            node.getParent().setRight(auxNode);
        }
        auxNode.setLeft(node);
        node.setParent(node);
    }

    public void rotationRight(TreeNode node) {
        TreeNode auxNode = node.getLeft();
        node.setLeft(auxNode.getRight());
        if (auxNode.getRight() != treeNIL) {
            auxNode.getRight().setParent(node);
        }
        auxNode.setParent(node.getParent());
        if (node.getParent() == null) {
            this.treeRoot = auxNode;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(auxNode);
        } else {
            node.getParent().setLeft(auxNode);
        }
        auxNode.setRight(node);
        node.setParent(auxNode);
    }

    public TreeNode findMin(TreeNode node) {
        while (node.getLeft() != treeNIL) {
            node = node.getLeft();
        }
        return node;
    }

    // Transferir un nodo del árbol al otro 
    private void nodeTransfer(TreeNode u, TreeNode v) {
        if (u.getParent() == null) {
            treeRoot = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }
    
      // --------------- Checkout methods --------------- //

    public void printTree() {
        printHelper(this.treeRoot, "", true);
    }

    private void printHelper(TreeNode root, String indent, boolean last) {
        if (root != treeNIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.getColor() == "red" ? "RED" : "BLACK";
            System.out.println(root.getPerson().getIdentity() + "(" + sColor + ")");
            printHelper(root.getLeft(), indent, false);
            printHelper(root.getRight(), indent, true);
        }
    }
    
    // ------------------ Gets & Setters ------------------ //

    public TreeNode getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeNode treeRoot) {
        this.treeRoot = treeRoot;
    }

    public TreeNode getTreeNIL() {
        return treeNIL;
    }

    public void setTreeNIL(TreeNode treeNIL) {
        this.treeNIL = treeNIL;
    }

}
