package com.TimeForStudy.application.lesson.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SearchDto {

    private Long id;

    private String label;

    private int  type;

    public SearchDto(long id, String label, int type) {
        this.id = id;
        this.label = label;
        this.type = type;

    }
}
