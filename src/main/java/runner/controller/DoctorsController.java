package runner.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import runner.entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import runner.service.DoctorsService;

import java.util.List;

@RestController
@RequestMapping(value = "api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorsController {

    @Autowired
    private DoctorsService doctorsService;


    @GetMapping
    public ResponseEntity<List<Doctors>> get() {
        List<Doctors> entities = doctorsService.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getById(@PathVariable int id) {
        Doctors entity = doctorsService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Doctors entity) {
        doctorsService.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Doctors entity) {
        doctorsService.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        doctorsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
