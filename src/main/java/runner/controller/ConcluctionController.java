package runner.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import runner.entity.Conclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runner.service.ConclusionService;

import java.util.List;

@RestController
@RequestMapping(value = "api/concluction", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConcluctionController {

    @Autowired
    private ConclusionService conclusionService;


    @GetMapping
    public ResponseEntity<List<Conclusion>> get() {
        List<Conclusion> entities = conclusionService.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Conclusion> getById(@PathVariable int id) {
        Conclusion entity = conclusionService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Conclusion entity) {
        conclusionService.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Conclusion entity) {
        conclusionService.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        conclusionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
