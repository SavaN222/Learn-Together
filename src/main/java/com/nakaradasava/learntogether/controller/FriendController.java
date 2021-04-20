package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.Status;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.FriendService;
import com.nakaradasava.learntogether.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class FriendController {

    private FriendService friendService;
    private StudentService studentService;

    @Autowired
    public FriendController(FriendService friendService, StudentService studentService) {
        this.friendService = friendService;
        this.studentService = studentService;
    }

    @PostMapping("/friend/add")
    public String addFriend(@ModelAttribute("friend") Friend friend,
                            @RequestParam(name = "recipient") int recipientId,
                            @AuthenticationPrincipal Student sender,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        int lowerId, higherId;

        if (sender.getId() > recipientId) {
            lowerId = recipientId;
            higherId = sender.getId();
        } else {
            lowerId = sender.getId();
            higherId = recipientId;
        }

        Student lowerStudent = studentService.findStudentById(lowerId).get();
        Student higherStudent = studentService.findStudentById(higherId).get();
        Optional<Friend> requestExist = friendService.findByStudentLowerAndStudentHigher(lowerStudent, higherStudent);

        if (requestExist.isPresent()) {
            model.addAttribute("status", "ERROR");
            return "redirect:/profile/" + recipientId;
        }

        friendService.save(lowerStudent, higherStudent, sender, friend);

        return "redirect:/profile/" + recipientId;
    }

    @PostMapping("/friend/response")
    public String requestResponse(@ModelAttribute(name = "friendObj") Friend friendObj,
                                  @RequestParam(name = "response") String response,
                                  RedirectAttributes redirectAttributes) {

        if ("YES".equals(response)) {
            friendObj.setStatus(Status.ACCEPT);

            redirectAttributes.addFlashAttribute("response", "Congratulations you have a new friend");

            friendService.update(friendObj);
        } else {
            redirectAttributes.addFlashAttribute("response", "Friend request reject");

            friendService.delete(friendObj);
        }
        return "redirect:/profile/" + friendObj.getActionUser().getId();
    }

    @DeleteMapping("/friend/delete/{profileId}")
    public String deleteFriend(@PathVariable int profileId,
                               @AuthenticationPrincipal Student student) {

        Student profileStudent = studentService.findStudentById(profileId).get();

        int lowerId, higherId;

        if (student.getId() > profileStudent.getId()) {
            lowerId = profileStudent.getId();
            higherId = student.getId();
        } else {
            lowerId = student.getId();
            higherId = profileStudent.getId();
        }

        Student lowerStudent = studentService.findStudentById(lowerId).get();
        Student higherStudent = studentService.findStudentById(higherId).get();
        Optional<Friend> friend = friendService.findByStudentLowerAndStudentHigher(lowerStudent, higherStudent);

        friendService.delete(friend.get());

        return "redirect:/profile/" + profileId;

    }


}
