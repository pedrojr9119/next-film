package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Integer> {

}
