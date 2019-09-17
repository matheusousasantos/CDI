package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;

@javax.faces.view.ViewScoped
@Named( value="pessoaBean" )
public class PessoaBean {
	
	@Inject
	private DaoGeneric<Pessoa> dao;
	
	@Inject
	private IDaoPessoa iDaoPessoa;
	
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	
	public String salvar() {
		pessoa = dao.merge(pessoa);
		
		this.carregarPessoas();
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	public String remover() {
		dao.deletePorId(pessoa);
		pessoa = new Pessoa();
		
		this.carregarPessoas();
		return "";
	}
	
	public String logar() {
		
		Pessoa p = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
				
		if(p != null) {//Achou o Usuário
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("pessoaLogado", p);
			
			return "primeiraPagina.jsf";
			
		} 
		
		return "index.jsf";
	}
	
	public boolean permiteAcesso(String acesso) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa p = (Pessoa) externalContext.getSessionMap().get("pessoaLogado");
		
		return p.getPerfilUser().equals(acesso);
		
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = dao.getListEntity(Pessoa.class);
	}
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
