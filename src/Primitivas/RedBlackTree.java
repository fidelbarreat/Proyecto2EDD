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

        //adjustAdd(newNode);
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
        // if (minOriginalColor == 0) {
        ///adjustRemove(child);
        //}
    }

// -------------- Adjust Tree Methods -------------- //
/*  private void adjustRemove( TreeNode node) {
        TreeNode sibling;
        while (node != treeRoot  )
    }
     */
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
