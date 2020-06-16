package br.com.projeto.testeclientefiel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.testeclientefiel.entity.Frase;
import br.com.projeto.testeclientefiel.repository.FraseRepository;

@Service
public class FraseService {

	@Autowired
	private FraseRepository fraseRepository;
	
	public Optional<Frase> pesquisaPorId(Long id){
		
		return fraseRepository.findById(id);
	}
	
	public void deletaPorId(Long id){
		
		fraseRepository.deleteById(id);
	}
	
	public List<Frase> buscaTodos(){
		
		return fraseRepository.findAll();
	}
	
	public void salvaFrase(Frase frase) {
		
		fraseRepository.save(frase);
	}
	
}
