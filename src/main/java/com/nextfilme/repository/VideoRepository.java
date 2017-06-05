package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

}
