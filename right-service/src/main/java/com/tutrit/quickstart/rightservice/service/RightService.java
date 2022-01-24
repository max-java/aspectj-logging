package com.tutrit.quickstart.rightservice.service;

import com.tutrit.quickstart.rightservice.bean.RightSide;
import com.tutrit.quickstart.rightservice.repo.RightRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RightService {
    RightRepository rightRepository;

    public RightSide findById(String id) {
        return rightRepository.findById(id).get();
    }

    public List<RightSide> findAll() {
        return rightRepository.findAll();
    }
}
