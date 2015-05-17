<%@ attribute name="id" required="true" %>
<input id= "${id}" name="${id}">
<script>
	$("#${id}").datepicker({
		changeMonth: true,
		changeYear:true,
		showButtonPanel:true,
		yearRange: "-100:+0",
		dateFormat: 'dd/mm/yyyyy'});
</script>