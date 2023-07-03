// Java GUI imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Java base imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Doador {
    private int id;
    private String nome;
    private String tipoSanguineo;
    private String fatorSanguineo;
    private String contato;
    private String cpf;
    private boolean doencaSanguinea;
    private String dataCadastro;

    public Doador(/* int id */ String nome, String tipoSanguineo, String fatorSanguineo, String contato, String cpf,
                  boolean doencaSanguinea, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.tipoSanguineo = tipoSanguineo;
        this.fatorSanguineo = fatorSanguineo;
        this.contato = contato;
        this.cpf = cpf;
        this.doencaSanguinea = doencaSanguinea;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
    
    public int  getid() {
        return id;
    }    
    
    public void setid(int id){
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getFatorSanguineo() {
        return fatorSanguineo;
    }

    public void setFatorSanguineo(String fatorSanguineo) {
        this.fatorSanguineo = fatorSanguineo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isDoencaSanguinea() {
        return doencaSanguinea;
    }

    public void setDoencaSanguinea(boolean doencaSanguinea) {
        this.doencaSanguinea = doencaSanguinea;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}

class BancoDeSangue {
    private List<Doador> doadores;
    private int[] quantidadeBolsas;
    private int[] quantidadeBolsasSolicitadas;

    public BancoDeSangue() {
        doadores = new ArrayList<>();
        quantidadeBolsas = new int[8];
        quantidadeBolsasSolicitadas = new int[8];
    }

    public List<Doador> getDoadores() {
        return doadores;
    }

    public void adicionarDoador(Doador doador) {
        doadores.add(doador);
        incrementarQuantidadeBolsas(doador.getTipoSanguineo());
    }

    public void removerDoador(Doador doador) {
        doadores.remove(doador);
        decrementarQuantidadeBolsas(doador.getTipoSanguineo());
    }

    private void incrementarQuantidadeBolsas(String tipoSanguineo) {
        if (tipoSanguineo.equals("A+")) {
            quantidadeBolsas[0]++;
        } else if (tipoSanguineo.equals("A-")) {
            quantidadeBolsas[1]++;
        } else if (tipoSanguineo.equals("B+")) {
            quantidadeBolsas[2]++;
        } else if (tipoSanguineo.equals("B-")) {
            quantidadeBolsas[3]++;
        } else if (tipoSanguineo.equals("AB+")) {
            quantidadeBolsas[4]++;
        } else if (tipoSanguineo.equals("AB-")) {
            quantidadeBolsas[5]++;
        } else if (tipoSanguineo.equals("O+")) {
            quantidadeBolsas[6]++;
        } else if (tipoSanguineo.equals("O-")) {
            quantidadeBolsas[7]++;
        }
    }

    private void decrementarQuantidadeBolsas(String tipoSanguineo) {
        if (tipoSanguineo.equals("A+")) {
            quantidadeBolsas[0]--;
        } else if (tipoSanguineo.equals("A-")) {
            quantidadeBolsas[1]--;
        } else if (tipoSanguineo.equals("B+")) {
            quantidadeBolsas[2]--;
        } else if (tipoSanguineo.equals("B-")) {
            quantidadeBolsas[3]--;
        } else if (tipoSanguineo.equals("AB+")) {
            quantidadeBolsas[4]--;
        } else if (tipoSanguineo.equals("AB-")) {
            quantidadeBolsas[5]--;
        } else if (tipoSanguineo.equals("O+")) {
            quantidadeBolsas[6]--;
        } else if (tipoSanguineo.equals("O-")) {
            quantidadeBolsas[7]--;
        }
    }

    public int[] getQuantidadeBolsas() {
        return quantidadeBolsas;
    }

    public int[] getQuantidadeBolsasSolicitadas() {
        return quantidadeBolsasSolicitadas;
    }
    
    public void adicionarBolsa(String tipoSanguineo) {
    if (tipoSanguineo.equals("A+")) {
        quantidadeBolsas[0]++;
    } else if (tipoSanguineo.equals("A-")) {
        quantidadeBolsas[1]++;
    } else if (tipoSanguineo.equals("B+")) {
        quantidadeBolsas[2]++;
    } else if (tipoSanguineo.equals("B-")) {
        quantidadeBolsas[3]++;
    } else if (tipoSanguineo.equals("AB+")) {
        quantidadeBolsas[4]++;
    } else if (tipoSanguineo.equals("AB-")) {
        quantidadeBolsas[5]++;
    } else if (tipoSanguineo.equals("O+")) {
        quantidadeBolsas[6]++;
    } else if (tipoSanguineo.equals("O-")) {
        quantidadeBolsas[7]++;
    }
}

    public void solicitarBolsa(String tipoSanguineo) {
        if (tipoSanguineo.equals("A+")) {
            if (quantidadeBolsas[0] > 0) {
                quantidadeBolsas[0]--;
                quantidadeBolsasSolicitadas[0]++;
            }
        } else if (tipoSanguineo.equals("A-")) {
            if (quantidadeBolsas[1] > 0) {
                quantidadeBolsas[1]--;
                quantidadeBolsasSolicitadas[1]++;
            }
        } else if (tipoSanguineo.equals("B+")) {
            if (quantidadeBolsas[2] > 0) {
                quantidadeBolsas[2]--;
                quantidadeBolsasSolicitadas[2]++;
            }
        } else if (tipoSanguineo.equals("B-")) {
            if (quantidadeBolsas[3] > 0) {
                quantidadeBolsas[3]--;
                quantidadeBolsasSolicitadas[3]++;
            }
        } else if (tipoSanguineo.equals("AB+")) {
            if (quantidadeBolsas[4] > 0) {
                quantidadeBolsas[4]--;
                quantidadeBolsasSolicitadas[4]++;
            }
        } else if (tipoSanguineo.equals("AB-")) {
            if (quantidadeBolsas[5] > 0) {
                quantidadeBolsas[5]--;
                quantidadeBolsasSolicitadas[5]++;
            }
        } else if (tipoSanguineo.equals("O+")) {
            if (quantidadeBolsas[6] > 0) {
                quantidadeBolsas[6]--;
                quantidadeBolsasSolicitadas[6]++;
            }
        } else if (tipoSanguineo.equals("O-")) {
            if (quantidadeBolsas[7] > 0) {
                quantidadeBolsas[7]--;
                quantidadeBolsasSolicitadas[7]++;
            }
        }
    }
}

class Main { // SistemaBancoDeSangue
    private static BancoDeSangue bancoDeSangue;
    private static Scanner scanner;

    public static void main(String[] args) {
        bancoDeSangue = new BancoDeSangue();
        scanner = new Scanner(System.in);

        exibirMenuLogin();
    }

    private static void exibirMenuLogin() {
        System.out.println("==== Login ====");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (login.equals("adm") && senha.equals("admin")) {
            System.out.println("Login realizado com sucesso como administrador.");
            exibirMenuAdministrador();
        } else if (loginExistente(login, senha)) {
            System.out.println("Doador já cadastrado.");
            exibirMenuPrincipal();
        } else {
            System.out.println("Doador não encontrado. Realize o cadastro.");
            realizarCadastro();
        }
    }

    private static boolean loginExistente(String login, String senha) {
        List<Doador> doadores = bancoDeSangue.getDoadores();
        for (Doador doador : doadores) {
            if (doador.getNome().equals(login) && doador.getTipoSanguineo().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    private static void exibirMenuAdministrador() {
        System.out.println("\n==== Menu Administrador ====");
        System.out.println("1. Visualizar lista de doadores");
        System.out.println("2. Adicionar bolsa de sangue");
        System.out.println("3. Remover doador");
        System.out.println("4. Remover lista de doadores");
        System.out.println("5. Visualizar quantidade de bolsas de sangue");
        System.out.println("6. Sair");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                exibirListaDoadores();
                break;
            case 2:
                adicionarBolsaSangue();
                break;
            case 3:
                exibirMenuRemoverDoador();
                break;
            case 4:
                removerListaDoadores();
                break;
            case 5:
                visualizarQuantidadeBolsas();
                break;
            case 6:
                System.out.println("Saindo do sistema...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                exibirMenuAdministrador();
                break;
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n==== Menu Principal ====");
        System.out.println("1. Visualizar lista de doadores");
        System.out.println("2. Solicitar bolsa de sangue");
        System.out.println("3. Remover cadastro");
        System.out.println("4. Sair");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                exibirListaDoadores();
                break;
            case 2:
                solicitarBolsaSangue();
                break;
            case 3:
                removerCadastro();
                break;
            case 4:
                System.out.println("Saindo do sistema...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                exibirMenuPrincipal();
                break;
        }
    }

    private static void exibirListaDoadores() {
        List<Doador> doadores = bancoDeSangue.getDoadores();
        System.out.println("\n==== Lista de Doadores ====");
        for (Doador doador : doadores) {
            System.out.println("Nome: " + doador.getNome());
            System.out.println("Tipo Sanguíneo: " + doador.getTipoSanguineo());
            System.out.println("Fator Sanguíneo: " + doador.getFatorSanguineo());
            System.out.println("Contato: " + doador.getContato());
            System.out.println("CPF: " + doador.getCpf());
            System.out.println("Doença Sanguínea: " + (doador.isDoencaSanguinea() ? "Sim" : "Não"));
            System.out.println("Data de Cadastro: " + doador.getDataCadastro());
            System.out.println("-----");
        }
        if (doadores.isEmpty()) {
            System.out.println("Nenhum doador cadastrado.");
        }
        if (loginExistente("adm", "admin")) {
            System.out.println("Quantidade de bolsas de sangue:");
            int[] quantidadeBolsas = bancoDeSangue.getQuantidadeBolsas();
            System.out.println("A+: " + quantidadeBolsas[0]);
            System.out.println("A-: " + quantidadeBolsas[1]);
            System.out.println("B+: " + quantidadeBolsas[2]);
            System.out.println("B-: " + quantidadeBolsas[3]);
            System.out.println("AB+: " + quantidadeBolsas[4]);
            System.out.println("AB-: " + quantidadeBolsas[5]);
            System.out.println("O+: " + quantidadeBolsas[6]);
            System.out.println("O-: " + quantidadeBolsas[7]);
        }
        exibirMenuPrincipal();
    }

    private static void adicionarBolsaSangue() {
        System.out.print("\nTipo Sanguíneo (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
        String tipoSanguineo = scanner.nextLine().toUpperCase();
        bancoDeSangue.adicionarBolsa(tipoSanguineo);
        System.out.println("Bolsa de sangue adicionada com sucesso.");
        exibirMenuAdministrador();
    }

    private static void exibirMenuRemoverDoador() {
        List<Doador> doadores = bancoDeSangue.getDoadores();
        System.out.println("\n==== Remover Doador ====");
        System.out.println("Selecione o número do doador que deseja remover:");
        for (int i = 0; i < doadores.size(); i++) {
            System.out.println((i + 1) + ". " + doadores.get(i).getNome());
        }
        System.out.println((doadores.size() + 1) + ". Voltar ao menu anterior");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (opcao >= 1 && opcao <= doadores.size()) {
            Doador doadorRemover = doadores.get(opcao - 1);
            bancoDeSangue.removerDoador(doadorRemover);
            System.out.println("Doador removido com sucesso.");
        } else if (opcao == doadores.size() + 1) {
            exibirMenuAdministrador();
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
        exibirMenuRemoverDoador();
    }

    private static void removerListaDoadores() {
        System.out.print("Tem certeza que deseja remover todos os doadores? (S/N): ");
        String confirmacao = scanner.nextLine().toUpperCase();

        if (confirmacao.equals("S")) {
            bancoDeSangue.getDoadores().clear();
            bancoDeSangue = new BancoDeSangue();
            System.out.println("Lista de doadores removida com sucesso.");
        }
        exibirMenuAdministrador();
    }

    private static void visualizarQuantidadeBolsas() {
        System.out.println("\n==== Quantidade de Bolsas de Sangue ====");
        int[] quantidadeBolsas = bancoDeSangue.getQuantidadeBolsas();
        System.out.println("A+: " + quantidadeBolsas[0]);
        System.out.println("A-: " + quantidadeBolsas[1]);
        System.out.println("B+: " + quantidadeBolsas[2]);
        System.out.println("B-: " + quantidadeBolsas[3]);
        System.out.println("AB+: " + quantidadeBolsas[4]);
        System.out.println("AB-: " + quantidadeBolsas[5]);
        System.out.println("O+: " + quantidadeBolsas[6]);
        System.out.println("O-: " + quantidadeBolsas[7]);
        exibirMenuAdministrador();
    }

    private static void realizarCadastro() {
        System.out.println("\n==== Cadastro de Doador ====");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo Sanguíneo (A, B, AB, O): ");
        String tipoSanguineo = scanner.nextLine().toUpperCase();
        System.out.print("Fator Sanguíneo (+ ou -): ");
        String fatorSanguineo = scanner.nextLine();
        System.out.print("Contato: ");
        String contato = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Portador de doença sanguínea? (S/N): ");
        boolean doencaSanguinea = scanner.nextLine().equalsIgnoreCase("S");
        String dataCadastro = java.time.LocalDate.now().toString();

        Doador novoDoador = new Doador(nome, tipoSanguineo, fatorSanguineo, contato, cpf, doencaSanguinea, dataCadastro);
        bancoDeSangue.adicionarDoador(novoDoador);

        System.out.println("Cadastro realizado com sucesso.");
        exibirMenuPrincipal();
    }

    private static void solicitarBolsaSangue() {
        System.out.print("\nTipo Sanguíneo (A, B, AB, O): ");
        String tipoSanguineo = scanner.nextLine().toUpperCase();
        bancoDeSangue.solicitarBolsa(tipoSanguineo);
        System.out.println("Solicitação de bolsa de sangue realizada com sucesso.");
        exibirMenuPrincipal();
    }

    private static void removerCadastro() {
        List<Doador> doadores = bancoDeSangue.getDoadores();
        System.out.println("\n==== Remover Cadastro ====");
        System.out.println("Selecione o número do seu cadastro que deseja remover:");
        for (int i = 0; i < doadores.size(); i++) {
            if (doadores.get(i).getNome().equals("adm") && doadores.get(i).getTipoSanguineo().equals("admin")) {
                continue;
            }
            System.out.println((i + 1) + ". " + doadores.get(i).getNome());
        }
        System.out.println((doadores.size() + 1) + ". Voltar ao menu anterior");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (opcao >= 1 && opcao <= doadores.size()) {
            Doador doadorRemover = doadores.get(opcao - 1);
            bancoDeSangue.removerDoador(doadorRemover);
            System.out.println("Cadastro removido com sucesso.");
        } else if (opcao == doadores.size() + 1) {
            exibirMenuPrincipal();
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
        exibirMenuRemoverDoador();
    }
}

// main
