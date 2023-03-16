package com.coiffure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.coiffure.model.Servise;
import com.coiffure.service.ServiseServises;
import com.coiffure.model.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.coiffure.repository.ServiseRepository;
import com.coiffure.Constants;

@RestController
@RequestMapping("/api/servise")
public class ServiseController {
	
	@Autowired
    ServiseServises serviseServices;

	@Autowired
	private ServiseRepository serviseRepository;



	@GetMapping("/servises")
	public String showServiselist(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/servises/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Servise> page = serviseRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listServises", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "servises";
	}



	
	@PostMapping("/create/")
	public ResponseEntity<Message> addNewServise(@RequestBody Servise servise) {
		try {
			Servise returnedServise = serviseServices.saveServise(servise);
			
			return new ResponseEntity<Message>(new Message("Upload Successfully!",
			  null, null,Arrays.asList(returnedServise), ""), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail to post a new Servise!",  null, null, null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveServiseInfo() {
		
		try {
			List<Servise> serviseInfos = serviseServices.getServiseInfos();
			
			return new ResponseEntity<Message>(new Message("Get Servise Infos!", null,null, serviseInfos,""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail!",  null, null, null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<Message> getServiseById(@PathVariable Long id) {
		try {
			Optional<Servise> optServise = serviseServices.getServiseById(id);
			
			if(optServise.isPresent()) {
				return new ResponseEntity<Message>(new Message
				("Successfully! Retrieve a Servise by id = " + id, null,null, Arrays.asList(optServise.get()),""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message("Failure -> NOT Found a Servise by id = " + id, null, null, null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",  null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message> updateServiseById(@RequestBody Servise _servise, @PathVariable Long id) {
		try {
			if(serviseServices.checkExistedServise(id)) {
				Servise servise = serviseServices.getServiseById(id).get();
				
				//set new values for servise
				servise.setNom_servise(_servise.getNom_servise());
				servise.setPrix_servise(_servise.getPrix_servise());
				servise.setDescription_servise(_servise.getDescription_servise());
				servise.setDate_creation_servise(_servise.getDate_creation_servise());
		
				// save the change to database
				serviseServices.updateServise(servise);
				
				return new ResponseEntity<Message>(new Message("Successfully! Updated a Servise " + "with id = " + id, null,  null,
				Arrays.asList(servise),  ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Servise " + "with id = " + id,  null, null,
						null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message> deleteServiseById(@PathVariable Long id) {
		try {
			// checking the existed of a Servise with id
			if(serviseServices.checkExistedServise(id)) {
				serviseServices.deleteServiseById(id);
				
				return new ResponseEntity<Message> (new Message("Successfully! Delete a Servise with id = " + id, null, null, null, ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Servise " + "with id = " + id, null, null,  null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
