package com.TimeForStudy.application.subject.web;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Обработчик запросов преподаваемой дисциплины.
 *
 * @author Velikanov Artyom.
 */
@RestController
@RequiredArgsConstructor
public class SubjectController {

    /**
     * {@link SubjectService}
     */
    private final SubjectService subjectService;

    /**
     * Получение списка преподаваемых дисциплин.
     *
     * @return список преподаваемых дисциплин.
     */
    @GetMapping(value = "/subject")
    public List<SubjectDto> getSubjects() {
        return subjectService.findAll();
    }

    /**
     * Получение дисциплин по наименованию.
     *
     * @param name наименовае дисциплины.
     * @return список преподаваемых дисциплин.
     */
    @PostMapping(value = "/subject")
    public List<IdNameDto> postSubjects(@RequestBody String name) {
        return subjectService.findAllSubjects(name);
    }

    /**
     * Получение преподаваемой дисциплины по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина
     */
    @GetMapping(value = "/subject/{id}")
    public SubjectDto getSubject(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    /**
     * Добавление преподаваемой дисциплины.
     *
     * @param subject преподаваемая дисциплина.
     */
    @PostMapping(value = "/admin/subject")
    public void addSubject(@RequestBody SubjectDto subject) {
        subjectService.saveSubject(subject);
    }

    /**
     * Редактирование преподаваемой дисциплины.
     *
     * @param id идентификатор.
     * @param subjectDto преподаваемая дисциплина.
     */
    @PutMapping(value = "/admin/subject/{id}")
    public void updateSubject(@PathVariable Long id, @RequestBody SubjectDto subjectDto) {
        subjectService.updateSubject(id, subjectDto);
    }

    /**
     * Удаление преподаваемой дисциплины.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/admin/subject/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

}
