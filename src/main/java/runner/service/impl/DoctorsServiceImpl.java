package runner.service.impl;


import org.springframework.stereotype.Service;
import runner.entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import runner.repository.DoctorsRepository;
import runner.service.DoctorsService;

import java.util.List;
@Service
public class DoctorsServiceImpl implements DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public Doctors read(Integer id) {
        return doctorsRepository.findById(id).get();
    }

    @Override
    public List<Doctors> read() {
        return doctorsRepository.findAll();
    }

    @Override
    public void save(Doctors entity) {
        doctorsRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        doctorsRepository.deleteById(id);
    }
}
