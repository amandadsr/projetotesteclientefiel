package br.com.projeto.testeclientefiel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.testeclientefiel.entity.Imagem;
import br.com.projeto.testeclientefiel.repository.ImagemRepository;

@Service
public class ImagemService {

	
	@Autowired
	private ImagemRepository imagemRepository;
	
	public void salvaImagem(Imagem imagem) {
		
		imagemRepository.save(imagem);
	}
	
	public List<Imagem> listaTodos(){
		
		return imagemRepository.findAll();
	}
	
	public List<Imagem> listaImagensPorPacote(Long idPacote){
		
		return imagemRepository.findByIdPacote(idPacote);
	}
	
	public void deletaPorIdPacote(Long idPacote) {
		
		imagemRepository.deleteAll(listaImagensPorPacote(idPacote));;
	}
	
	public Optional<Imagem> pesquisaPorId(Long id){
		
		return imagemRepository.findById(id);
	}
}
