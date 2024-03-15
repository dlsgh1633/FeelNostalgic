package com.shop.repository;

import com.shop.entity.Board;
import com.shop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
        List<Comment> findAllByboardOrderById(Board board);
        Optional<Comment> findById(Long id);
}
