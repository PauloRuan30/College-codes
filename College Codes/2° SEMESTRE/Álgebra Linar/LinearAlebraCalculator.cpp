#include <iostream>
#include <vector>
#include <cmath>

class Matrix {
private:
    int rows;
    int cols;
    std::vector<double> elements;

public:
    Matrix(int r, int c, const std::vector<double>& value)
        : rows(r), cols(c), elements(value) {}

    double get(int l, int c) const {
        int index = l * cols - cols;
        index = index + c - 1;
        return elements[index];
    }

    std::vector<double> getElements() const {
        return elements;
    }

    int getCols() const {
        return cols;
    }

    int getRows() const {
        return rows;
    }

    void setElements(const std::vector<double>& elements) {
        this->elements = elements;
    }

    void set(int l, int c, double value) {
        int index = l * cols - cols;
        index = index + c - 1;
        elements[index] = value;
    }
};

class Vector {
private:
    std::vector<double> elements;

public:
    Vector(const std::vector<double>& value)
        : elements(value) {}

    double get(int i) const {
        return elements[i];
    }

    std::vector<double> getElements() const {
        return elements;
    }

    int getSize() const {
        return elements.size();
    }

    void set(int index, double value) {
        elements[index] = value;
    }
};

class LinearAlgebra {
public:
    static Matrix transpose(const Matrix& m) {
        std::vector<double> transposedElements;

        for (int i = 0; i < m.getCols(); ++i) {
            for (int j = i; j < m.getElements().size(); j += m.getCols()) {
                transposedElements.push_back(m.getElements()[j]);
            }
        }

        Matrix transposed(m.getCols(), m.getRows(), transposedElements);
        return transposed;
    }

    static Vector times(const Matrix& a, const Vector& b) {
        std::vector<double> result;

        for (int i = 0; i < a.getRows() * a.getCols(); i += a.getCols()) {
            double aux = 0.0;

            for (int j = 0; j < a.getCols(); ++j) {
                aux += a.get(i + j) * b.get(j);
            }

            result.push_back(aux);
        }

        Vector multipliedResult(result);
        return multipliedResult;
    }

    static Matrix times(const Matrix& a, const Matrix& b) {
        std::vector<double> result;

        for (int k = 0; k < a.getCols(); ++k) {
            for (int i = 0; i < a.getCols(); ++i) {
                int cont = 0;
                double num = 0.0;

                for (int j = i; j < b.getElements().size(); j += a.getCols()) {
                    num += a.getElements()[j] * b.getElements()[k * a.getRows() + cont];
                    ++cont;
                }

                result.push_back(num);
            }
        }

        Matrix multipliedResult(a.getRows(), b.getCols(), result);
        return multipliedResult;
    }

    static Matrix times(const Matrix& v, double d) {
        std::vector<double> result;
        for (double i : v.getElements()) {
            result.push_back(i * d);
        }
        return Matrix(v.getRows(), v.getCols(), result);
    }

    static Vector times(const Vector& v, double d) {
        std::vector<double> result;
        for (double i : v.getElements()) {
            result.push_back(i * d);
        }
        return Vector(result);
    }

    static Vector sum(const Vector& a, const Vector& b) {
        std::vector<double> c;
        for (int i = 0; i < a.getElements().size(); ++i) {
            c.push_back(a.get(i) + b.get(i));
        }
        return Vector(c);
    }

    static double normaVector(const Vector& v) {
        double aux = 0.0;
        for (double i : v.getElements()) {
            aux += std::pow(i, 2.0);
        }
        return std::sqrt(aux);
    }
};

class Transformations : public LinearAlgebra {
public:
    Vector translacao2D(const Vector& value, const Vector& transformation) {
        return sum(value, transformation);
    }

    Vector translacao3D(const Vector& value, const Vector& transformation) {
        return sum(value, transformation);
    }

    Vector rotacao2D(const Vector& value, double angle) {
        std::vector<double> aux{
            std::cos(angle), -1.0 * std::sin(angle),
            std::sin(angle), std::cos(angle)
        };

        Matrix transformation(2, 2, aux);
        Vector result = times(transformation, value);
        return result;
    }

