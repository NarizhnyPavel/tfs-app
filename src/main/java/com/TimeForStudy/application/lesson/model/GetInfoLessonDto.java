package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetInfoLessonDto {

    private Long userId;

    private byte role;

    private int weekNum;
}
