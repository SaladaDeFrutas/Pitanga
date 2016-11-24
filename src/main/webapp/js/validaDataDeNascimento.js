/**
 * @returns {Boolean} true, se a data esta no formato dd/mm/yyyy valido
 * 
 */
function validaDataDeNascimento(){
	var data = document.getElementById('dataDeNascimento').value
    var validformat = new RegExp("^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$") //checagem do fomato
    var returnval=false
    if (validformat.test(data) == false){
        alert("Por favor, informe a data de nascimento adequadamente no formato"
			+ " dd/MM/yyyy.")
		return returnval
    } else{ //checagem pela adequacao da data
        var monthfield = data.split("/")[1]
        var dayfield = data.split("/")[0]
        var yearfield = data.split("/")[2]
        var dayobj = new Date(yearfield, monthfield-1, dayfield)
        if ((dayobj.getMonth()+1!=monthfield)||
        	(dayobj.getDate()!=dayfield)||
        	(dayobj.getFullYear()!=yearfield)||
        	(validaIdade(dayobj)) == false)
            alert("Por favor, informe a data de nascimento adequadamente no formato"
			+ " dd/MM/yyyy (dia, mes e ano corretos). Sera permitida apenas idade minima de 13 anos e maxima"
			+ " de 130 anos.")
        else
            returnval=true
    }
    if (returnval==false)
        return returnval
}

/**
 * 
 * @param dataDeNascimento do usuario
 * @returns {Boolean} true, se a idade for maior que 13 e menor que 130 e false e for menor que
 * 13 ou maior que 130 anos
 */
function validaIdade(dataDeNascimento) {
	var minIdade = 13
	var maxIdade = 130
	var dataChecagemMin = new Date( (dataDeNascimento.getFullYear() + minIdade), dataDeNascimento.getMonth(),
									dataDeNascimento.getDate() )
	var today = new Date;
	
	if(today.getTime() - dataChecagemMin.getTime() < 0)
		return false
	else{
		var dataChecagemMax = new Date( (dataDeNascimento.getFullYear() + maxIdade), dataDeNascimento.getMonth(),
				dataDeNascimento.getDate() )
		if(today.getTime() - dataChecagemMax.getTime() < 0)
			return true
		else
			return false
	}
	
}