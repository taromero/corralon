   
   function validateLogin(){
	   var numeric = true;
	   $('.checkNumeric').each(function() {
		   	   if(!isNumeric($(this).val())){
		   		  isNumeric = false;
		   		  $('#errorLogin').html("Ingrese un valor numerico por favor");
		   	   }
		   });
	   
	   return isNumeric;
   }
   
   function validatePresupuesto(){
	   return validateFields('tablaInputsPresupuesto', 'errorPresupuesto');
   }
   
   function validateOrdenDeCompra(){
	   return validateFields('tablaInputsOC', 'errorOC');
   }
   
   function validateBusqueda(){
	   return validateFieldsAlMenosUnoLleno('tablaInputsBusqueda', 'errorBusqueda');
   }
   
   function validateFields(idTabla, idLabelError){
	   var todoOk = true;
	   $('#' + idTabla + ' input.notEmpty').each(function() {
	   	   if(isEmpty($(this).val())){
	   		  todoOk = false;
	   		  $('#' + idLabelError).html("Complete todos los campos por favor");
	   		  $('#' + idLabelError + 'Div').show();
	   		  return false;
	   	   }
	   });
	   $('#' + idTabla + ' input.checkNumeric').each(function() {
		   if(isEmpty($(this).val())){
	   		  todoOk = false;
	   		  $('#' + idLabelError).html("Complete todos los campos por favor");
	   		  $('#' + idLabelError + 'Div').show();
	   		  return false;
		   }else if(!isNumeric($(this).val())){
	   		  todoOk = false;
	   		  $('#' + idLabelError).html("Por favor ingrese un valor numerico para las cantidades");
	   	      $('#' + idLabelError + 'Div').show();
	   		  return false;
	   	   }else if($(this).val()<1){
		   		  todoOk = false;
		   		  $('#' + idLabelError).html("Por favor ingrese una cantidad mayor a cero");
		   	      $('#' + idLabelError + 'Div').show();
		   		  return false;
		   	   }
	   });
	   
	   if(todoOk) $('#' + idLabelError + 'Div').hide();
	   
	   return (todoOk);
   }
   
   /**
    * Verifica que al menos uno de los campos este lleno
    * @param idTabla
    * @param idLabelError
    * @returns {Boolean}
    */
   function validateFieldsAlMenosUnoLleno(idTabla, idLabelError){
	   var todosLosCamposVacios = true;
	   var numerico = true;
	   $('#' + idTabla + ' input.notEmpty').each(function() {
	   	   if(!isEmpty($(this).val())){
	   		  $('#' + idLabelError + 'Div').hide();
	   		  todosLosCamposVacios = false;
	   	   }
	   });
	   $('#' + idTabla + ' input.checkNumeric').each(function() {
		   if(!isEmpty($(this).val())){
			  $('#' + idLabelError + 'Div').hide();
		   	  todosLosCamposVacios = false;
			  if(!isNumeric($(this).val())){
				  numerico = false;
			  }
		   }
	   });
	   
	   if(!numerico){
		   $('#' + idLabelError).html("Por favor ingrese un valor numerico para las cantidades");
	   	   $('#' + idLabelError + 'Div').show();
	   }else if(todosLosCamposVacios){
		   $('#' + idLabelError).html("Complete todos los campos por favor");
	   	   $('#' + idLabelError + 'Div').show();
	   }
	   return numerico && !todosLosCamposVacios;
   }

   function isNumeric(strString)
   //  check for valid numeric strings	
   {
   var strValidChars = "0123456789.-";
   var strChar;
   var blnResult = true;

   if (isEmpty(strString)) return false;

   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
         blnResult = false;
         }
      }
   return blnResult;
   }
   
   function isEmpty(strString){
	   return (strString.length == 0);
   }