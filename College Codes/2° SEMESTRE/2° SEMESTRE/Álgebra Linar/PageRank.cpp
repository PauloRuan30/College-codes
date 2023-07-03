#include <iostream>
#include <vector>
#include <cmath>

// Função para imprimir a matriz
void printMatrix(const std::vector<std::vector<double>>& matrix) {
    for (const auto& row : matrix) {
        for (const auto& element : row) {
            std::cout << element << " ";
        }
        std::cout << std::endl;
    }
}

// Função para calcular o PageRank
std::vector<double> calculatePageRank(const std::vector<std::vector<double>>& matrix, double dampingFactor, int numIterations) {
    int n = matrix.size();

    // Verificar se a matriz é quadrada
    for (const auto& row : matrix) {
        if (row.size() != n) {
            std::cerr << "Erro: A matriz não é quadrada." << std::endl;
            return std::vector<double>();
        }
    }

    // Criar um vetor de probabilidade inicial com valores iguais
    std::vector<double> probability(n, 1.0 / n);

    // Calcular a matriz de transição dividindo cada elemento pelo número de links de saída
    std::vector<std::vector<double>> transitionMatrix(n, std::vector<double>(n, 0.0));
    for (int i = 0; i < n; i++) {
        int numOutlinks = 0;
        for (int j = 0; j < n; j++) {
            numOutlinks += matrix[i][j];
        }
        if (numOutlinks > 0) {
            for (int j = 0; j < n; j++) {
                transitionMatrix[i][j] = matrix[i][j] / numOutlinks;
            }
        }
        else {
            // Lidar com páginas sem links de saída (dead ends)
            for (int j = 0; j < n; j++) {
                transitionMatrix[i][j] = 1.0 / n;
            }
        }
    }

    // Executar iterações para calcular o PageRank
    double convergenceLimit = 1e-8;
    for (int iteration = 0; iteration < numIterations; iteration++) {
        std::vector<double> newProbability(n, 0.0);

        // Para cada página, calcular a nova probabilidade com base na matriz de transição
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newProbability[j] += dampingFactor * probability[i] * transitionMatrix[i][j];
            }
        }

        // Verificar convergência
        double convergence = 0.0;
        for (int i = 0; i < n; i++) {
            convergence += std::abs(newProbability[i] - probability[i]);
        }
        if (convergence < convergenceLimit) {
            break;
        }

        // Atualizar o vetor de probabilidade
        probability = newProbability;
    }

    return probability;
}

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
    }
    else if (menu == 2) {
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
    }
    else {
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