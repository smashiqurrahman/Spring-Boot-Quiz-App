package com.ashiq.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashiq.main.model.Programmer;
import com.ashiq.main.repository.ProgrammerRepo;

@Controller
@ControllerAdvice
public class MainController {

	@Autowired
	ProgrammerRepo programmerRepo;

	@GetMapping("/home")
	public String homePage() {
		return "HomePage.html";
	}

	@ModelAttribute
	public void welcomeMsg(Model model) {
		model.addAttribute("msg", "Welcome Dev to this spring boot tutorial");
	}

	@PostMapping("/addProgrammer")
	public String addProgrammer(@ModelAttribute Programmer programmer) {
		programmerRepo.save(programmer);

		return "redirect:/home";
	}

	@PostMapping("/findById")
	public String findProgrammer(@RequestParam int pId, Model m) {
		Programmer p1 = programmerRepo.getOne(pId);

		m.addAttribute("programmer", p1);

		return "ProgrammerInfo.html";
	}
	
	@PostMapping("/findByLang")
	public String findByLang(@RequestParam String pLang, Model m) {
		List<Programmer> listP1 = programmerRepo.findBypLang(pLang);
		System.out.println(listP1);
		m.addAttribute("programmer", listP1);
		
		return "AllProgrammer.html";
	}

	
	@PostMapping("/findByName")
	public String findByName(@RequestParam String pName, Model m) {
		List<Programmer> listP1 = programmerRepo.findp(pName);
		System.out.println(listP1);
		m.addAttribute("programmer", listP1);
		
		return "AllProgrammer.html";
	}
	


	@GetMapping("/deleteProgrammer")
	public String deleteProgrammer(@RequestParam int pId) {
		programmerRepo.deleteById(pId);	

		return "redirect:/home";
	}
	
	@PostMapping("/updateProgrammer")
	public String updateProgrammer(@ModelAttribute Programmer programmer) {
		Programmer p1 = programmerRepo.getOne(programmer.getpId());
		p1.setpName(programmer.getpName());
		p1.setpLang(programmer.getpLang());
		
		programmerRepo.save(p1);

		return "ProgrammerInfo.html";
	}

	@GetMapping("allProgrammer")
	public String allProgrammer(Model m) {
		List<Programmer> pList = new ArrayList<Programmer>();
		pList.add(new Programmer(1001, "Raynold", "Java"));
		pList.add(new Programmer(1002, "Dave", "Python"));
		pList.add(new Programmer(1003, "Stacy", "C#"));
		m.addAttribute("programmer", pList);

		return "AllProgrammer.html";
	}
}
