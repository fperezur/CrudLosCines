package com.rf.lcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.lcs.persistence.entity.Cine;

public interface ICine extends JpaRepository<Cine, Long>{

}
