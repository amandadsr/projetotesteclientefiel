package br.com.projeto.testeclientefiel.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.testeclientefiel.entity.Frase;
import br.com.projeto.testeclientefiel.service.FraseService;

@Controller
public class FraseController {

	@Autowired
	private FraseService fraseService;
	
	
	@GetMapping("/cadastro/frases")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/frases");
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("fraseobj", new Frase());
		
		return modelAndView;
	}
	
	@PostMapping("**/salvafrase")
	public ModelAndView salvaFrase(Frase frase) {
		
				
		fraseService.salvaFrase(frase);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/frases");
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("fraseobj", new Frase());
		return modelAndView;
	}
	
	@GetMapping("**/listafrases")
	public ModelAndView listaFrases() {
		ModelAndView modelAndView = new ModelAndView("cadastro/frases");
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("fraseobj", new Frase());
		return modelAndView;
	}
	
	@GetMapping("**/editarfrase/{id}")
	public ModelAndView editaFrase(@PathVariable("id") Long id) {
		
		Optional<Frase> frase = fraseService.pesquisaPorId(id);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/frases");
		modelAndView.addObject("fraseobj", frase.get());
		return modelAndView;
	}
	
	@GetMapping("**/removerfrase/{id}")
	public ModelAndView removeFrase(@PathVariable("id") Long id) {
		
		fraseService.deletaPorId(id);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/frases");
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("fraseobj", new Frase());
		return modelAndView;
	}
	
	
}
