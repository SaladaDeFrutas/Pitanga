/**
 * funcao JS que valida a senha para cadastro de filmes
 * 
 * @returns {Boolean} - true se os campos conferem e false se nao conferem 
 */
function validaCadastroFilme(){
    var titulo = document.getElementById('titulo').value
    var idioma = document.getElementById('idioma').value
    var modoDeExibicao = document.getElementById('modoDeExibicao').value
    var produtora = document.getElementById('produtora').value
    var data = document.getElementById('dataEstreia').value
    var duracao = document.getElementById('duracao').value
    
    var pattLetrasNumeros = new RegExp("^[\-A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9 ]{2,60}$")
    
    var pattLetras = new RegExp("^[\-A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{2,60}$")
    
    var pattDuracao = new RegExp("^[0-9]+$")
    
    if(pattLetrasNumeros.test( titulo ) == false) {
       alert("O titulo deve conter apenas letras maiusculas, minusculas, hifen e numeros, sem caracteres especiais.");
       return false;
    }	
    
    if(pattLetras.test( idioma) == false) {
        alert('A descricao do idioma deve conter apenas letras maiusculas e minusculas e hifen.');
        return false;
     }
    
    if(pattLetrasNumeros.test( modoDeExibicao ) == false) {
        alert('O modo de exibicao deve conter apenas letras maiusculas, minusculas, hifen e numeros, sem caracteres especiais.');
        return false;
     }
    
    if(pattLetrasNumeros.test( produtora ) == false) {
        alert('A descricao da produtora deve conter apenas letras maiusculas, minusculas, hifen e numeros, sem caracteres especiais.');
        return false;
     }
    
    if(pattDuracao.test( duracao ) == false){
    	alert('Digite a duracao usando numeros e em minutos.')
    	return false
    }
    
    return validaData(data);
 }

function validaData(data){
	
    var validformat = new RegExp("^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$") //checagem do fomato
    var returnval=false
    if (validformat.test(data) == false){
        alert("Por favor, informe a data adequadamente no formato"
			+ " dd/MM/yyyy.")
		return returnval
    } else{ //checagem pela adequacao da data
        var monthfield = data.split("/")[1]
        var dayfield = data.split("/")[0]
        var yearfield = data.split("/")[2]
        var dayobj = new Date(yearfield, monthfield-1, dayfield)
        if ((dayobj.getMonth()+1!=monthfield)||
        	(dayobj.getDate()!=dayfield)||
        	(dayobj.getFullYear()!=yearfield))
            alert("Por favor, informe a data adequadamente no formato"
			+ " dd/MM/yyyy (dia, mes e ano corretos).")
        else
            returnval=true
    }
    if (returnval==false)
        return returnval
}