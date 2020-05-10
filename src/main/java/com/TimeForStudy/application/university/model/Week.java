package com.TimeForStudy.application.university.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Week {

    private boolean monday = false;

    private boolean tuesday = false;

    private boolean wednesday = false;

    private boolean thursday = false;

    private boolean friday = false;

    private boolean saturday = false;

    private boolean sunday = false;
}