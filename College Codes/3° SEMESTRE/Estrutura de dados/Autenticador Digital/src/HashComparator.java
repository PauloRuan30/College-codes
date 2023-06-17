import java.io.IOException;

public class HashComparator {

    private FileHashGenerator fileHashGenerator;

    public HashComparator() {
        this.fileHashGenerator = new FileHashGenerator();
    }

    public void setFile(String newFilePath) throws IOException {
        fileHashGenerator.loadFileAndGenerateHash(newFilePath);
    }

    public String getHash(){
        return fileHashGenerator.getFileHash();
    }

    public String getPath(){
        return fileHashGenerator.getPath();
    }

    public boolean isHashEqual(String anotherFilePath) throws IOException {
        FileHashGenerator anotherFileHashGenerator = new FileHashGenerator();
        anotherFileHashGenerator.loadFileAndGenerateHash(anotherFilePath);

        String hash1 = fileHashGenerator.getFileHash();
        String hash2 = anotherFileHashGenerator.getFileHash();

        if (hash1 == null || hash2 == null) {
            throw new IOException("Falha ao gerar hash de um dos arquivos.");
        }

        return hash1.equals(hash2);
    }

    public static void main(String[] args) {
        // Exemplo de uso
        HashComparator hashComparator = new HashComparator();

        try {
            hashComparator.setFile("C:\\Users\\GFarias\\Documents\\qualquer_coisa.txt");
            // Verificando se o hash do arquivo fornecido corresponde ao armazenado no HashComparator
            boolean isEqual = hashComparator.isHashEqual("C:\\Users\\GFarias\\Documents\\qualquer_coisa - Copia.txt");
            System.out.println("Os hashes são iguais? " + isEqual);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }
}