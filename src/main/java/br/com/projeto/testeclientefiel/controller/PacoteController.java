package br.com.projeto.testeclientefiel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.testeclientefiel.entity.Pacote;
import br.com.projeto.testeclientefiel.service.ImagemService;
import br.com.projeto.testeclientefiel.service.PacoteService;

@Controller
public class PacoteController {
	
	@Autowired
	private PacoteService pacoteService;

	@Autowired
	private ImagemService imagemService;
	
	@GetMapping("/cadastro/pacotesdeimagens")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/pacotesdeimagens");
		modelAndView.addObject("pacotes", pacoteService.buscaTodos());
		modelAndView.addObject("pacoteobj", new Pacote());
		
		return modelAndView;
	}
	
	@PostMapping("**/salvapacote")
	public ModelAndView salvaPacote(Pacote pacote) {
		
		pacoteService.salvaPacote(pacote);
		ModelAndView modelAndView = new ModelAndView("cadastro/pacotesdeimagens");
		modelAndView.addObject("pacotes", pacoteService.buscaTodos());
		modelAndView.addObject("pacoteobj", new Pacote());
		
		return modelAndView;
	}
	
	@GetMapping("**/removepacote/{id}")
	public ModelAndView removePacote(@PathVariable("id") Long id) {
		
		
		pacoteService.deletaPorId(id);
		imagemService.deletaPorIdPacote(id);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/pacotesdeimagens");
		modelAndView.addObject("pacotes", pacoteService.buscaTodos());
		modelAndView.addObject("pacoteobj", new Pacote());
		
		return modelAndView;
	}
	
	@GetMapping("**/alterapacote/{id}")
	public ModelAndView alteraPacote(@PathVariable("id") Long id) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/gerenciarpacotedeimagens");
		modelAndView.addObject("pacoteobj", pacoteService.buscaPorId(id));
		modelAndView.addObject("imagens", imagemService.listaImagensPorPacote(id));
		
		return modelAndView;
	}
}
