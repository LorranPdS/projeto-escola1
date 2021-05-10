package br.com.proway.senior.escola.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import br.com.proway.senior.escola.model.Aluno;
import br.com.proway.senior.escola.model.Materia;
import br.com.proway.senior.escola.model.Prova;

public class ProvaControllerTest {

	static ProvaController provaController;

	@Before
	public void setUpBeforeClass() {
		Prova prova = new Prova(202105, new Aluno(), new Materia());
		provaController = new ProvaController(prova);
	}

	@Test
	public void testProvaController() {
		assertNotNull(provaController);
	}
	
	@Test
	public void testAdicionarNotaProva() throws Exception {
		provaController.get(1).setNota(7.8);
//		provaController.get(1).g
	}
	
	

}










