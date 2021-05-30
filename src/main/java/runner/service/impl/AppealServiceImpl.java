package runner.service.impl;

import runner.entity.Appeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runner.repository.AppealRepository;
import runner.service.AppealService;

import java.util.List;

@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealRepository appealRepository;

    @Override
    public Appeal read(Integer id) {
        return appealRepository.findById(id).get();
    }

    @Override
    public List<Appeal> read() {
        return appealRepository.findAll();
    }

    @Override
    public void save(Appeal entity) {
        appealRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        appealRepository.deleteById(id);
    }

}
