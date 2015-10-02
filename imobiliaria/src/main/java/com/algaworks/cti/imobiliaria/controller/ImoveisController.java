package com.algaworks.cti.imobiliaria.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cti.imobiliaria.model.Imovel;
import com.algaworks.cti.imobiliaria.model.TipoImovel;
import com.algaworks.cti.imobiliaria.repository.Imoveis;
import com.algaworks.cti.imobiliaria.repository.filter.ImovelFilter;

@Controller
@RequestMapping("/imoveis")
public class ImoveisController {
	
	@Autowired
	private Imoveis imoveis;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroImovel");
		mv.addObject(new Imovel());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView adicionar(@Validated Imovel imovel, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();
		if (errors.hasErrors()) {
			mv.setViewName("CadastroImovel");
			return mv;
		}
		
		imoveis.guardar(imovel);
		
		mv.setViewName("redirect:/imoveis/novo");
		attributes.addFlashAttribute("mensagem", "Imóvel cadastrado com sucesso!");
		return mv;
	}
	
	@ModelAttribute
	public List<TipoImovel> tiposImoveis() {
		return Arrays.asList(TipoImovel.values());
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("PesquisaImoveis");
		mv.addObject("filtro", new ImovelFilter());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView filtrar(@ModelAttribute("filtro") ImovelFilter filtro) {
		ModelAndView mv = new ModelAndView("PesquisaImoveis");
		
		List<Imovel> imoveisFiltrados = imoveis.filtrar(filtro);
		mv.addObject(imoveisFiltrados);
		
		return mv;
	}
	
}





