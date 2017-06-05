package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}
