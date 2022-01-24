package com.tutrit.quickstart.leftservice.rest;

import com.tutrit.quickstart.leftservice.bean.LeftSide;
import com.tutrit.quickstart.leftservice.service.LeftService;
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
public class LeftController {
    LeftService leftService;

    @GetMapping("/left/{id}")
    public LeftSide findById(@PathVariable Long id) {
        return leftService.findById(id);
    }

    @GetMapping("/left")
    public List<LeftSide> findByAll() {
        leftService.voidMethod();
        return leftService.findAll();
    }
}
