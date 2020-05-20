package com.TimeForStudy.application.lesson.model;

import lombok.Data;

@Data
public class UpdatePosition {

    private long oldPositionId;

    private String newPositionNum;

    private long newClassroomId;
}
