package br.com.projeto.testeclientefiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.testeclientefiel.entity.Frase;

@Repository
public interface FraseRepository extends JpaRepository<Frase, Long>{

}
