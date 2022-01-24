package com.tutrit.quickstart.leftservice.bean;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.tutrit.quickstart.traceservice.aspect.Traceable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeftSide implements Traceable {
    @Id
    @GeneratedValue
    Long id;
    String name;

    @Override
    public String traceId() {
        return id.toString();
    }
}
