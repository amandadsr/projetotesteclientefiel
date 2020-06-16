package br.com.projeto.testeclientefiel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.testeclientefiel.entity.Pacote;
import br.com.projeto.testeclientefiel.repository.PacoteRespository;

@Service
public class PacoteService {

	@Autowired
	private PacoteRespository pacoteRespository;
	
	public void salvaPacote(Pacote pacote) {
		
		pacoteRespository.save(pacote);
	}
	
	public List<Pacote> buscaTodos(){
		
		return pacoteRespository.findAll();
	}
	
	public void deletaPorId(Long id) {
		
		pacoteRespository.deleteById(id);
	}

	public Optional<Pacote> buscaPorId(Long id){
		
		return pacoteRespository.findById(id);
	}
}
