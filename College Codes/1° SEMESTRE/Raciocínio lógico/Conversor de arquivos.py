import json
import csv
import pandas as pd
import os

# Função para ler um arquivo JSON e retornar os dados
def read_json(file_path):
    with open(file_path, 'r') as file:
        data = json.load(file)
    return data

# Função para ler um arquivo CSV e retornar os dados
def read_csv(file_path):
    with open(file_path, 'r') as file:
        reader = csv.DictReader(file)
        data = list(reader)
    return data

# Função para ler um arquivo XLSX e retornar os dados
def read_xlsx(file_path):
    data = pd.read_excel(file_path)
    return data.to_dict('records')

# Função para escrever dados em um arquivo TXT
def write_txt(file_path, data):
    with open(file_path, 'w') as file:
        for item in data:
            file.write(str(item) + '\n')

# Função para escrever dados em um arquivo CSV
def write_csv(file_path, data):
    keys = data[0].keys()
    with open(file_path, 'w', newline='') as file:
        writer = csv.DictWriter(file, keys)
        writer.writeheader()
        writer.writerows(data)

# Função para escrever dados em um arquivo JSON
def write_json(file_path, data):
    with open(file_path, 'w') as file:
        json.dump(data, file, indent=4)

# Função para converter um arquivo JSON para CSV
def json_to_csv(json_path, csv_path):
    data = read_json(json_path)
    write_csv(csv_path, data)
    print(f'Arquivo JSON convertido para CSV: {csv_path}')

# Função para converter um arquivo CSV para JSON
def csv_to_json(csv_path, json_path):
    data = read_csv(csv_path)
    write_json(json_path, data)
    print(f'Arquivo CSV convertido para JSON: {json_path}')

# Função para converter um arquivo XLSX para CSV
def xlsx_to_csv(xlsx_path, csv_path):
    data = read_xlsx(xlsx_path)
    write_csv(csv_path, data)
    print(f'Arquivo XLSX convertido para CSV: {csv_path}')

# Função para converter um arquivo CSV para XLSX
def csv_to_xlsx(csv_path, xlsx_path):
    data = pd.DataFrame(read_csv(csv_path))
    data.to_excel(xlsx_path, index=False)
    print(f'Arquivo CSV convertido para XLSX: {xlsx_path}')

# Função para exibir o menu de seleção
def show_menu():
    print("Selecione a opção:")
    print("1. Gerar relatório .txt a partir de um arquivo JSON")
    print("2. Gerar relatório .txt a partir de um arquivo CSV")
    print("3. Gerar relatório .txt a partir de um arquivo XLSX")
    print("4. Converter arquivo JSON para CSV")
    print("5. Converter arquivo CSV para JSON")
    print("6. Converter arquivo XLSX para CSV")
    print("7. Converter arquivo CSV para XLSX")
    print("8. Sair")

# Função para limpar a tela do console
def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

# Função principal do programa
def main():
    while True:
        clear_screen()
        show_menu()
        option = input("Digite o número da opção desejada: ")
        
        if option == '1':
            json_path = input("Digite o caminho para o arquivo JSON: ")
            data = read_json(json_path)
            txt_path = input("Digite o caminho para salvar o relatório TXT: ")
            write_txt(txt_path, data)
            print(f'Relatório TXT gerado: {txt_path}')
        
        elif option == '2':
            csv_path = input("Digite o caminho para o arquivo CSV: ")
            data = read_csv(csv_path)
            txt_path = input("Digite o caminho para salvar o relatório TXT: ")
            write_txt(txt_path, data)
            print(f'Relatório TXT gerado: {txt_path}')
        
        elif option == '3':
            xlsx_path = input("Digite o caminho para o arquivo XLSX: ")
            data = read_xlsx(xlsx_path)
            txt_path = input("Digite o caminho para salvar o relatório TXT: ")
            write_txt(txt_path, data)
            print(f'Relatório TXT gerado: {txt_path}')
        
        elif option == '4':
            json_path = input("Digite o caminho para o arquivo JSON: ")
            csv_path = input("Digite o caminho para salvar o arquivo CSV: ")
            json_to_csv(json_path, csv_path)
        
        elif option == '5':
            csv_path = input("Digite o caminho para o arquivo CSV: ")
            json_path = input("Digite o caminho para salvar o arquivo JSON: ")
            csv_to_json(csv_path, json_path)
        
        elif option == '6':
            xlsx_path = input("Digite o caminho para o arquivo XLSX: ")
            csv_path = input("Digite o caminho para salvar o arquivo CSV: ")
            xlsx_to_csv(xlsx_path, csv_path)
        
        elif option == '7':
            csv_path = input("Digite o caminho para o arquivo CSV: ")
            xlsx_path = input("Digite o caminho para salvar o arquivo XLSX: ")
            csv_to_xlsx(csv_path, xlsx_path)
        
        elif option == '8':
            print("Programa encerrado.")
            break
        
        input("Pressione Enter para continuar...")

if __name__ == '__main__':
    main()
