package com.TimeForStudy.application.parser.service.impl;

import com.TimeForStudy.application.classroom.domain.ClassroomRepository;
import com.TimeForStudy.application.classroom.model.AddClassroomDto;
import com.TimeForStudy.application.group.domain.GroupEntity;
import com.TimeForStudy.application.group.domain.GroupRepository;
import com.TimeForStudy.application.group.model.AddGroupDto;
import com.TimeForStudy.application.parser.domain.ParserResponse;
import com.TimeForStudy.application.parser.service.Parser;
import com.TimeForStudy.application.parser.service.ParserService;
import com.TimeForStudy.application.subject.domain.SubjectRepository;
import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.AddUserDto;
import com.TimeForStudy.application.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
     * Получение url ссылки на парсикг
     */
    @Override
    public ParserResponse inUrlParser(String url) {
        Parser parser = Parser.getInstance();
        parser.setUrl(url);
        String message = parser.loadFromUrl();
        ParserResponse parserResponse = new ParserResponse();
        parserResponse.setStatus(message);
        if (message=="Успешно") {
            List<AddUserDto> professors = parser.professors;
            List<AddGroupDto> groups = parser.groups;
            List<AddSubjectDto> subjects = parser.subjects;
            List<AddClassroomDto> classrooms = parser.classrooms;
            userRepository.saveAll(professors.stream().map(AddUserDto::on).collect(Collectors.toList()));
            groupRepository.saveAll(groups.stream().map(AddGroupDto::on).collect(Collectors.toList()));
            subjectRepository.saveAll(subjects.stream().map(AddSubjectDto::on).collect(Collectors.toList()));
            classroomRepository.saveAll(classrooms.stream().map(AddClassroomDto::on).collect(Collectors.toList()));
            parserResponse.setGroupnum(groups.size());
            parserResponse.setProfnum(professors.size());
            parserResponse.setRoomnum(classrooms.size());
            parserResponse.setSubjectnum(subjects.size());
        }
        return parserResponse;
    }
}
