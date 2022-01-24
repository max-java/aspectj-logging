package com.tutrit.quickstart.rightservice.rest;

import com.tutrit.quickstart.rightservice.bean.RightSide;
import com.tutrit.quickstart.rightservice.service.RightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RightController {
    RightService rightService;

    @GetMapping("/left/{id}")
    public RightSide findById(@PathVariable String id) {
        return rightService.findById(id);
    }

    @GetMapping("/left")
    public List<RightSide> findByAll() {
        return rightService.findAll();
    }
}
