package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DaysDto {

    private String dayName;

    private List<InfoLessonDto> infoLessonDtos;
}
