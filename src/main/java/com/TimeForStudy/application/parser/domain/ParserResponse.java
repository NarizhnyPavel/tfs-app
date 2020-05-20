package com.TimeForStudy.application.parser.domain;


import lombok.Data;

@Data
public class ParserResponse {

    private String status;
    private int profnum;
    private int subjectnum;
    private int roomnum;
    private int groupnum;

}
