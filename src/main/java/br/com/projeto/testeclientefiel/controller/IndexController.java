package br.com.projeto.testeclientefiel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/cadastro/frases")
	public String frases() {
		return "cadastro/frases";
	}
}
