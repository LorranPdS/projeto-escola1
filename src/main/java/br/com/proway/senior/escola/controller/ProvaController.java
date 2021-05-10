package br.com.proway.senior.escola.controller;

import br.com.proway.senior.escola.model.Prova;
import br.com.proway.senior.escola.model.ProvaDao;
import br.com.proway.senior.escola.persistence.ArrayListPersistenceProva;

public class ProvaController {
	
	private Prova prova;
	private ProvaDao provaDao;
	private ArrayListPersistenceProva dbProvas = new ArrayListPersistenceProva();
	
	/**
	 * Classe de controle da Prova
	 * 
	 * Recebe uma prova e verifica se a prova já existe na persistência.
	 * Se nao existir, cria um novo.
	 * 
	 * @param provaEntrada
	 */
	public ProvaController(Prova provaEntrada) {
		this.provaDao = new ProvaDao(dbProvas);		
		this.prova = provaEntrada;
	}
	
	public ProvaController(int index) {
		this.provaDao = new ProvaDao(dbProvas);
		this.prova = provaDao.get(index);
	}
	
	/**
	 * Adiciona uma avaliação 
	 * 
	 * @param prova
	 */
	public Prova addProva() {
		if(this.prova.getId() != null) {
			return this.provaDao.add(this.prova);	
		}
		return this.prova;
	}

	/**
	 * Remove uma avaliacao do boletim.
	 * 
	 * Ao remover a {@link Prova} a media sera recalculada.
	 */
	public void removeProva() {
		try{
			provaDao.remove(prova.getId());
		}catch(Exception e) {
			System.out.println("Prova nao removida. Erro:" + e.getMessage());
		}
	}

	public Prova get(Integer id) {
		return this.provaDao.get(id);		
	}
}

