package com.example.demo.domain.Program.repos;

import com.example.demo.domain.Program.models.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramsRepo extends JpaRepository<Programs, Long> {
    List<Programs> findByProgramName(String programName);
}
