package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TypeSearch {

    private boolean professor;

    private  boolean group;

    private boolean classroom;
}
