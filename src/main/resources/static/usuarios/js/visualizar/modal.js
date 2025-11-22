document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('livroSearch');
    const dropdown = document.getElementById('searchDropdown');
    const searchWrapper = document.querySelector('.search-wrapper');
    const isbnInput = document.getElementById('isbn_exemplar');
    const codigoInput = document.getElementById('codigo_exemplar');
    const dataEmprestimoInput = document.getElementById('data_emprestimo');
    const dataPrevistaInput = document.getElementById('data_devolucao_prevista');
    const formEmprestimo = document.getElementById('formEmprestimo');

    // Pega todos os itens de busca
    const searchItems = document.querySelectorAll('.search-item');

// Formata data para dd/MM/yyyy
    function formatarDataBR(date) {
        const dia = String(date.getDate()).padStart(2, '0');
        const mes = String(date.getMonth() + 1).padStart(2, '0');
        const ano = date.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }

// Data de empréstimo = hoje
    const hoje = new Date();
    dataEmprestimoInput.value = formatarDataBR(hoje);

// Data prevista = hoje + 14 dias
    const dataPrevisao = new Date();
    dataPrevisao.setDate(dataPrevisao.getDate() + 14);
    dataPrevistaInput.value = formatarDataBR(dataPrevisao);

    // Mostra dropdown ao focar no input
    searchInput.addEventListener('focus', function () {
        dropdown.classList.add('show');
        searchWrapper.classList.add('active');
    });

    // Filtra itens conforme digita
    searchInput.addEventListener('input', function () {
        const searchTerm = this.value.toLowerCase().trim();
        let hasVisible = false;

        searchItems.forEach(item => {
            const titulo = item.getAttribute('data-livro-titulo').toLowerCase();
            const isbn = item.getAttribute('data-livro-isbn').toLowerCase();
            const codigo = item.getAttribute('data-exemplar-codigo').toLowerCase();

            if (titulo.includes(searchTerm) || isbn.includes(searchTerm) || codigo.includes(searchTerm)) {
                item.style.display = '';
                hasVisible = true;
            } else {
                item.style.display = 'none';
            }
        });

        dropdown.classList.add('show');
        searchWrapper.classList.add('active');
    });

// Seleciona um item
    searchItems.forEach(item => {
        item.addEventListener('click', function () {
            const isbn = this.getAttribute('data-exemplar-isbn');
            const codigo = this.getAttribute('data-exemplar-codigo');
            const titulo = this.getAttribute('data-livro-titulo');

            // Preenche o input visível
            searchInput.value = `${titulo} (${codigo})`;

            // Preenche os campos hidden que serão enviados
            document.getElementById('isbn_exemplar').value = isbn;
            document.getElementById('codigo_exemplar').value = codigo;

            // Fecha o dropdown
            dropdown.classList.remove('show');
            searchWrapper.classList.remove('active');
        });
    });

    // Fecha dropdown ao clicar fora
    document.addEventListener('click', function (e) {
        if (!searchWrapper.contains(e.target)) {
            dropdown.classList.remove('show');
            searchWrapper.classList.remove('active');
        }
    });

    // Validação antes de submeter
    formEmprestimo.addEventListener('submit', function (e) {
        if (!isbnInput.value || !codigoInput.value) {
            e.preventDefault();
            alert('Por favor, selecione um exemplar da lista.');
            searchInput.focus();
            return false;
        }
    });
});

