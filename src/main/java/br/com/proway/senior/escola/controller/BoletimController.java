package br.com.proway.senior.escola.controller;

import java.util.ArrayList;

import br.com.proway.senior.escola.model.Boletim;
import br.com.proway.senior.escola.model.BoletimDao;
import br.com.proway.senior.escola.model.Prova;
import br.com.proway.senior.escola.persistence.ArrayListPersistenceBoletim;

public class BoletimController {
	
	private Boletim boletim;
	private BoletimDao boletimDao;
	private ArrayListPersistenceBoletim dbBoletim = new ArrayListPersistenceBoletim();

	/**
	 * Classe de controle do boletim
	 * 
	 * Recebe um boletim e verifica se o boletim já existe na persistÃªncia.
	 * Se nao existir, cria um novo.
	 * 
	 * @param boletimEntrada
	 */
	public BoletimController(Boletim boletimEntrada) {
		this.boletimDao = new BoletimDao(dbBoletim);
		this.boletim = boletimEntrada;
	}
	
	/**
	 * Metodo para adicionar boletim
	 * 
	 * Adiciona um {@link Boletim} ao banco de dados.
	 * 
	 * @return Boletim
	 */
	public Boletim addBoletim() {
		if(this.boletim.getId() == null) {
			return this.boletimDao.add(this.boletim);	
		}
		return this.boletim;
	}
	
	/**
	 * Adiciona uma avaliacao ao boletim.
	 * 
	 * Ao adicionar a {@link Prova} a media sera recalculada.
	 * 
	 * @param prova
	 */
	public void addProva(Prova prova) {
		ProvaController provaController = new ProvaController(prova);
		if(prova.getId() != null) {
			provaController.addProva();	
		}else {
			provaController.get(prova.getId());	
		}
		boletim.getProvas().add(prova);
		this.calcularMedia();
	}

	/**
	 * Remove uma avaliacao do boletim.
	 * 
	 * Ao remover a Prova, a media sera recalculada.
	 * 
	 * @param indiceProva
	 */
	public void removeProva(int indiceProva) {
		ProvaController provaController = new ProvaController(indiceProva);
		provaController.removeProva();	
		try {
			boletim.getProvas().remove(indiceProva);
			this.calcularMedia();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Remove todas as provas contidas na lista.
	 * 
	 */
	public void removeTodasProvas() {
		ArrayList<Prova> provas = this.boletim.getProvas();
		
		for(Prova prova : provas) {
			ProvaController provaController = new ProvaController(prova);
			provaController.removeProva();	
		}
		this.boletim.getProvas().clear();
		this.calcularMedia();
	}
	
	/**
	 * Calcula a media ponderada das provas.
	 * 
	 * Realiza o calculo da media das notas das provas e atualiza o valor da
	 * media.
	 */
	private void calcularMedia() {
		Double total = 0.0;
		int pesos = 0;
		for(Prova prova : boletim.getProvas()) {
			total += prova.getNota() * prova.getPeso();
			pesos += prova.getPeso();
		}
		boletim.setMedia(total / pesos); 
	}

	public Boletim getBoletim() {
		return this.boletim;
	}
	
}
