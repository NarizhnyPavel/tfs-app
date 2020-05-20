package com.TimeForStudy.application.parser.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.parser.service.ParserService;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {

    /**
     * {@link ClassroomRepository}
     */
    private final ClassroomRepository classroomRepository;

    /**
     * {@link SubjectRepository}
     */
    private final SubjectRepository subjectRepository;

    /**
     * {@link GroupRepository}
     */
    private final GroupRepository groupRepository;

    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;

    /**
     * Получение url ссылки на парсикг
     */
    @Override
    public String inUrlParser(String url) {

        return " ";
    }
}
