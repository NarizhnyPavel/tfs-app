package com.TimeForStudy.application.lesson.domain;

import com.TimeForStudy.application.classroom.domain.Classroom;
import com.TimeForStudy.application.group.domain.Group;
import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.lessontype.domain.LessonType;
import com.TimeForStudy.application.semester.domain.Semester;
import com.TimeForStudy.application.subject.domain.Subject;
import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_type_id")
    private LessonType lessonType;


}
