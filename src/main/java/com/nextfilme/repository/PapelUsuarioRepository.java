package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.PapelUsuario;

@Repository
public interface PapelUsuarioRepository extends JpaRepository<PapelUsuario, Integer> {
}
