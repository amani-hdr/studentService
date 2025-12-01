package com.example.student_service.controller;

import com.example.student_service.model.University;
import com.example.student_service.model.Student;
import com.example.student_service.repository.UniversityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

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
    
    // ✏️ METTRE À JOUR une université (PUT) - CORRECTION AJOUTÉE
    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University universityDetails) {
        // Recherche l'université existante
        University existingUniversity = universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with ID: " + id));

        // Met à jour les champs
        existingUniversity.setName(universityDetails.getName());
        existingUniversity.setLocation(universityDetails.getLocation());

        // Sauvegarde et retourne l'entité mise à jour
        University updatedUniversity = universityRepository.save(existingUniversity);
        return ResponseEntity.ok(updatedUniversity);
    }

    // 🗑️ SUPPRIMER une université (DELETE) - CORRECTION AJOUTÉE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        if (!universityRepository.existsById(id)) {
            throw new EntityNotFoundException("University not found with ID: " + id);
        }
        universityRepository.deleteById(id);
        // Retourne une réponse 204 No Content pour la suppression réussie
        return ResponseEntity.noContent().build();
    }
}
