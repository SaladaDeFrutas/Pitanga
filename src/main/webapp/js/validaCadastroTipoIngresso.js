/**
 * funcao que valida os campos de cadastro de tipo ingresso
 * 
 * o nome do tipo ingresso nao pode conter espaços 
 * 
 * @returns
 */
function validaCadastroTipoIngresso(){
    var nome = document.getElementById('nome').value;
    var preco = document.getElementById('preco').value;
   
    var pattNome = new RegExp("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\-]{2,200}$");
    
    var pattPreco = new RegExp("^[0-9]+\.?[0-9]$");
    
    
    if(pattNome.test( nome ) == false) {
       alert('O nome deve conter apenas letras maiusculas e minusculas sem espaco.');
       return false;
    }	
    
    if(pattPreco.test( preco ) == false) {
        alert('O preco deve estar no formato adequado. Caso seja um numero com virgula,' +
        		' represente a virgula usando ponto e digite apenas um digito apos o ponto.' +
        		' Ex: "22.2".');
        return false;
    }
    
    return true;     
 }