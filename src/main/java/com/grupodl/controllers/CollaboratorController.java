package com.grupodl.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupodl.models.Collaborator;
import com.grupodl.services.CollaboratorService;
import com.grupodl.services.exceptions.FieldInvalidException;

@RestController
@RequestMapping("/api")
public class CollaboratorController {
	
	@Autowired
	CollaboratorService collaboratorService;
	
	@GetMapping("/collaborators")
	public ResponseEntity<List<Collaborator>> findAllCollaborator(){
		List<Collaborator> listCollaborator = collaboratorService.findAllCollaborator();
		return ResponseEntity.status(HttpStatus.OK).body(listCollaborator);
	}
	
	@GetMapping("/collaborators/{cpf}")
	public ResponseEntity<Collaborator> findCollaboratorByCpf(@PathVariable String cpf){
		Collaborator collaborator = collaboratorService.findCollaboratorByCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(collaborator);
	}
	
	@PostMapping("/collaborators")
	public ResponseEntity<Void> insertBreakfast(@RequestBody @Valid Collaborator collaborator, BindingResult result){
		if(result.hasErrors()) {
			throw new FieldInvalidException("Algum campo est� invalido!");
		}
		collaboratorService.insertBreakfast(collaborator);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@PutMapping("collaborators/{cpf}")
    public ResponseEntity<Void> updateBreakfast(@RequestBody Collaborator collaborator, @PathVariable String cpf){	
		collaboratorService.updateBreakfast(collaborator, cpf);
		return ResponseEntity.status(HttpStatus.OK).build();
    }
	
	@DeleteMapping("collaborators/{cpf}")
    public ResponseEntity<Void> deleteBreakfast(@PathVariable String cpf){	
		collaboratorService.deleteBreakfast(cpf);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
