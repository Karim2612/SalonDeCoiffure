package com.coiffure.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.coiffure.service.RdvServises;
import com.coiffure.model.Rdv;
import com.coiffure.repository.RdvRepository;
import com.coiffure.repository.ServiseRepository;
import com.coiffure.repository.UserRepository;
import com.coiffure.model.Message;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.coiffure.Constants;

@RestController
@RequestMapping("/api/rdv")
public class RdvController {

	@Autowired
	RdvServises rdvServices;

	@Autowired
	private ServiseRepository serviseRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RdvRepository rdvRepository;


	@PostMapping("/create")
	public ResponseEntity<Message> addNewRdv(@RequestBody Rdv rdv) {
		try {
			Rdv returnedRdv = rdvServices.saveRdv(rdv);

			return new ResponseEntity<Message>(new Message("Upload Successfully!", null,
			Arrays.asList(returnedRdv), null, ""), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Fail to post a new Rdv!", null, null, null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rdvs/new")
	public String showRdvNewForm(Model model) {
		model.addAttribute("listServises", serviseRepository.findAll());
		model.addAttribute("rdv", new Rdv());	
		model.addAttribute("listUsers", userRepository.findAll());

		return "rdv_form";
	}

	@GetMapping("/rdvs/edit/{id}")
	public String showDriverEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("listServises", serviseRepository.findAll());
		model.addAttribute("rdv", rdvRepository.findById(id).get());
		model.addAttribute("listUsers", userRepository.findAll());
		return "rdv_form";
	}
	

	@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveUserInfo() {

		try {
			List<Rdv> rdvInfos = rdvServices.getRdvInfos();

			return new ResponseEntity<Message>(new Message("Get Rdv Infos!", null,  rdvInfos, null, ""),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Fail!", null, null, null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findone/{id}")
	public ResponseEntity<Message> getRdvById(@PathVariable Long id) {
		try {
			Optional<Rdv> optRdv = rdvServices.getRdvById(id);

			if (optRdv.isPresent()) {
				return new ResponseEntity<Message>(
						new Message("Successfully! Retrieve a rdv by id = " + id, null,
						Arrays.asList(optRdv.get()), null, ""),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(
						new Message("Failure -> NOT Found a rdv by id = " + id, null, null, null, ""),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message> updateRdvById(@RequestBody Rdv _rdv, @PathVariable Long id) {
		try {
			if (rdvServices.checkExistedRdv(id)) {
				Rdv rdv = rdvServices.getRdvById(id).get();

				// set new values for rdv
				rdv.setDate_heur_rdv(_rdv.getDate_heur_rdv());
				rdv.setDescription_rdv(_rdv.getDescription_rdv());
				rdv.setDate_creation_rdv(_rdv.getDate_creation_rdv());

				// save the change to database
				rdvServices.updateRdv(rdv);

				return new ResponseEntity<Message>(
						new Message("Successfully! Updated a Rdv " + "with id = " + id,null,
						Arrays.asList(rdv), null, ""),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(
						new Message("Failer! Can NOT Found a Rdv " + "with id = " + id, null, null, null, ""),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message> deleteRdvById(@PathVariable Long id) {
		try {
			// checking the existed of a Rdv with id
			if (rdvServices.checkExistedRdv(id)) {
				rdvServices.deleteRdvById(id);

				return new ResponseEntity<Message>(
						new Message("Successfully! Delete a Rdv with id = " + id, null, null, null, ""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(
						new Message("Failer! Can NOT Found a Rdv " + "with id = " + id, null, null, null, ""),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
