document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('livroSearch');
    const dropdown = document.getElementById('searchDropdown');
    const searchWrapper = document.querySelector('.search-wrapper');
    const isbnInput = document.getElementById('exemplarIsbn');
    const codigoInput = document.getElementById('exemplarCodigo');
    const dataEmprestimoInput = document.getElementById('dataEmprestimo');
    const dataPrevistaInput = document.getElementById('dataPrevista');
    const formEmprestimo = document.getElementById('formEmprestimo');

    // Pega todos os itens de busca
    const searchItems = document.querySelectorAll('.search-item');

    // Define data de hoje
    const hoje = new Date().toISOString().split('T')[0];
    dataEmprestimoInput.value = hoje;

    // Define data prevista (hoje + 14 dias)
    const dataPrevisao = new Date();
    dataPrevisao.setDate(dataPrevisao.getDate() + 14);
    dataPrevistaInput.value = dataPrevisao.toISOString().split('T')[0];

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

            // Preenche os campos hidden
            isbnInput.value = isbn;
            codigoInput.value = codigo;

            // Fecha o dropdown
            dropdown.classList.remove('show');
            searchWrapper.classList.remove('active');

            console.log('Selecionado:', {isbn, codigo, titulo}); // Debug
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