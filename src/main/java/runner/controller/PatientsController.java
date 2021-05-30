package runner.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import runner.entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runner.service.PatientsService;

import java.util.List;

@RestController
@RequestMapping(value = "api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @GetMapping
    public ResponseEntity<List<Patients>> get() {
        List<Patients> entities = patientsService.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patients> getById(@PathVariable int id) {
        Patients entity = patientsService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Patients entity) {
        patientsService.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Patients entity) {
        patientsService.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        patientsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
