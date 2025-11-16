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

