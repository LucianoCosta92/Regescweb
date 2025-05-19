package br.com.luciano.regescweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.luciano.regescweb.dto.ProfessorDTO;
import br.com.luciano.regescweb.models.Professor;
import br.com.luciano.regescweb.models.StatusProfessor;
import br.com.luciano.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;

	@GetMapping("/professores")
	public ModelAndView index() {
		List<Professor> professores = this.professorRepository.findAll();
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}
	
	@GetMapping("/professores/new")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("statusProfessor", StatusProfessor.values());
		return mv;
	}
	
	@PostMapping("/professores")
	public String create(@Valid ProfessorDTO request, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("ERRO:");
			return "redirect:/professores/new";
		} else {
			Professor professor = request.toProfessor();
			this.professorRepository.save(professor);
			return "redirect:/professores";
		}
	}
	
	
}
