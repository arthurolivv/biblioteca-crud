function alternarOrdenacao() {
    const input = document.getElementById('inputOrdenar');
    const icon = document.querySelector('#btnOrdenar i');
    const btn = document.getElementById('btnOrdenar');

    if (input.value === 'titulo_desc') {
        input.value = 'titulo_asc';
        icon.className = 'bi bi-sort-alpha-down';
        btn.title = 'Ordenar Z-A';
    } else {
        input.value = 'titulo_desc';
        icon.className = 'bi bi-sort-alpha-down-alt';
        btn.title = 'Ordenar A-Z';
    }

    document.getElementById('formFiltros').submit();
}