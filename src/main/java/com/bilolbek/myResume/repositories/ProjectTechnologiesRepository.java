package com.bilolbek.myResume.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilolbek.myResume.api.model.ProjectTechnologies;

public interface ProjectTechnologiesRepository extends JpaRepository<ProjectTechnologies, Long>{
    List<ProjectTechnologies> findByProjectId(Long id);
}
