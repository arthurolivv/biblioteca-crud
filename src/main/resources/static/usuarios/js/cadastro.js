document.addEventListener("DOMContentLoaded", function () {

    // CPF
    IMask(document.getElementById('cpf'), {
        mask: '000.000.000-00'
    });

    // CEP
    IMask(document.getElementById('cep'), {
        mask: '00000-000'
    });

    // RG
    IMask(document.getElementById('rg'), {
        mask: [
            {
                mask: '00.000.000-0' // termina com número
            },
            {
                mask: '00.000.000-X', // termina com X (maiúsculo)
                blocks: {
                    X: {
                        mask: /^[A-Za-z]$/ // qualquer letra, mas mostra como X
                    }
                }
            }
        ]
    });

});
