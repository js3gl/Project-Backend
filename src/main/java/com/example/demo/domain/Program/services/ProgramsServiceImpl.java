package com.example.demo.domain.Program.services;

import com.example.demo.domain.Program.models.Programs;
import com.example.demo.domain.Program.repos.ProgramsRepo;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramsServiceImpl implements ProgramsService {

    private ProgramsRepo programsRepo;

    public ProgramsServiceImpl(ProgramsRepo programsRepo) {
        this.programsRepo = programsRepo;
    }

    @Override
    public Programs createProgram(Programs program) throws ResourceCreationException {
        return programsRepo.save(program);
    }

    @Override
    public Programs getProgramById(Long id) throws ResourceNotFoundException {
        return programsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No program with id: " + id));
    }

    @Override
    public List<Programs> getAll() {
        return programsRepo.findAll();
    }

    @Override
    public Programs updateProgram(Long id, Programs programDetails) throws ResourceNotFoundException {
        Programs program = getProgramById(id);
        program.setProgramName(programDetails.getProgramName());
        program.setCost(programDetails.getCost());
        program.setExpenseId(program.getExpenseId());
        return programsRepo.save(program);
    }

    @Override
    public Boolean deleteProgram(Long id) throws ResourceNotFoundException {
        Programs program = programsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program does not exist, cannot delete"));
        programsRepo.delete(program);
        return true;
    }

    @Override
    public List<Programs> getProgramsByProgramName(String programName) {
        return programsRepo.findByProgramName(programName);
    }
}
