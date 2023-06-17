import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileHashGenerator {

    private String fileHash;

    private String filePath;

    public void loadFileAndGenerateHash(String filePath) throws IOException {
        MerkleTree merkleTree = new MerkleTree();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                merkleTree.insert(line);
            }
        }

        // Atribuir o hash da raiz da MerkleTree para fileHash
        if (merkleTree.getRoot() != null) {
            this.fileHash = merkleTree.getRoot().getHash();
        } else {
            throw new IOException("O arquivo está vazio ou não pôde ser lido corretamente.");
        }

        this.filePath = filePath;
    }

    public String getFileHash() {
        return fileHash;
    }

    public String getPath(){
        return filePath;
    }

    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);
        FileHashGenerator fileHashGenerator = new FileHashGenerator();

        String filePath = "C:\\Users\\GFarias\\gamedev\\Shopping_estacionamento\\banco_dados\\placas.txt";

        try {
            // Fornecer o caminho do arquivo que você deseja gerar o hash
            fileHashGenerator.loadFileAndGenerateHash(filePath);
            System.out.println("Hash inicial do arquivo: " + fileHashGenerator.getFileHash());
            System.out.print("Faça mudanças ao arquivo no pc e então digite qualquer coisa para continuar: "); prompt.nextLine();
            fileHashGenerator.loadFileAndGenerateHash(filePath);
            System.out.println("Hash do arquivo: " + fileHashGenerator.getFileHash());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }
}
