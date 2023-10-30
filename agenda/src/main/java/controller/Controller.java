package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contato;
import model.DAO;

@WebServlet(urlPatterns= {
		"/Controller", 
		"/main", 
		"/insert", 
		"/select",
		"/update"
		})
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAO dao = new DAO();
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		if(action.equals("/main")) {
			contatos(request, response);
		} else if(action.equals("/insert")) {
			novoContato(request, response);
		} else if(action.equals("/select")) {
			listarContato(request, response);
		} else if(action.equals("/update")) {
			atualizarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	private void contatos(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Contato> contatos = dao.contatos();
		
		request.setAttribute("contatos", contatos);
		
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
	}

	private void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		
		Contato contato = new Contato(); 
		
		if(Optional.ofNullable(nome).isPresent()
		   && Optional.ofNullable(fone).isPresent()
		   && Optional.ofNullable(email).isPresent()) {
			
			contato.setNome(nome);
			contato.setFone(fone);
			contato.setEmail(email);
			
			dao.insert(contato);
			
			response.sendRedirect("main");
		}
		
	}
	
	private void listarContato(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Contato contato = new Contato();
		contato.setId(Long.parseLong(id));
		
		dao.getById(contato);
		
		request.setAttribute("id", 	  contato.getId());
		request.setAttribute("nome",  contato.getNome());
		request.setAttribute("fone",  contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		
		rd.forward(request, response);
	}
	
	private void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Contato contato = new Contato();
		
		contato.setId(Long.parseLong(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.update(contato);
		
		response.sendRedirect("main");
	}

}
