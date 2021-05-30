package runner.service.impl;

import org.springframework.stereotype.Service;
import runner.entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import runner.repository.PatientsRepository;
import runner.service.PatientsService;

import java.util.List;
@Service
public class PatientsServiceImpl implements PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;

    @Override
    public Patients read(Integer id) {
        return patientsRepository.findById(id).get();
    }

    @Override
    public List<Patients> read() {
        return patientsRepository.findAll();
    }

    @Override
    public void save(Patients entity) {
        patientsRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        patientsRepository.deleteById(id);
    }
}
