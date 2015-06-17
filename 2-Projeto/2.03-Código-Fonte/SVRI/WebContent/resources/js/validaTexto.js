/**
 * funcao JS que valida a senha para cadastro de clientes e funcionarios
 * 
 * @returns {Boolean} - true se a senha esta no formato adequado, false, se nao esta
 */
function validaTexto(){
    var nome = document.getElementById('nome').value;
    var funcao = document.getElementById('funcao').value;
    var matricula = document.getElementById('matricula').value;

    var pattNome = new RegExp("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{2,200}$");
    
    var pattFuncao = new RegExp("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9 ]{2,30}$");
    
    var pattMatricula = new RegExp("^[0-9]{1,12}$");
    
    if(pattNome.test( nome ) == false) {
       alert('O nome deve conter apenas letras maiusculas e minusculas sem caracteres especiais.');
       return false;
    }	
    
    if(pattFuncao.test( funcao ) == false) {
        alert('A descricao da funcao deve conter apenas letras e numeros.');
        return false;
     }
    
    if(pattMatricula.test( matricula ) == false) {
        alert('A matricula deve ser expressa em numeros. Sem caracteres especiais');
        return false;
     }
    
    return true;     
 }