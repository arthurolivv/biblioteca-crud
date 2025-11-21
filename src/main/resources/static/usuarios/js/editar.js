document.addEventListener('DOMContentLoaded', function () {
    const senhaAtual = document.getElementById('senhaAtual');
    const senha = document.getElementById('senha');
    const confirmarSenha = document.getElementById('confirmarSenha');
    const senhaErro = document.getElementById('senhaErro');
    const senhaOk = document.getElementById('senhaOk');
    const form = document.querySelector('form');

    // Validação em tempo real
    function validarSenhas() {
        const senhaValue = senha.value;
        const confirmarValue = confirmarSenha.value;

        // Se ambos estão vazios, está OK (não vai alterar senha)
        if (senhaValue === '' && confirmarValue === '') {
            senhaErro.style.display = 'none';
            senhaOk.style.display = 'none';
            confirmarSenha.setCustomValidity('');
            return true;
        }

        // Se apenas um está preenchido
        if (senhaValue === '' || confirmarValue === '') {
            senhaErro.style.display = 'none';
            senhaOk.style.display = 'none';
            return false;
        }

        // Se ambos preenchidos mas diferentes
        if (senhaValue !== confirmarValue) {
            senhaErro.style.display = 'block';
            senhaOk.style.display = 'none';
            confirmarSenha.setCustomValidity('As senhas não coincidem');
            return false;
        }

        // Se tudo certo
        senhaErro.style.display = 'none';
        senhaOk.style.display = 'block';
        confirmarSenha.setCustomValidity('');
        return true;
    }

    // Função para verificar se está tentando alterar senha
    function verificarAlteracaoSenha() {
        const senhaAtualValue = senhaAtual.value;
        const senhaValue = senha.value;
        const confirmarValue = confirmarSenha.value;

        // Se digitou senha atual mas não preencheu as outras
        if (senhaAtualValue !== '' && (senhaValue === '' || confirmarValue === '')) {
            // Adiciona dica visual
            if (senha.value === '') {
                senha.classList.add('border-warning');
            }
            if (confirmarSenha.value === '') {
                confirmarSenha.classList.add('border-warning');
            }
        } else {
            senha.classList.remove('border-warning');
            confirmarSenha.classList.remove('border-warning');
        }

        // Se digitou nova senha mas não a atual
        if ((senhaValue !== '' || confirmarValue !== '') && senhaAtualValue === '') {
            senhaAtual.classList.add('border-warning');
        } else {
            senhaAtual.classList.remove('border-warning');
        }
    }

    // Eventos
    senha.addEventListener('input', function () {
        validarSenhas();
        verificarAlteracaoSenha();
    });

    confirmarSenha.addEventListener('input', function () {
        validarSenhas();
        verificarAlteracaoSenha();
    });

    senhaAtual.addEventListener('input', verificarAlteracaoSenha);

    // Validação antes de enviar
    form.addEventListener('submit', function (e) {
        const senhaAtualValue = senhaAtual.value;
        const senhaValue = senha.value;
        const confirmarValue = confirmarSenha.value;

        // Se está tentando alterar senha
        if (senhaValue !== '' || confirmarValue !== '' || senhaAtualValue !== '') {

            // Verificar se preencheu todos os campos necessários
            if (senhaAtualValue === '' || senhaValue === '' || confirmarValue === '') {
                e.preventDefault();
                alert('Para alterar a senha, você precisa preencher:\n- Senha atual\n- Nova senha\n- Confirmação da nova senha');

                // Focar no primeiro campo vazio
                if (senhaAtualValue === '') {
                    senhaAtual.focus();
                } else if (senhaValue === '') {
                    senha.focus();
                } else {
                    confirmarSenha.focus();
                }
                return false;
            }

            // Validar se a nova senha tem o mínimo de caracteres
            if (senhaValue.length < 6) {
                e.preventDefault();
                alert('A nova senha deve ter no mínimo 6 caracteres!');
                senha.focus();
                return false;
            }

            // Validar confirmação
            if (!validarSenhas()) {
                e.preventDefault();
                alert('As senhas não coincidem! Verifique e tente novamente.');
                confirmarSenha.focus();
                return false;
            }
        }

        return true;
    });
});