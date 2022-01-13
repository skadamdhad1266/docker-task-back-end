package com.KnowledgePioneer.backEndTask.repository;

import com.KnowledgePioneer.backEndTask.entity.PatentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentRepository extends JpaRepository<PatentInfo,Long> {


}
