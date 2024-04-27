package com.rf.lcs.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rf.lcs.exceptions.DaoException;
import com.rf.lcs.exceptions.DomainException;

@Service
public interface IService<T,S> {
	
	public T insert(T t) throws DomainException, DaoException;
	
	public boolean update(T t) throws DomainException, DaoException;
	
	public boolean deleteById(S s) throws DaoException;
	
	public List<T> listAll();
	
	public Optional<T> findById(S s);
	
	public boolean existById(S s);
}
