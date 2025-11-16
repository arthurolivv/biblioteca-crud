//Selects2
$(document).ready(function () {
    $('#autoresSelect').select2({
        placeholder: "Selecione autores",
        allowClear: true,
        width: '100%'
    });

    $('#categoriasSelect').select2({
        placeholder: "Selecione categorias",
        allowClear: true,
        width: '100%'
    });
});

//Pre visualizar imagem
function previewImagem() {
    const input = document.getElementById('imagemUrlInput');
    const preview = document.getElementById('imagemPreview');
    const url = input.value.trim();

    // Limpar preview anterior
    preview.innerHTML = '';

    if (url === '') {
        // Mostrar placeholder
        preview.innerHTML = `
            <svg width="80" height="80" fill="#dee2e6" viewBox="0 0 16 16">
                <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                <path d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
            </svg>
            <p class="text-muted mb-0 mt-2" style="font-size: 0.85rem;">Preview da capa</p>
        `;
        return;
    }

    // Criar elemento de loading
    preview.innerHTML = `
        <div class="spinner-border text-secondary" role="status">
            <span class="visually-hidden">Carregando...</span>
        </div>
        <p class="text-muted mb-0 mt-2" style="font-size: 0.85rem;">Carregando imagem...</p>
    `;

    // Criar nova imagem
    const img = new Image();

    img.onload = function() {
        preview.innerHTML = '';
        const imgElement = document.createElement('img');
        imgElement.src = url;
        imgElement.alt = 'Preview da capa';
        imgElement.style.maxWidth = '100%';
        imgElement.style.maxHeight = '200px';
        imgElement.style.objectFit = 'contain';
        imgElement.className = 'rounded';
        preview.appendChild(imgElement);
    };

    img.onerror = function() {
        preview.innerHTML = `
            <svg width="60" height="60" fill="#dc3545" viewBox="0 0 16 16">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <p class="text-danger mb-0 mt-2" style="font-size: 0.85rem;">Erro ao carregar</p>
        `;
    };

    img.src = url;
}

//pre carregar imagem no campo de imagem
document.addEventListener('DOMContentLoaded', function() {

    // Preview da imagem se já houver URL preenchida (após erro de validação ou na edição)
    const imagemInput = document.getElementById('imagemUrlInput');
    if (imagemInput && imagemInput.value.trim() !== '') {
        previewImagem();
    }

});

//Exemplares
function carregarExemplaresExistentes(exemplares) {
    const container = document.getElementById('exemplaresContainer');
    container.innerHTML = ''; // Limpar apenas uma vez no início

    exemplares.forEach((exemplar, i) => {
        adicionarCampoExemplar(i, exemplar);
    });
}

