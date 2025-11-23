document.addEventListener('DOMContentLoaded', function() {
    var cnpjInput = document.getElementById('cnpjEditora');
    var form = document.querySelector('#modalCadastro form');

    function unmaskCnpj(cnpj) {
        return cnpj.replace(/\D/g, '');
    }

    function maskCnpj(value) {
        if (!value) return "";
        value = unmaskCnpj(value);

        if (value.length > 14) {
            value = value.substring(0, 14);
        }

        value = value.replace(/^(\d{2})(\d)/, '$1.$2');
        value = value.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
        value = value.replace(/\.(\d{3})(\d)/, '.$1/$2');
        value = value.replace(/(\d{4})(\d)/, '$1-$2');

        return value;
    }

    if (cnpjInput && cnpjInput.value) {
        cnpjInput.value = maskCnpj(cnpjInput.value);
    }

    if (cnpjInput) {
        cnpjInput.addEventListener('input', function(e) {
            e.target.value = maskCnpj(e.target.value);
        });
    }

    var hasValidationError = form && (form.querySelector('.is-invalid') || form.querySelector('.invalid-feedback'));

    if (hasValidationError) {
        var modalElement = document.getElementById('modalCadastro');
        if (modalElement) {
            var modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    }
});