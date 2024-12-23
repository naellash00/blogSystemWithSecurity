package com.example.blogsystemsecurity.Controller;

import com.example.blogsystemsecurity.Api.ApiResponse;
import com.example.blogsystemsecurity.Model.Blog;
import com.example.blogsystemsecurity.Model.MyUser;
import com.example.blogsystemsecurity.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog){
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog){
        blogService.updateBlog(myUser.getId(),blog_id, blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blog_id){
        blogService.deleteBlog(myUser.getId(), blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));
    }

    //endpoint: get all user blogs
    @GetMapping("/get/user-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser){
//        blogService.getMyBlogs(myUser.getId());
        return ResponseEntity.status(200).body(blogService.getMyBlogs(myUser.getId()));
    }

    //endpoint: get blog by id
    @GetMapping("/get-blog/by-id/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer blog_id){
//        blogService.getBlogById(blog_id);
        return ResponseEntity.status(200).body(blogService.getBlogById(myUser.getId(), blog_id));
    }

    @GetMapping("/get-blog/by-title/{blog_title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal MyUser myUser, @PathVariable String blog_title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(myUser.getId(), blog_title));
    }

}