    Vector rotacao3DX(const Vector& value, double angle) {
        std::vector<double> aux{
            1.0, 0.0, 0.0, 0.0,
            0.0, std::cos(angle), -1.0 * std::sin(angle), 0.0,
            0.0, std::sin(angle), std::cos(angle), 0.0,
            0.0, 0.0, 0.0, 1.0
        };

        Matrix transformation(4, 4, aux);
        Vector modifiedValue = value;
        modifiedValue.getElements().push_back(1.0);
        Vector result = times(transformation, modifiedValue);
        result.getElements().pop_back();
        return result;
    }

    Vector rotacao3DY(const Vector& value, double angle) {
        std::vector<double> aux{
            std::cos(angle), 0.0, std::sin(angle), 0.0,
            0.0, 1.0, 0.0, 0.0,
            -1.0 * std::sin(angle), 0.0, std::cos(angle), 0.0,
            0.0, 0.0, 0.0, 1.0
        };

        Matrix transformation(4, 4, aux);
        Vector modifiedValue = value;
        modifiedValue.getElements().push_back(1.0);
        Vector result = times(transformation, modifiedValue);
        result.getElements().pop_back();
        return result;
    }

    Vector rotacao3DZ(const Vector& value, double angle) {
        std::vector<double> aux{
            std::cos(angle), -1.0 * std::sin(angle), 0.0, 0.0,
            std::sin(angle), std::cos(angle), 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
        };

        Matrix transformation(4, 4, aux);
        Vector modifiedValue = value;
        modifiedValue.getElements().push_back(1.0);
        Vector result = times(transformation, modifiedValue);
        result.getElements().pop_back();
        return result;
    }
};

int main() {
    // Test the implemented functionality
    std::vector<double> values1{1.0, 2.0, 3.0};
    std::vector<double> values2{4.0, 5.0, 6.0};
    Vector vector1(values1);
    Vector vector2(values2);

    std::vector<double> elements{
        1.0, 2.0, 3.0,
        4.0, 5.0, 6.0,
        7.0, 8.0, 9.0
    };
    Matrix matrix(elements);

    Matrix transposedMatrix = LinearAlgebra::transpose(matrix);
    Vector multipliedVector = LinearAlgebra::times(matrix, vector1);
    Matrix multipliedMatrix = LinearAlgebra::times(matrix, transposedMatrix);
    Matrix scaledMatrix = LinearAlgebra::times(matrix, 2.0);
    Vector scaledVector = LinearAlgebra::times(vector2, 3.0);
    Vector sumVector = LinearAlgebra::sum(vector1, vector2);
    double norm = LinearAlgebra::normaVector(vector1);

    std::cout << "Transposed Matrix:" << std::endl;
    for (int i = 1; i <= transposedMatrix.getRows(); ++i) {
        for (int j = 1; j <= transposedMatrix.getCols(); ++j) {
            std::cout << transposedMatrix.get(i, j) << " ";
        }
        std::cout << std::endl;
    }

    std::cout << "Multiplied Vector:" << std::endl;
    for (int i = 0; i < multipliedVector.getSize(); ++i) {
        std::cout << multipliedVector.get(i) << " ";
    }
    std::cout << std::endl;

    std::cout << "Multiplied Matrix:" << std::endl;
    for (int i = 1; i <= multipliedMatrix.getRows(); ++i) {
        for (int j = 1; j <= multipliedMatrix.getCols(); ++j) {
            std::cout << multipliedMatrix.get(i, j) << " ";
        }
        std::cout << std::endl;
    }

    std::cout << "Scaled Matrix:" << std::endl;
    for (int i = 1; i <= scaledMatrix.getRows(); ++i) {
        for (int j = 1; j <= scaledMatrix.getCols(); ++j) {
            std::cout << scaledMatrix.get(i, j) << " ";
        }
        std::cout << std::endl;
    }

    std::cout << "Scaled Vector:" << std::endl;
    for (int i = 0; i < scaledVector.getSize(); ++i) {
        std::cout << scaledVector.get(i) << " ";
    }
    std::cout << std::endl;

    std::cout << "Sum Vector:" << std::endl;
    for (int i = 0; i < sumVector.getSize(); ++i) {
        std::cout << sumVector.get(i) << " ";
    }
    std::cout << std::endl;

    std::cout << "Norm of Vector: " << norm << std::endl;

    return 0;
}
