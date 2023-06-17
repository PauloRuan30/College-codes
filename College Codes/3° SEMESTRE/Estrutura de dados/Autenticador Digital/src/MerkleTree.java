public class MerkleTree {

    private MerkleNode root;

    public MerkleTree() {
        this.root = null;
    }

    public MerkleNode getRoot() {
        return root;
    }

    private int height(MerkleNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private MerkleNode rightRotate(MerkleNode y) {
        MerkleNode x = y.getLeft();
        MerkleNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        updateHash(y);
        updateHash(x);

        return x;
    }

    private MerkleNode leftRotate(MerkleNode x) {
        MerkleNode y = x.getRight();
        MerkleNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        updateHash(x);
        updateHash(y);

        return y;
    }

    private int getBalance(MerkleNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    public void insert(String line) {
        root = insert(root, line);
    }

    private MerkleNode insert(MerkleNode node, String line) {
        if (node == null) {
            return new MerkleNode(line);
        }

        if (line.compareTo(node.getLine()) < 0) {
            node.setLeft(insert(node.getLeft(), line));
        } else if (line.compareTo(node.getLine()) > 0) {
            node.setRight(insert(node.getRight(), line));
        } else {
            return node;
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && line.compareTo(node.getLeft().getLine()) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && line.compareTo(node.getRight().getLine()) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && line.compareTo(node.getLeft().getLine()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && line.compareTo(node.getRight().getLine()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        updateHash(node);

        return node;
    }

    private void updateHash(MerkleNode node) {
        String leftHash = (node.getLeft() != null) ? node.getLeft().getHash() : "";
        String rightHash = (node.getRight() != null) ? node.getRight().getHash() : "";
        String content = node.getLine();
        String combinedHash = content + leftHash + rightHash;

        node.setHash(node.generateHash(combinedHash));
    }

    public MerkleNode search(String line) {
        return search(root, line);
    }

    private MerkleNode search(MerkleNode root, String line) {
        if (root == null || root.getLine().equals(line)) {
            return root;
        }

        if (line.compareTo(root.getLine()) < 0) {
            return search(root.getLeft(), line);
        }

        return search(root.getRight(), line);
    }
}