document.addEventListener('DOMContentLoaded', function() {
    var cnpjInput = document.getElementById('cnpjEditora');
    if (cnpjInput) {
        cnpjInput.addEventListener('input', function(e) {
            e.target.value = e.target.value.replace(/[^0-9]/g, '');
        });
    }

    var form = document.querySelector('#modalCadastro form');
    var hasValidationError = form && (form.querySelector('.is-invalid') || form.querySelector('.invalid-feedback'));

    if (hasValidationError) {
        var modalElement = document.getElementById('modalCadastro');
        if (modalElement) {
            var modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    }
});