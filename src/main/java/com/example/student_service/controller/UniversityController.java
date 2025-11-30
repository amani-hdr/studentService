package com.example.student_service.controller;

import com.example.student_service.model.University;
import com.example.student_service.model.Student;
import com.example.student_service.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@CrossOrigin
public class UniversityController {

    private final UniversityRepository universityRepository;

    public UniversityController(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    // ➕ Ajouter une université
    @PostMapping
    public University addUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

    // 📋 Lister toutes les universités
    @GetMapping
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // 🔍 Récupérer une université par ID
    @GetMapping("/{id}")
    public University getUniversityById(@PathVariable Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found"));
    }

    // 👩‍🎓 Obtenir tous les étudiants d'une université
    @GetMapping("/{id}/students")
    public List<Student> getStudentsByUniversity(@PathVariable Long id) {
        University uni = universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found"));
        return uni.getStudents();
    }
}
