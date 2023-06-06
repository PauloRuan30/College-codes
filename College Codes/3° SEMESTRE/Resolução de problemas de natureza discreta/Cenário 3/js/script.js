// Variáveis globais
const matriz = [];

// Mapeamento dos estados das placas
const estadoPlacas = {
  "JTA": "Pará",
  "JWE": "Pará",
  "NSE": "Pará",
  "NTC": "Pará",
  "OBT": "Pará",
  "OCA": "Pará",
  "OFI": "Pará",
  "OFW": "Pará",
  "SAK": "Amapá", 
  "SAM": "Amapá",
  "QLN": "Amapá",
  "QLT": "Amapá",
  "NEI": "Amapá",
  "NFB": "Amapá",
  "NUH": "Roraima",
  "NUL": "Roraima",
  "RZA": "Roraima",
  "RZB": "Roraima",
  // ... e assim por diante
};

// Função para adicionar um veículo à matriz
function adicionarVeiculo() {
  const placaInput = document.getElementById('placa-input');
  const placa = placaInput.value.toUpperCase();

  // Verificar se a placa é válida
  if (!validarPlaca(placa)) {
    alert('Placa inválida. Insira uma placa no padrão correto.');
    return;
  }

  // Verificar se a placa já está na matriz
  if (placaNaMatriz(placa)) {
    alert('Essa placa já está na matriz.');
    return;
  }

  // Obter o estado da placa
  const estado = obterEstadoPlaca(placa);

  // Adicionar veículo à matriz
  const veiculo = {
    placa: placa,
    estado: estado,
    tempo: 0,
    valor: 0
  };
  matriz.push(veiculo);

  // Atualizar a tabela
  atualizarTabela();

  // Limpar o campo de entrada
  placaInput.value = '';
}

// Função para obter o estado da placa
function obterEstadoPlaca(placa) {
  const letrasEstado = Object.keys(estadoPlacas);
  for (let i = 0; i < letrasEstado.length; i++) {
    const letras = letrasEstado[i];
    if (placa.startsWith(letras)) {
      return estadoPlacas[letras];
    }
  }
  return 'Desconhecido';
}

// Função para remover um veículo da matriz
function removerVeiculo() {
  const placaInput = document.getElementById('placa-saida-input');
  const placa = placaInput.value.toUpperCase();

  // Verificar se a placa é válida
  if (!validarPlaca(placa)) {
    alert('Placa inválida. Insira uma placa no padrão correto.');
    return;
  }

  // Verificar se o veículo está na matriz
  const index = encontrarVeiculo(placa);
  if (index === -1) {
    alert('Veículo não encontrado na matriz.');
    return;
  }

  // Remover veículo da matriz
  matriz.splice(index, 1);

  // Atualizar a tabela
  atualizarTabela();

  // Exibir mensagem de remoção
  const registroSaida = document.getElementById('registro-saida');
  registroSaida.textContent = 'Carro ' + placa + ' foi removido do estacionamento.';
  
  // Limpar o campo de saída
  placaInput.value = '';
}

// Função para adicionar tempo de estacionamento a um veículo na matriz
function adicionarTempoEstacionamento() {
  const placaInput = document.getElementById('placa-saida-input');
  const placa = placaInput.value.toUpperCase();

  // Verificar se a placa é válida
  if (!validarPlaca(placa)) {
    alert('Placa inválida. Insira uma placa no padrão correto.');
    return;
  }

  // Verificar se o veículo está na matriz
  const index = encontrarVeiculo(placa);
  if (index === -1) {
    alert('Veículo não encontrado na matriz.');
    return;
  }

  const tempoInput = document.getElementById('tempo-input');
  const tempo = parseInt(tempoInput.value);

  // Verificar se o tempo é válido
  if (isNaN(tempo) || tempo < 0) {
    alert('Tempo inválido. Insira um valor numérico positivo.');
    return;
  }

  // Atualizar o tempo do veículo na matriz
  matriz[index].tempo = tempo;

  // Calcular o valor a pagar
  matriz[index].valor = calcularValorEstacionamento(tempo);

  // Atualizar a tabela
  atualizarTabela();

  // Limpar os campos
  placaInput.value = '';
  tempoInput.value = '';
}

// Função para atualizar a tabela com a matriz de veículos
function atualizarTabela() {
  const tabela = document.getElementById('matriz');
  tabela.innerHTML = '';

  const cabecalho = document.createElement('tr');
  cabecalho.innerHTML = '<th>Posição</th><th>Placa</th><th>Estado</th><th>Tempo (minutos)</th><th>Valor a Pagar</th><th>Remover</th>';
  tabela.appendChild(cabecalho);

  for (let i = 0; i < matriz.length; i++) {
    const veiculo = matriz[i];
    const linha = document.createElement('tr');
    linha.innerHTML = '<td>' + (i + 1) + '</td><td>' + veiculo.placa + '</td><td>' + obterEstadoPlaca(veiculo.placa) + '</td><td>' + veiculo.tempo + '</td><td>' + veiculo.valor.toFixed(2) + '</td><td><button onclick="removerVeiculoPorIndice(' + i + ')">Remover</button></td>';
    tabela.appendChild(linha);
  }
}

// Função para validar o formato da placa
function validarPlaca(placa) {
  const regexPlaca = /^[A-Z]{3}\d[A-Z]{1}\d{2}$/;
  return regexPlaca.test(placa);
}

// Função para verificar se a placa já está na matriz
function placaNaMatriz(placa) {
  return matriz.some(veiculo => veiculo.placa === placa);
}

// Função para encontrar o índice do veículo na matriz
function encontrarVeiculo(placa) {
  for (let i = 0; i < matriz.length; i++) {
    if (matriz[i].placa === placa) {
      return i;
    }
  }
  return -1;
}

// Função para calcular o valor do estacionamento
function calcularValorEstacionamento(tempo) {
  const tolerancia = 15;
  const valorHora = 5.0;
  const valorFracaoHora = 2.5;

  if (tempo <= tolerancia) {
    return 0;
  } else if (tempo <= 180) {
    return valorHora;
  } else {
    const horas = Math.ceil(tempo / 60);
    return valorHora + (horas - 3) * valorFracaoHora;
  }
}

// Função para remover um veículo da matriz por índice
function removerVeiculoPorIndice(index) {
  const veiculo = matriz[index];
  matriz.splice(index, 1);
  atualizarTabela();
  const registroSaida = document.getElementById('registro-saida');
  registroSaida.textContent = 'Carro ' + veiculo.placa + ' foi removido do estacionamento.';
}

