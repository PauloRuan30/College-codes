import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MerkleNode {

    private static final String HASH_ALGORITHM = "SHA-1";
    private String hash;
    private String line;
    private int height;
    private MerkleNode left, right;

    public MerkleNode(String line) {
        this.line = line;
        this.hash = generateHash(line);
        this.height = 1;
    }

    // Método agora é público para ser acessado de MerkleTree
    public String generateHash(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] bytes = digest.digest(content.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não suportado: " + HASH_ALGORITHM, e);
        }
    }

    // Getters e Setters
    public String getHash() {
        return hash;
    }

    // Setter para hash
    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getLine() {
        return line;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MerkleNode getLeft() {
        return left;
    }

    public void setLeft(MerkleNode left) {
        this.left = left;
    }

    public MerkleNode getRight() {
        return right;
    }

    public void setRight(MerkleNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "MerkleNode [hash=" + hash + ", line=" + line + ", height=" + height + "]";
    }
}