<%@ attribute name="id" required="true" %>
<%@ attribute name="value" %>
<%@ attribute name="name" %>
<input id="${id}" name="${id}" value="${value}" name="${name}">
<script type="text/javascript">
    $("#${id}").datepicker({
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        yearRange: "-100:+0",
        dateFormat: 'dd/MM/yyyy HH:mm'
    });
</script>
