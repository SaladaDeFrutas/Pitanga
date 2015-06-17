/**
 * funcao JS que valida a senha para cadastro de clientes e funcionarios
 * 
 * @returns {Boolean} - true se a senha esta no formato adequado, false, se nao esta
 */
function validaSenha(){
    var senha = document.getElementById('senha').value;

    var patt = new RegExp("^[a-zA-Z0-9]{6,20}$");
    if(patt.test( senha ) == false) {
       alert('A senha deve conter pelo menos 6 e no maximo 20 caracteres. Nao deve conter nada alem de letras e numeros.');
       return false;
    }
    
    return true;     
 }
