package runner.service;

import java.util.List;

import runner.entity.SuperID;


public interface Service<T extends SuperID> {
	T read(Integer id);
	List<T> read();
	void save(T entity);
	void delete(Integer id);
}
