package com.cg.sprint1.proj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint1.proj.entities.Client;
import com.cg.sprint1.proj.entities.Engineer;
import com.cg.sprint1.proj.exceptions.InvalidClientIdException;
import com.cg.sprint1.proj.exceptions.InvalidCredentialsException;
import com.cg.sprint1.proj.exceptions.InvalidEngineerIdException;
import com.cg.sprint1.proj.exceptions.PermissionDeniedException;
import com.cg.sprint1.proj.services.ClientSerivce;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientSerivce clientservice;


	@PostMapping(value = "/save",consumes = "application/json")
	public ResponseEntity<String> saveClient(@RequestBody Client c) {
		clientservice.saveClient(c.getClientId(),c.getPassword(),c.getAddress(),c.getPhoneNumber());
		return new ResponseEntity<String>(" *** CLIENT ADDED SUCCESSFULLY *** ",HttpStatus.OK);
	}

	@GetMapping(value = "/details/clientId/{clientId}", produces = "application/json")
	public ResponseEntity<Client> getClientByCLientId(@PathVariable("clientId") String clientId) throws InvalidClientIdException {
		Optional<Client> c = null;
		c = clientservice.getClientByCLientId(clientId);
		if(c.isPresent()) {
			return new ResponseEntity<Client>(clientservice.getClientByCLientId(clientId).get(),HttpStatus.OK);
		}
		else {
			throw new InvalidClientIdException("!!! Given ClientId is invalid !!!");
		}
	}

	@GetMapping(value = "/engineerId/{engineerId}", produces = "application/json")
	public ResponseEntity<Optional<Engineer>> getEngineerById(@PathVariable("engineerId") int id) throws InvalidEngineerIdException {
		Optional<Engineer> e = null;
		e = clientservice.getEngineerById(id);
		if(e.isPresent()) {
			return new ResponseEntity<Optional<Engineer>>(clientservice.getEngineerById(id),HttpStatus.OK);
		}
		else {
			throw new InvalidEngineerIdException("!!! Given EngineerId is invalid !!!");
		}
		
	}

	@GetMapping(value = "/engineerdomain/{category}", produces = "application/json")
	public ResponseEntity<List<Engineer>> getEngineersByDomain(@PathVariable("category") String category) {
		return new ResponseEntity<List<Engineer>>(clientservice.getEngineersByDomain(category), HttpStatus.OK);

	}

	@PutMapping(value = "/changestatus",consumes = "application/json")
	 public ResponseEntity<String> chnageStatusOfComplaint(@RequestParam int complaintId,@RequestParam String status) throws PermissionDeniedException{
	      try {
			clientservice.changeStatusOfComplaint(complaintId, status);
		} catch (PermissionDeniedException e) {
			throw new PermissionDeniedException("!!! PERMISSION DENIED !!!");
		}
	      return new ResponseEntity<String>(" *** STATUS CHANGED SUCCESSFULLY *** ",HttpStatus.OK);
	}
	
	@GetMapping(value = "/clientSignIn", produces = "application/json")
	public ResponseEntity<Client>  signIn(@RequestParam String clientId,@RequestParam String password) throws InvalidCredentialsException, InvalidClientIdException{
		Client myClient = null;
		try {
			System.out.println("controller"+clientId);
			myClient = clientservice.signIn(clientId, password);
		} catch (InvalidCredentialsException e) {
			throw new InvalidCredentialsException(" !!! Invalid Credentials Given !!! ");
		}
		return new ResponseEntity<Client>(myClient,HttpStatus.ACCEPTED);
	}
	
	
}
