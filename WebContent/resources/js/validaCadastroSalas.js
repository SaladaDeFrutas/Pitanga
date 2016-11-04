/**
 * funcao que valida os campos de cadastro de tipo ingresso
 * 
 * o nome do tipo ingresso nao pode conter espaços 
 * 
 * @returns
 */
function validaCadastroSalas(){
    var qntFileiras = document.getElementById('qntFileiras').value
    var qntColunas = document.getElementById('qntColunas').value
   
    var pattQnt = new RegExp("^[0-9]+$")
    
    if(pattQnt.test( qntFileiras ) == false) {
       alert('A quantidade de fileiras deve ser um numero inteiro.')
       return false
    }	
    
    if(pattQnt.test( qntColunas ) == false) {
        alert('A quantidade de colunas deve ser um numero inteiro.')
        return false
    }
    
    if(parseInt(qntFileiras) >26 || parseInt(qntColunas) > 100 ){
    	alert('Dimensoes muito grandes. Maximo de 26 fileiras e 100 colunas para formatar corretamente.')
    	return false
    }
    	
    return true
 }