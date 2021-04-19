package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.repository.student.StudentPostRepository;
import com.nakaradasava.learntogether.service.student.StudentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class StudentPostController {
    private StudentPostService studentPostService;

    @Autowired
    public StudentPostController(StudentPostService studentPostService) {
        this.studentPostService = studentPostService;
    }

    /**
     * Trim all white spaces for user inputs
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/student/post")
    public String saveStudentPost(@Valid @ModelAttribute(name = "studentPostObj") StudentPost studentPost,
                                  BindingResult bindingResult,
                                  @AuthenticationPrincipal Student student,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorPost", "You must write something...");
            return "redirect:/";
        }

        if (null != studentPost.getId()) {
            studentPost.setEdited(true);
        }

        studentPost.setStudent(student);

        studentPostService.save(studentPost);

        redirectAttributes.addFlashAttribute("successPost", "Your post is published");

        return "redirect:/";
    }
}