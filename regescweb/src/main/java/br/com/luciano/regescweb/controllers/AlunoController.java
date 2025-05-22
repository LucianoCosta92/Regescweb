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

import br.com.luciano.regescweb.dto.AlunoDTO;
import br.com.luciano.regescweb.models.Aluno;
import br.com.luciano.regescweb.repositories.AlunoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		List<Aluno> alunos = this.alunoRepository.findAll();
		ModelAndView mv = new ModelAndView("alunos/index");
		mv.addObject("alunos", alunos);
		return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView formulario(AlunoDTO request) {
		ModelAndView mv = new ModelAndView("alunos/new");
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid AlunoDTO request, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("alunos/new");
			return mv;
		} else {
			Aluno aluno = request.toAluno();
			this.alunoRepository.save(aluno);
			return new ModelAndView("redirect:/alunos/" + aluno.getId());
		}
	}
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Aluno> optional = this.alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno aluno = optional.get();
			ModelAndView mv = new ModelAndView("alunos/show");
			mv.addObject("aluno", aluno);
			return mv;
		} else {
			return new ModelAndView("redirect:/alunos");
		}
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, AlunoDTO request) {
		Optional<Aluno> optional = this.alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno aluno = optional.get();
			request.fromAluno(aluno);

			ModelAndView mv = new ModelAndView("alunos/edit");
			mv.addObject("alunoId", aluno.getId());
			return mv;
		} else {
			return new ModelAndView("redirect:/alunos");
		}
	}
	
	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid AlunoDTO request, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("alunos/edit");
			mv.addObject("alunoId", id);
			return mv;
		} else {
			Optional<Aluno> optional = this.alunoRepository.findById(id);
			if (optional.isPresent()) {
				Aluno aluno = request.toAluno(optional.get());
				this.alunoRepository.save(aluno);
				
				return new ModelAndView("redirect:/alunos/" + aluno.getId());
			} else {
				return new ModelAndView("redirect:/alunos");
			}
		}
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(alunoRepository.existsById(id)) {
			this.alunoRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("mensagem", "Aluno #" + id + " deletado com sucesso!");
			redirectAttributes.addFlashAttribute("erro", false);
		} else {
			redirectAttributes.addFlashAttribute("mensagem", "Aluno #" + id + " não encontrado no banco!");
			redirectAttributes.addFlashAttribute("erro", true);
		}
		return "redirect:/alunos";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
