package com.tutrit.quickstart.rightservice.repo;

import com.tutrit.quickstart.rightservice.bean.RightSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepository extends JpaRepository<RightSide, String> {
}
