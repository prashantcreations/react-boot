package com.example.demo.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Project;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ProjectService;

@CrossOrigin// form React srever to conect to spring server  
@RestController
@RequestMapping("/ppmtool/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")// will add principal as an arument if we are implemanting jwt otherwise there is no need
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result,Principal principal){
		
		// first way validation
		/*
		 * if(result.hasErrors()) { return new
		 * ResponseEntity<String>("not a valid object",HttpStatus.BAD_REQUEST); }
		 */		
		
		//seconde way of validation
		ResponseEntity<?> errorResponse = mapValidationErrorService.mapValidation(result);
		if(errorResponse!=null) {
			return errorResponse;
		}
		projectService.createProject(project,principal.getName());
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?>  getProjectById(@PathVariable String projectId,Principal principal) {
		
		Project project = projectService.findByProjectIdentification(projectId,principal.getName());
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<Project>> findAll(Principal principal){
		Iterable iterable = projectService.findAll(principal.getName());
		return new ResponseEntity<Iterable<Project>>(iterable,HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId,Principal principle) {
		projectService.deleteProjectById(projectId,principle.getName());
		return new ResponseEntity<String>("Project Id :-"+projectId+" is deleted",HttpStatus.OK);
		
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProjectById(@RequestBody Project project){
		projectService.updateProjectById(project);
		return new ResponseEntity<String>("Project Id :-"+project.getProjectIdentification()+" is updated",HttpStatus.OK);
	}
	
	
}
