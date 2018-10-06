package br.edu.fapi.poo.servlets.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.edu.fapi.poo.servlets.usuario.Usuario;

@WebFilter(urlPatterns = "/*")
public class AuditFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		System.out.println("Usu�rio " + checkUser(req) + " est� acessando a URI: " + uri);

		chain.doFilter(request, response);
	}

	private String checkUser(HttpServletRequest req) {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario.logado");
		if (usuario == null) {
			return "<deslogado>";
		}
		return usuario.getEmail();
	}

}
