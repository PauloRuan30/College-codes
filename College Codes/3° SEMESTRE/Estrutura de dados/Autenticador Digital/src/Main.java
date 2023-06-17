import com.sun.nio.sctp.IllegalReceiveException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static void definirCaminho(String message, HashComparator hashComparator, Scanner prompt) {
        System.out.print(message);
        String caminho;
        while (true) {
            try {
                caminho = prompt.nextLine();
                hashComparator.setFile(caminho);
                if (hashComparator.getHash() == null) throw new IOException();
                return;
            } catch (IOException | InputMismatchException e) {
                System.out.print("Caminho inválido, tente novamente: ");
            }
        }
    }


    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);
        HashComparator hashComparator = new HashComparator();
        System.out.println("--------------------AV3 ESTRUTURA DE DADOS--------------------");
        String message = "Informe o caminho do arquivo que será usado como parametro: ";
        definirCaminho(message, hashComparator, prompt);

        while (true) {
            System.out.println("\n---------------SELECIONAR ACAO---------------");
            System.out.println("1 - Validar arquivo txt");
            System.out.println("2 - Exibir caminho do arquivo usado como parametro");
            System.out.println("3 - Alterar arquivo usado como parametro");
            System.out.println("4 - Exibir hash do arquivo usado como parâmetro");
            System.out.println("99 - Encerrar programa");

            System.out.print("\nDigite o numero correspondente a acao desejada: ");
            int acao_principal;
            while (true){
                try{
                    acao_principal = prompt.nextInt();
                    if (acao_principal != 1 && acao_principal != 2 && acao_principal !=3 && acao_principal != 4 && acao_principal != 99) throw new IllegalArgumentException();
                    prompt.nextLine();
                    break;
                } catch (InputMismatchException | IllegalArgumentException e){
                    prompt.nextLine();
                    System.out.print("Acao desconhecida. Digite um numero valido dessa vez: ");
                }
            }

            switch (acao_principal) {
                case 1 -> {
                    System.out.println("\n--------VALIDAR ARQUIVO TXT--------");
                    System.out.print("Informe o caminho do arquivo txt a ser comparado: ");
                    String caminho;
                    boolean is_authentic;
                    while (true) {
                        try {
                            caminho = prompt.nextLine();
                            is_authentic = hashComparator.isHashEqual(caminho);
                            break;
                        } catch (IOException | InputMismatchException e) {
                            System.out.print("Caminho inválido, tente novamente: ");
                        }
                    }
                    if (is_authentic) System.out.println("Estamos diante de um arquivo AUTENTICO");
                    else System.out.println("Estamos diante de um arquivo NAO AUTENTICO");
                }
                case 2 -> {
                    System.out.println("\n---------------EXIBIR CAMINHO DO ARQUIVO TXT USADO COMO PARÂMETRO---------------");
                    System.out.println("Caminho: " + hashComparator.getPath());
                }
                case 3 -> {
                    System.out.println("\n--------------ALTERAR ARQUIVO USADO COMO PARAMETRO--------------");
                    message = "Informe o caminho do novo arquivo que será usado como parametro: ";
                    definirCaminho(message, hashComparator, prompt);
                }
                case 4 -> {
                    System.out.println("\n--------------EXIBIR HASH DO ARQUIVO USADO COMO PARÂMETRO--------------");
                    System.out.println("Hash do arquivo: " + hashComparator.getHash());
                }
                case 99 -> System.exit(0);
            }
        }
    }
}