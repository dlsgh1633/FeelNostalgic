package com.shop.repository;

import com.shop.entity.Board;
import com.shop.entity.Comment;
import com.shop.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findAllByparentIdOrderById(Comment parentId);
    Optional<Reply> findById(Long id);
}
