package br.com.luciano.regescweb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.regescweb.dto.ProfessorDTO;
import br.com.luciano.regescweb.models.Professor;
import br.com.luciano.regescweb.models.StatusProfessor;
import br.com.luciano.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Professor> professores = this.professorRepository.findAll();
		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("professores", professores);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView formulario(ProfessorDTO request) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid ProfessorDTO request, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/new");
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		} else {
			Professor professor = request.toProfessor();
			this.professorRepository.save(professor);
			return new ModelAndView("redirect:/professores/" + professor.getId());
		}
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		if (optional.isPresent()) {
			Professor professor = optional.get();
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject("professor", professor);
			return mv;
		} else {
			return new ModelAndView("redirect:/professores");
		}
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, ProfessorDTO request) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		if (optional.isPresent()) {
			Professor professor = optional.get();
			request.fromProfessor(professor);

			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId", professor.getId());
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		} else {
			return new ModelAndView("redirect:/professores");
		}
	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid ProfessorDTO request, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId", id);
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		} else {
			Optional<Professor> optional = this.professorRepository.findById(id);
			if (optional.isPresent()) {
				Professor professor = request.toProfessor(optional.get());
				this.professorRepository.save(professor);
				
				return new ModelAndView("redirect:/professores/" + professor.getId());
			} else {
				return new ModelAndView("redirect:/professores");
			}
		}
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(professorRepository.existsById(id)) {
			this.professorRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("mensagem", "Professor #" + id + " deletado com sucesso!");
			redirectAttributes.addFlashAttribute("erro", false);
		} else {
			redirectAttributes.addFlashAttribute("mensagem", "Professor #" + id + " não encontrado no banco!");
			redirectAttributes.addFlashAttribute("erro", true);
		}
		return "redirect:/professores";
	}

}
