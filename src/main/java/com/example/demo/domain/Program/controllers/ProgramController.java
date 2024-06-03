package com.example.demo.domain.Program.controllers;

import com.example.demo.domain.Program.models.Programs;
import com.example.demo.domain.Program.services.ProgramsService;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private ProgramsService programsService;

    @Autowired
    public ProgramController(ProgramsService programsService) {
        this.programsService = programsService;
    }

    @GetMapping("")
    public ResponseEntity<List<Programs>> getAll(@RequestParam(required = false) String programName) {
        List<Programs> programs;
        if (programName != null) {
            programs = programsService.getProgramsByProgramName(programName);
        } else {
            programs = programsService.getAll();
        }
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Programs> createProgram(@RequestBody Programs program) throws ResourceCreationException {
        program = programsService.createProgram(program);
        return new ResponseEntity<>(program, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Programs> getProgramById(@PathVariable("id") Long id) {
        Programs program = programsService.getProgramById(id);
        return new ResponseEntity<>(program, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Programs> updateProgram(@PathVariable("id") Long id, @RequestBody Programs programDetails) {
        try {
            Programs updatedProgram = programsService.updateProgram(id, programDetails);
            return new ResponseEntity<>(updatedProgram, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable("id") Long id) {
        try {
            programsService.deleteProgram(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
