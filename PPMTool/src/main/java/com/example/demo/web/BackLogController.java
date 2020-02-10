package com.example.demo.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ProjectTask;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ProjecttaskServices;

@CrossOrigin
@RestController
@RequestMapping("/api/backlog")
public class BackLogController {
	
	@Autowired
	ProjecttaskServices projectTaskServie ;
	
	@Autowired
	MapValidationErrorService error;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBackLog(@Valid @RequestBody ProjectTask projectTask
			, BindingResult result,@PathVariable String backlog_id,Principal principle){
		
			
		ResponseEntity<?> errorMap= error.mapValidation(result);
		if(errorMap!=null) {
			return errorMap;
		}
		
		projectTaskServie.addProjectTask(backlog_id, projectTask,principle.getName());
		
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{backlog_id}")
	public ResponseEntity<Iterable<ProjectTask>> getAllProjectTask(@PathVariable String backlog_id,Principal principle){
		return new ResponseEntity<Iterable<ProjectTask>>(projectTaskServie.findProjectTask(backlog_id,principle.getName()),HttpStatus.OK);
	}
	
	@GetMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<ProjectTask> findPTByProjectSequence(@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
		
		ProjectTask projectTask = projectTaskServie.findPTByProjectSequence(backlog_id,pt_id,principal.getName());
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updatePrjectTaskByProjectSequence(@Valid @RequestBody ProjectTask projectTask,BindingResult result,
			@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
		
		ResponseEntity<?> errorMap= error.mapValidation(result);
		if(errorMap!=null) {
			return errorMap;
		}
		projectTaskServie.updatePTByPrjojectSequence(backlog_id,pt_id,projectTask,principal.getName());
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> deleteProjectTaskBySequenceNumber(@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
		
		projectTaskServie.deleteProjectTaskBySequenceNumber(backlog_id,pt_id,principal.getName());
		return new ResponseEntity<String>(backlog_id+" deleted successfully ",HttpStatus.OK);
	}

}
