package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.Status;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.repository.FriendRepository;
import com.nakaradasava.learntogether.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FriendService {

    private FriendRepository friendRepository;
    private StudentService studentService;

    @Autowired
    public FriendService(FriendRepository friendRepository, StudentService studentService) {
        this.friendRepository = friendRepository;
        this.studentService = studentService;
    }

    public Optional<Friend> findByStudentLowerAndStudentHigher(Student lowerStudent, Student higherStudent) {
        return friendRepository.findByStudentLowerAndStudentHigher(lowerStudent, higherStudent);
    }


    public void save(Student lowerStudent, Student higherStudent, Student sender, Friend friend) {
        friend.setStudentLower(lowerStudent);
        friend.setStudentHigher(higherStudent);
        friend.setStatus(Status.PENDING);
        friend.setActionUser(sender);

        friendRepository.save(friend);
    }

    public void update(Friend friend) {
        friendRepository.save(friend);
    }

    public void delete(Friend friend) {
        friendRepository.delete(friend);
    }

    /**
     * Swap student id's, always first save lower id than higher, speed up query!
     * @param senderId student1 id
     * @param recipientId student2 id
     * @return map with lower and higher student
     */
    public Map<String, Student> swapStudent(int senderId, int recipientId) {

        int lowerId, higherId;

        if (senderId > recipientId) {
            lowerId = recipientId;
            higherId = senderId;
        } else {
            lowerId = senderId;
            higherId = recipientId;
        }

        Map<String, Student> students = new HashMap<>();

        students.put("lowerStudent", studentService.findStudentById(lowerId).get());
        students.put("higherStudent", studentService.findStudentById(higherId).get());

        return students;
    }
}