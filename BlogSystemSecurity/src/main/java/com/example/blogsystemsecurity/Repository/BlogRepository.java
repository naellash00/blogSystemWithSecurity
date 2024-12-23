package com.example.blogsystemsecurity.Repository;

import com.example.blogsystemsecurity.Model.Blog;
import com.example.blogsystemsecurity.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);
    List<Blog> getBlogsByUserId(Integer user_id);
    List<Blog> findBlogByUserId(Integer user_id);
    List<Blog> findAllByUser(MyUser myUser);
    Blog findBlogByTitle(String title);
}
