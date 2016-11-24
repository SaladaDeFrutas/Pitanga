/**
 * @returns {Boolean} true, se a data esta no formato dd/mm/yyyy valido
 * 
 */
function validaDataHora(){
	var data = document.getElementById('data').value
    var validformat = new RegExp("^([0-9]{2})\/([0-9]{2})\/([0-9]{4}) ([0-9]{2}):([0-9]{2})$") //checagem do fomato
    var returnval=false
    if (validformat.test(data) == false){
        alert("Por favor, informe a data e hora adequadamente no formato"
			+ " dd/MM/yyyy HH:mm.")
		return returnval
	}else{ //checagem pela adequacao da data
    	var match = validformat.exec(data)
    	var horas;
    	var minutos;
    	var monthfield;
        var dayfield;
        var yearfield;
    	
    	if(match != null) {
    		horas = match[4]
    		minutos = match[5]
    		dayfield = match[1]
    		monthfield = match[2]
    		yearfield = match[3]
    	}
    	
    	horas = parseInt(horas)
    	minutos = parseInt(minutos)

        var dayobj = new Date(yearfield, monthfield-1, dayfield)
    	
        if ((dayobj.getMonth()+1!=monthfield)||
        	(dayobj.getDate()!=dayfield)||
        	(dayobj.getFullYear()!=yearfield)||
        	(validaHora(horas,minutos)) == false)
            alert("Por favor, informe a data e hora adequadamente no formato"
			+ " dd/MM/yyyy HH:mm (dia, mes e ano corretos, depois as horas e os minutos).")
        else
            returnval=true
    }
    if (returnval==false)
        return returnval
}

/**
 * 
 * @param horas horario de 0 a 23 horas passado
 * @param minutos minutos de 00 a 59 passado
 * @returns {Boolean} true, se a hora esta adequada e coerente e false se nao estiver
 */
function validaHora(horas, minutos) {
	if(horas > 23 || horas < 0)
		return false
	
	if(minutos > 59 || minutos < 0)
		return false
		
	return true
}