/**
 * Validação de formulário
 */

 function validar() {
	 let nome = frmContato.nome.value;
	 let fone = frmContato.fone.value;
	 let email = frmContato.email.value;
	 
	 if(isEmpty(nome)) {
		 alert(campoObrigatorio('Nome'));
		 frmContato.nome.focus;
		 return false;
	 } else if(isEmpty(fone)) {
		 alert(campoObrigatorio('Telefone'));
		 frmContato.nome.focus;
		 return false;
	 } else if(isEmpty(email)) {
		 alert(campoObrigatorio('E-mail'));
		 frmContato.nome.focus;
		 return false;
	 } else {
		 document.forms['frmContato'].submit();
	 }
	 

 }
 
 function campoObrigatorio(campo) {
	 return `Preencha o campo ${campo}`;
 }
 
 function isEmpty(str) {
	return str === '' ? true : false;
 }