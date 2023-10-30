<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.Contato"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.List"%>
<%
List<Contato> contatos = (List<Contato>) request.getAttribute("contatos");
%>
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
		<div>
			<h1>Agenda de contatos</h1>
			<a class="btn-primary" href="novo.html">Novo contato</a>
		</div>

		<div>
			<table class="table-bordered table-hover">
				<thead>
					<tr>
						<td>ID</td>
						<td>Nome</td>
						<td>Telefone</td>
						<td>E-mail</td>
						<td>Opções</td>
					</tr>
				</thead>
				<tbody>
					<%
					for (Contato contato : contatos) {
					%>
					<tr>
						<td><%=contato.getId()%></td>
						<td><%=contato.getNome()%></td>
						<td><%=contato.getFone()%></td>
						<td><%=contato.getEmail()%></td>
						<td>
							<a 
								href="select?id=<%= contato.getId()%>" 
								class="btn-primary"
							>
								Editar
							</a>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>


			</table>
		</div>

	</div>


</body>
</html>