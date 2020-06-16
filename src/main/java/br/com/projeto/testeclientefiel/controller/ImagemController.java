package br.com.projeto.testeclientefiel.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.testeclientefiel.entity.Imagem;
import br.com.projeto.testeclientefiel.entity.Pacote;
import br.com.projeto.testeclientefiel.service.FraseService;
import br.com.projeto.testeclientefiel.service.ImagemService;
import br.com.projeto.testeclientefiel.service.PacoteService;

@Controller
public class ImagemController {
	 
	@Autowired
	private ImagemService imagemService;
	
	@Autowired
	private PacoteService pacoteService;
	
	@Autowired
	private FraseService fraseService;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@PostMapping("**/adicionaimagem")
	public ModelAndView imagem(Pacote pacote) {
		ModelAndView modelAndView = new ModelAndView("cadastro/imagem");
		modelAndView.addObject("imagemobj", new Imagem());
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("pacoteobj", pacoteService.buscaPorId(pacote.getIdPacote()));
		return modelAndView;
	}
	
	
	@PostMapping(value = "**/salvaimagem", consumes = {"multipart/form-data"})
	public ModelAndView salvaImagem(Imagem imagem, @RequestParam Long idPacote, final MultipartFile[] file) throws IOException {
		
		imagem.setIdPacote(idPacote);
		
		for (MultipartFile multipartFile : file) {
			
		
			if(imagem.getId() != null && imagem.getId() > 0) { //editando
				
				Imagem imagemTemp = imagemService.pesquisaPorId(imagem.getId()).get();
				
				imagem.setFoto(imagemTemp.getFoto());
				imagemService.salvaImagem(imagem);
				
			}else {
				Imagem novo = new Imagem();
				
				novo.setFonte(imagem.getFonte());
				novo.setFoto(multipartFile.getBytes());
				novo.setFrase(imagem.getFrase());
				novo.setIdPacote(idPacote);
				
				imagemService.salvaImagem(novo);
			}
			
			
		}
		
		ModelAndView modelAndView = new ModelAndView("cadastro/gerenciarpacotedeimagens");
		modelAndView.addObject("imagens", imagemService.listaImagensPorPacote(idPacote));
		modelAndView.addObject("pacoteobj", pacoteService.buscaPorId(imagem.getIdPacote()));
		
		return modelAndView;
	}
	
	
	@GetMapping("**/editarimagem/{id}")
	public ModelAndView editarImagem(@PathVariable("id") Long id) {
		
		
		
		ModelAndView modelAndView = new ModelAndView("cadastro/imagem");
		modelAndView.addObject("frases", fraseService.buscaTodos());
		modelAndView.addObject("imagemobj", imagemService.pesquisaPorId(id));
		modelAndView.addObject("pacoteobj", pacoteService.buscaPorId(imagemService.pesquisaPorId(id).get().getIdPacote()));
		
		return modelAndView;
	}
	
	 /*@RequestMapping(value = "/exportar/{id}", method = RequestMethod.GET)
	    public HttpEntity<byte[]> download(@PathVariable("id")Long id) throws IOException {

	    	
	       // byte[] arquivo = Files.readAllBytes(Paths.get("/home/wolmir/minha-imagem.jpg") );
	    	
	    	byte[] arquivo = imagemService.pesquisaPorId(id).get().getFoto();
	    	
	        HttpHeaders httpHeaders = new HttpHeaders();

	        httpHeaders.add("Content-Disposition", "attachment;filename=\"minha-imagem.jpg\"");

	        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

	        return entity;
	    }*/
	    
		@GetMapping("**/exportarimagem/{id}") //fazer o download em pdf da lista de pessoas
		public void imprimePdf(@PathVariable("id") Long id, 
				HttpServletRequest request, HttpServletResponse response) throws Exception{
		
			List<Imagem> imagens = new ArrayList<Imagem>();
			imagens.add(imagemService.pesquisaPorId(id).get());
			//List<Imagem> arquivo = new ArrayList<Imagem>();
			//arquivo.add(imagemService.pesquisaPorId(id).get());
						
			//chamar o serviço que faz a geração do relatório
			byte[] pdf = reportUtil.gerarRelatorio(imagens, "imagem", request.getServletContext());
			
			//tamanho da resposta para o navegador
			response.setContentLength(pdf.length);

			//definir na resposta o tipo de arquivo
			response.setContentType("application/octet-stream");
			
			//definir o cabeçalho da resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment: filename=\"%s\"", "relatorio.pdf");
			response.setHeader(headerKey, headerValue);
			
			//finaliza a resposta para o navegador
			response.getOutputStream().write(pdf);
			
		}
}
