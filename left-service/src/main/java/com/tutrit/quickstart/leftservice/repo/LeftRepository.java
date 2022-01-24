package com.tutrit.quickstart.leftservice.repo;

import com.tutrit.quickstart.leftservice.bean.LeftSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeftRepository extends JpaRepository<LeftSide, Long> {
}
