document.addEventListener('DOMContentLoaded', function () {

    const hasValidationError = document.querySelector('.is-invalid'); // Input com erro
    const hasErrorMessage = document.querySelector('.alert-danger');  // Mensagem de erro geral
    const modalCadastroElement = document.getElementById('modalCadastro');

    if (modalCadastroElement) {
        const modalCadastro = new bootstrap.Modal(modalCadastroElement, {});

        if (hasValidationError || (hasErrorMessage && !hasErrorMessage.innerText.includes('excluída com sucesso'))) {
            modalCadastro.show();
        }
    }

    const alertList = document.querySelectorAll('.alert');
    alertList.forEach(function (alert) {
        new bootstrap.Alert(alert);
    });
});