package com.example.inside_task.repositories;

import com.example.inside_task.entity.User;
import com.example.inside_task.entity.UserMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserMessageRepository extends PagingAndSortingRepository<UserMessage,Long> {
    public Page<UserMessage> findAllByUserId(User userId,Pageable pageable);
}
