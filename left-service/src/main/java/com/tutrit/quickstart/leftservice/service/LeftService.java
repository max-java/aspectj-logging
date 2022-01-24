package com.tutrit.quickstart.leftservice.service;

import com.tutrit.quickstart.leftservice.bean.LeftSide;
import com.tutrit.quickstart.leftservice.repo.LeftRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeftService {
    LeftRepository leftRepository;

    public LeftSide findById(Long id) {
        return leftRepository.findById(id).get();
    }

    public List<LeftSide> findAll() {
        return leftRepository.findAll();
    }

    public void voidMethod() {

    }
}
