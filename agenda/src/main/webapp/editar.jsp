<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="icon" href="img/favicon.png">
<title>Agenda de Contatos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<h1>Editar contato</h1>
		<form name="frmContato" action="update">
			<table>
				<tr>
					<td><input class="form-control" type="text" name="id"
						value="<% out.print(request.getAttribute("id")); %>"></td>
				</tr>
				<tr>
					<td><input class="form-control" type="text" name="nome"
						value="<% out.print(request.getAttribute("nome")); %>"></td>
				</tr>
				<tr>
					<td><input class="form-control" type="text" name="fone"
						value="<% out.print(request.getAttribute("fone")); %>"></td>
				</tr>
				<tr>
					<td><input class="form-control" type="email" name="email"
						value="<% out.print(request.getAttribute("email")); %>"></td>
				</tr>
			</table>
			<input class="btn-primary" style="margin-top: 15px;" type="button"
				value="Atualizar" onclick="validar()">
		</form>
	</div>

	<script src="scripts/validador.js"></script>
</body>
</html>