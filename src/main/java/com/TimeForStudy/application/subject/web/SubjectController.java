package com.TimeForStudy.application.subject.web;

import com.TimeForStudy.application.subject.model.AddSubjectDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * Возвращает список преподаваемых дисциплин.
     *
     * @return список преподаваемых дисциплин.
     */
    @GetMapping(value = "/subject")
    public List<SubjectDto> getSubjects() {
        return subjectService.findAll();
    }

    /**
     * Возвращает преподаваемую дисциплину по идентификатору.
     *
     * @param id идентификатор.
     * @return преподаваемая дисциплина
     */
    @GetMapping(value = "/subject/{id}")
    public SubjectDto getSubject(@PathVariable long id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping(value = "/subject/all")
    public List<SubjectDto> getSubject() {
        return subjectService.findAll();
    }

    /**
     * Добавляет новую преподаваемую дисциплину.
     *
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @PostMapping(value = "/subject/add")
    public void addSubject(@RequestBody AddSubjectDto addSubjectDto) {
        subjectService.saveSubject(addSubjectDto);
    }

    /**
     * Изменяет данную преподаваемую дисциплину.
     *
     * @param id идентификатор.
     * @param addSubjectDto преподаваемая дисциплина.
     */
    @PutMapping(value = "/subject/update/{id}")
    public void updateSubject(@PathVariable long id, @RequestBody AddSubjectDto addSubjectDto) {
        subjectService.updateSubject(id, addSubjectDto);
    }

    /**
     * Удаляет преподаваемую дисциплину.
     *
     * @param id идентификатор.
     */
    @DeleteMapping(value = "/subject/delete/{id}")
    public void deleteSubject(@PathVariable long id) {
        subjectService.deleteSubject(id);
    }
}
