package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.Status;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    private FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
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
}
