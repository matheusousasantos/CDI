package br.com.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.entidades.Pessoa;
import br.com.jppautil.JPAUtil;

@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter {
	
	@Inject
	private JPAUtil jPAUtil; 

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Pessoa pessoaLogada = (Pessoa) session.getAttribute("pessoaLogado");
		
		String url = req.getServletPath();
		
//		Quando eu quero acessar uma página de dentro do sistema( que não seja o "index" )
//		porém a pessoa não esta logada.
		if(!url.equalsIgnoreCase("index.jsf") && pessoaLogada == null) {
			
			RequestDispatcher view = request.getRequestDispatcher("/index.jsf");
			view.forward(request, response);
			return;
			
		} else {
		
			chain.doFilter(request, response);
		
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		jPAUtil.getEntityManager();
	}

}
