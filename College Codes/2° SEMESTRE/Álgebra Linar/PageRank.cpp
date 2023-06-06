int main() {
    int menu;
    std::cout << "Digite 1 para usar a matriz pré-definida e 2 para escrever uma matriz personalizada: ";
    std::cin >> menu;

    std::vector<std::vector<double>> matrix;
    int n;
    
    if (menu == 1) {
        // Matriz de exemplo
        matrix = {
            {0, 1, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 0}
        };
    } else if (menu == 2) {
        std::cout << "Digite o tamanho da matriz: ";
        std::cin >> n;
        std::cout << "Digite a matriz de transicao:" << std::endl;
        for (int i = 0; i < n; i++) {
            std::vector<double> row(n);
            for (int j = 0; j < n; j++) {
                std::cin >> row[j];
            }
            matrix.push_back(row);
        }
    } else {
        std::cout << "Opção inválida." << std::endl;
        return 0;
    }

    // Fator de amortecimento e número de iterações
    double dampingFactor = 0.85;
    const int numIterations = 100;

    // Calcular o PageRank
    std::vector<double> pageRank = calculatePageRank(matrix, dampingFactor, numIterations);

    // Imprimir os resultados
    for (int i = 0; i < pageRank.size(); i++) {
        std::cout << "Page " << i << ": " << pageRank[i] << std::endl;
    }

    return 0;
}