function adicionarCampoExemplar(index, dados = null) {
    const container = document.getElementById('exemplaresContainer');

    const exemplar = dados || { codigo_exemplar: '', proprio: '', status: '' };

    let proprioValue = '';
    if (exemplar.proprio !== null && exemplar.proprio !== undefined) {
        proprioValue = String(exemplar.proprio).toLowerCase();
    }

    let statusValue = '';
    if (exemplar.status) {
        // Se for objeto com propriedade 'name'
        if (typeof exemplar.status === 'object' && exemplar.status.name) {
            statusValue = exemplar.status.name;
        }
        else if (typeof exemplar.status === 'string') {
            statusValue = exemplar.status;
        }
    }

    console.log('Status normalizado:', statusValue); // Debug

    const exemplarDiv = document.createElement('div');
    exemplarDiv.className = 'card mb-3';
    exemplarDiv.setAttribute('data-exemplar-index', index);

    exemplarDiv.innerHTML = `
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h6 class="card-subtitle mb-0 text-muted">Exemplar ${index + 1}</h6>
                <button type="button" class="btn btn-sm btn-danger" onclick="removerExemplar(${index})">
                    Remover
                </button>
            </div>
            <div class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Código do Exemplar</label>
                    <input type="text" 
                           name="exemplares[${index}].codigo_exemplar" 
                           class="form-control" 
                           placeholder="Ex: EX-${String(index + 1).padStart(4, '0')}"
                           pattern="^EX-\\d+$"
                           value="${exemplar.codigo_exemplar || ''}" 
                           required>
                    <small class="text-muted">Formato: EX-0001, EX-0002, etc.</small>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Exclusivo para consulta local?</label>
                    <select name="exemplares[${index}].proprio" class="form-select" required>
                        <option value="" ${proprioValue === '' ? 'selected' : ''}>Selecione...</option>
                        <option value="true" ${proprioValue === 'true' ? 'selected' : ''}>Sim</option>
                        <option value="false" ${proprioValue === 'false' ? 'selected' : ''}>Não</option>
                    </select>
                </div>                
                <div class="col-md-4">
                    <label class="form-label">Status</label>
                    <select name="exemplares[${index}].status" class="form-select" required>
                        <option value="">Selecione...</option>             
                        <option value="DISPONIVEL" ${statusValue === 'DISPONIVEL' ? 'selected' : ''}>
                            Disponível
                        </option>
                        <option value="MANUTENCAO" ${statusValue === 'MANUTENCAO' ? 'selected' : ''}>
                            Em Manutenção
                        </option>
                        <option value="EMPRESTADO" ${statusValue === 'EMPRESTADO' ? 'selected' : ''}>
                            Emprestado
                        </option>
                    </select>
                </div>
            </div>
        </div>
    `;

    container.appendChild(exemplarDiv);
}

function gerarExemplares() {
    const container = document.getElementById('exemplaresContainer');
    const qtdInput = document.getElementById('qtdExemplaresInput');
    const qtdNovos = parseInt(qtdInput.value);

    if (isNaN(qtdNovos) || qtdNovos < 1) {
        alert('Por favor, digite uma quantidade válida');
        return;
    }

    const exemplaresExistentes = container.querySelectorAll('.card').length;

    for (let i = 0; i < qtdNovos; i++) {
        const novoIndex = exemplaresExistentes + i;
        adicionarCampoExemplar(novoIndex);
    }

    qtdInput.value = '';

    reindexarExemplares();
}

function removerExemplar(index) {
    const container = document.getElementById('exemplaresContainer');
    const exemplares = container.querySelectorAll('.card');

    if (exemplares.length <= 1) {
        alert('Deve haver pelo menos um exemplar!');
        return;
    }

    const exemplar = container.querySelector(`[data-exemplar-index="${index}"]`);
    if (exemplar) {
        exemplar.remove();
        reindexarExemplares();
    }
}

// Reindexar exemplares após adicionar/remover
function reindexarExemplares() {
    const container = document.getElementById('exemplaresContainer');
    const exemplares = container.querySelectorAll('.card');

    exemplares.forEach((exemplar, index) => {
        exemplar.setAttribute('data-exemplar-index', index);

        const titulo = exemplar.querySelector('.card-subtitle');
        if (titulo) {
            titulo.textContent = `Exemplar ${index + 1}`;
        }

        const codigoInput = exemplar.querySelector('input[name^="exemplares"]');
        const proprioSelect = exemplar.querySelectorAll('select[name^="exemplares"]')[0];
        const statusSelect = exemplar.querySelectorAll('select[name^="exemplares"]')[1];

        if (codigoInput) {
            const valorAtual = codigoInput.value;
            codigoInput.name = `exemplares[${index}].codigo_exemplar`;
            codigoInput.value = valorAtual;
            codigoInput.placeholder = `Ex: EX-${String(index + 1).padStart(4, '0')}`;
        }
        if (proprioSelect) {
            const valorAtual = proprioSelect.value;
            proprioSelect.name = `exemplares[${index}].proprio`;
            proprioSelect.value = valorAtual;
        }
        if (statusSelect) {
            const valorAtual = statusSelect.value;
            statusSelect.name = `exemplares[${index}].status`;
            statusSelect.value = valorAtual;
        }

        const btnRemover = exemplar.querySelector('.btn-danger');
        if (btnRemover) {
            btnRemover.setAttribute('onclick', `removerExemplar(${index})`);
        }
    });
}
