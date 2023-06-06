const menuItems = document.querySelectorAll('#menu-h ul li a');
const alunosTable = document.querySelector('#alunos-table tbody');
const dengueTable = document.querySelector('#dengue-table tbody');
const onibusTable = document.querySelector('#onibus-table tbody');
const Rel1Table = document.querySelector('#Rel1-table tbody');
const Rel2Table = document.querySelector('#Rel2-table tbody');
const Rel3Table = document.querySelector('#Rel3-table tbody');
const Rel4Table = document.querySelector('#Rel4-table tbody');
const Rel5Table = document.querySelector('#Rel5-table tbody');


// função para criar linhas de tabela
function criarLinhasTabela(data, table) {
  table.innerHTML = '';
  data.forEach((item) => {
    const tr = document.createElement('tr');
    for (const key in item) {
      const td = document.createElement('td');
      td.textContent = item[key];
      tr.appendChild(td);
    }
    table.appendChild(tr);
  });
}

// função para carregar dados da tabela
function carregarDadosTabela(url, table) {
  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      criarLinhasTabela(data, table);
    });
}

// adiciona um evento de clique para cada item de menu
menuItems.forEach((item) => {
  item.addEventListener('click', (event) => {
    event.preventDefault();
    const url = event.target.getAttribute('data-json');
    const tableId = event.target.getAttribute('data-table');
    const table = document.querySelector(`#${tableId}-table tbody`);
    carregarDadosTabela(url, table);

    // Esconde todas as tabelas, exceto a que foi clicada
    document.querySelectorAll('table').forEach((t) => {
      if (t.id !== `${tableId}-table`) {
        t.style.display = 'none';
      } else {
        t.style.display = 'block';
      }
    });
  });
});
