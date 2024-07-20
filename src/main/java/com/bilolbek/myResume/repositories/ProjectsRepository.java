package com.bilolbek.myResume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bilolbek.myResume.api.model.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}
