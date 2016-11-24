/**
 * funcao que valida se o usuario escolheu pelo menos um ingresso para comprar
 * @returns {Boolean} true se os campos estao preenchidos e
 * false se nao estao preenchidos
 */
function validaEscolherIngressos(qtdTiposIngresso) {
    var quantidadeIngressosEscolhidos = 0;
    for (i = 0; i < qtdTiposIngresso; i++) {
        quantidadeIngressosEscolhidos += document.getElementById('quantidadeIngresso'.concat(i)).value;
    }

    if (quantidadeIngressosEscolhidos == 0) {
        alert('Escolha pelo menos um ingresso.');
        return false;
    }

    return true;
}
