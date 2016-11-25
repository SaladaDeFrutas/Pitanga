/**
 * funcao que valida se o usuario digitou o email e a senha para logar
 * @returns {Boolean} true se os campos estao preenchidos e
 * false se nao estao preenchidos
 */
function validaLogin() {
    var email = document.getElementById('email').value;
    var senha = document.getElementById('senha').value;

    if (email == "") {
        alert('Digite o email.');
        return false;
    }

    if (senha == "") {
        alert('Digite a senha.');
        return false;
    }

    return true;
}
