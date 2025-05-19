package br.com.luciano.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("nome", "Luciano");
		return mv; // o Spring vai renderizar o arquivo templates/hello.html
	}
	
	/*@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("nome", "Luciano");
		return "hello"; // o Spring vai renderizar o arquivo templates/hello.html
	}*/
	
}
