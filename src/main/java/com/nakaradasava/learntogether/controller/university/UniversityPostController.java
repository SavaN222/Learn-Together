package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.service.university.UniversityPostService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UniversityPostController {

    private UniversityPostService universityPostService;
    private UniversityService universityService;

    @Autowired
    public UniversityPostController(UniversityPostService universityPostService, UniversityService universityService) {
        this.universityPostService = universityPostService;
        this.universityService = universityService;
    }

    @PostMapping("/university/post/{universityId}")
    public String savePost(@PathVariable int universityId,
                         @ModelAttribute(name = "post") UniversityPost universityPost,
                         @AuthenticationPrincipal Student student,
                         RedirectAttributes redirectAttributes) {

        University university = universityService.findUniversity(universityId);

        universityPost.setStudent(student);
        universityPost.setUniversity(university);

        if (null != universityPost.getId()) {
            universityPost.setEdited(true);
        }

        universityPostService.savePost(universityPost);

        redirectAttributes.addFlashAttribute("postMsg", "UniversityPost Created");

        return "redirect:/university/" + universityId;
    }

    @DeleteMapping("/university/post/delete/{postId}")
    public String deletePostById(@PathVariable int postId,
                                 @RequestParam int universityId,
                                 RedirectAttributes redirectAttributes) {

        universityPostService.deletePostById(postId);

        redirectAttributes.addFlashAttribute("deletePostMsg", "UniversityPost deleted!");

        return "redirect:/university/" + universityId;
    }

    @GetMapping("/university/post/edit/{postId}")
    public String showEditForm(@PathVariable int postId,
                               Model model,
                               @AuthenticationPrincipal Student student) {

        UniversityPost universityPost = universityPostService.findPostById(postId);

        if (!student.getId().equals(universityPost.getStudent().getId())) {
            return "redirect:/university/" + universityPost.getUniversity().getId();
        }

        model.addAttribute("post", universityPost);

        return "university/edit-post";
    }

    @GetMapping("/university/post/{postId}")
    public String showSinglePost(@PathVariable int postId,
                                 Model model,
                                 @AuthenticationPrincipal Student student) {

        UniversityPost universityPost = universityPostService.findPostById(postId);

        model.addAttribute("post", universityPost);
        model.addAttribute("commentObj", new UniversityPostComment());

        return "university/single-post";
    }
}
