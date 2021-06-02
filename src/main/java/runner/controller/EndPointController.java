package runner.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import runner.entity.Appeal;
import runner.entity.Conclusion;
import runner.entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import runner.entity.Patients;
import runner.service.DoctorsService;
import runner.service.PatientsService;
import runner.service.AppealService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/endpoint", produces = MediaType.APPLICATION_JSON_VALUE)
public class EndPointController {

    @Autowired
    private PatientsService patientsService;
    private DoctorsService doctorsService;
    private AppealService appealService;

    @GetMapping
    public ResponseEntity<List<Doctors>> get() {
        List<Patients> patients = this.patientsService.read();
        int count = 0;
        List<Doctors> doctors = new ArrayList<>( );
        List<Doctors> doctorsAll = new ArrayList<>();
        List<Conclusion> conclusions = new ArrayList<>();
        for (Patients patients1 : patients) {
            for(Conclusion conclusion1 : conclusions){
                doctors.add(patients1.getAppeals(conclusion1.getDoctors()));
            }
        }
        for (Doctors doctor : doctors) {
            for (Doctors doctors1 : doctors) {
                if(doctor == doctors1) {
                    count++;
                    if(count >= 2 ) {
                        doctorsAll.add(doctors1);
                    }
                }
            }
            count = 0;
        }
        if(doctorsAll.isEmpty()) {
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(doctorsAll, HttpStatus.OK);
    }
}
