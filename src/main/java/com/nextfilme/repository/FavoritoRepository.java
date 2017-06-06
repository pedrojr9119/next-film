package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

}
