package com.example.demo.domain.Program.services;

import com.example.demo.domain.Program.models.Programs;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ProgramsService {
    Programs createProgram(Programs program) throws ResourceCreationException;
    Programs getProgramById(Long id) throws ResourceNotFoundException;
    List<Programs> getAll();
    Programs updateProgram(Long id, Programs programsDetails) throws ResourceNotFoundException;
    Boolean deleteProgram(Long id) throws ResourceNotFoundException;
    List<Programs> getProgramsByProgramName(String programName);
}

