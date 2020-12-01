package com.TimeForStudy.application.parser.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomEntity;
import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.parser.model.ParserResponse;
import com.TimeForStudy.application.parser.service.Parser;
import com.TimeForStudy.application.parser.service.ParserService;
import com.TimeForStudy.application.subject.domain.SubjectEntity;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * Парсинг таблицы по url.
     * Таблица должна располагаться на яндекс диске.
     *
     * @param url публичная ссылка для доступа к таблице.
     * @return строка состояния.
     */
    @Override
    public ParserResponse inUrlParser(String url) {
        Parser parser = Parser.getInstance();
        parser.setUrl(url);
        String message = parser.loadFromUrl();
        ParserResponse parserResponse = new ParserResponse();
        parserResponse.setStatus(message);
        if ("Успешно".equals(message)) {
            List<AddUserDto> professors = parser.professors;
            List<IdNameDto> groups = parser.groups;
            List<SubjectDto> subjects = parser.subjects;
            List<IdNameDto> classrooms = parser.classrooms;
//            userRepository.saveAll(professors.stream().map(AddUserDto::on).collect(Collectors.toList()));
            groupRepository.saveAll(groups.stream()
                    .map(it -> {
                        GroupEntity entity = new GroupEntity();
                        entity.setNumber(it.getName());
                        return entity;
                    }).collect(Collectors.toList()));
            subjectRepository.saveAll(subjects.stream()
                    .map(it -> {
                        SubjectEntity entity = new SubjectEntity();
                        entity.setName(it.getName());
                        entity.setArc(it.getArc());
                        return entity;
                    }).collect(Collectors.toList()));
            classroomRepository.saveAll(classrooms.stream()
                    .map(it -> {
                        ClassroomEntity classroom = new ClassroomEntity();
                        classroom.setNumber(it.getName());
                        return classroom;
                    }).collect(Collectors.toList()));
            parserResponse.setGroupnum(groups.size());
            parserResponse.setProfnum(professors.size());
            parserResponse.setRoomnum(classrooms.size());
            parserResponse.setSubjectnum(subjects.size());
        }
        return parserResponse;
    }
}
