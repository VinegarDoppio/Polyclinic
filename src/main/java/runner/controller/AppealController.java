package runner.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import runner.entity.Appeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runner.service.AppealService;

import java.util.List;

@RestController
@RequestMapping(value = "api/appeal", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppealController {

    @Autowired
    private AppealService appealService;


    @GetMapping
    public ResponseEntity<List<Appeal>> get() {
        List<Appeal> entities = appealService.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appeal> getById(@PathVariable int id) {
        Appeal entity = appealService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Appeal entity) {
        appealService.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Appeal entity) {
        appealService.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        appealService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
