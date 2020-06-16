package br.com.projeto.testeclientefiel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.testeclientefiel.entity.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long>{

	List<Imagem> findByIdPacote(Long idPacote);
	
	void deleteByIdPacote(Long idPacote);
}
