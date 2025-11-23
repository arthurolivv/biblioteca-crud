// ==================== SCRIPT PARA RESERVA ====================
document.addEventListener('DOMContentLoaded', function() {
    const searchInputReserva = document.getElementById('livroSearchReserva');
    const dropdownReserva = document.getElementById('searchDropdownReserva');
    const searchItemsReserva = document.querySelectorAll('.search-item-reserva');
    const isbnInputReserva = document.getElementById('isbn_livro');
    const dataReservaInput = document.getElementById('data_reserva');
    const noResultsMessage = document.getElementById('noResultsMessage');

    // Define a data atual no campo de data de reserva
    const hoje = new Date();
    const dataFormatada = hoje.toLocaleDateString('pt-BR');
    dataReservaInput.value = dataFormatada;

    // Mostrar dropdown ao focar no input
    searchInputReserva.addEventListener('focus', function() {
        dropdownReserva.style.display = 'block';
    });

    // Filtrar livros conforme o usuário digita
    searchInputReserva.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        let hasVisibleItems = false;

        searchItemsReserva.forEach(item => {
            const titulo = item.getAttribute('data-livro-titulo').toLowerCase();

            if (titulo.includes(searchTerm)) {
                item.style.display = 'block';
                hasVisibleItems = true;
            } else {
                item.style.display = 'none';
            }
        });

        // Mostrar mensagem "Nenhum livro encontrado" se não houver resultados
        if (!hasVisibleItems && searchTerm !== '') {
            noResultsMessage.style.display = 'block';
        } else {
            noResultsMessage.style.display = 'none';
        }

        dropdownReserva.style.display = 'block';
    });

    // Selecionar livro ao clicar
    searchItemsReserva.forEach(item => {
        item.addEventListener('click', function() {
            const isbn = this.getAttribute('data-livro-isbn');
            const titulo = this.getAttribute('data-livro-titulo');

            // Preencher campos
            searchInputReserva.value = titulo;
            isbnInputReserva.value = isbn;

            // Fechar dropdown
            dropdownReserva.style.display = 'none';
        });
    });

    // Fechar dropdown ao clicar fora
    document.addEventListener('click', function(e) {
        if (!searchInputReserva.contains(e.target) && !dropdownReserva.contains(e.target)) {
            dropdownReserva.style.display = 'none';
        }
    });

    // Limpar formulário ao fechar modal
    const modalReserva = document.getElementById('modalNovaReserva');
    modalReserva.addEventListener('hidden.bs.modal', function() {
        searchInputReserva.value = '';
        isbnInputReserva.value = '';

        // Resetar data para hoje
        dataReservaInput.value = dataFormatada;

        // Mostrar todos os itens novamente
        searchItemsReserva.forEach(item => {
            item.style.display = 'block';
        });

        // Esconder mensagem de "não encontrado"
        noResultsMessage.style.display = 'none';
    });
});