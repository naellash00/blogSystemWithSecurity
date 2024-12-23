package com.example.blogsystemsecurity.Service;

import com.example.blogsystemsecurity.Api.ApiException;
import com.example.blogsystemsecurity.Model.Blog;
import com.example.blogsystemsecurity.Model.MyUser;
import com.example.blogsystemsecurity.Repository.AuthRepository;
import com.example.blogsystemsecurity.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    public void addBlog(Integer user_id, Blog blog) {
        // check the user id
        MyUser myUser = authRepository.findMyUserById(user_id);
        blog.setUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id, Integer blog_id, Blog blog) {
        // check their ids
        MyUser myUser = authRepository.findMyUserById(user_id);
        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if (myUser == null) {
            throw new ApiException("User not found");
        }
        if (oldBlog == null) {
            throw new ApiException("blog not found");
        }
        // check this blog user is the same user here
        if (!(oldBlog.getUser().getId().equals(myUser.getId()))) {
            throw new ApiException("This user cannot update this blog");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer user_id, Integer blog_id) {
        MyUser myUser = authRepository.findMyUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (myUser == null) {
            throw new ApiException("User not found");
        }
        if (blog == null) {
            throw new ApiException("blog not found");
        }
        if (!blog.getUser().getId().equals(myUser.getId())) {
            throw new ApiException("this user cannot delete this blog");
        }
        blogRepository.delete(blog);
    }

    //endpoint: get all user blogs
    public List<Blog> getMyBlogs(Integer user_id) {
        MyUser myUser = authRepository.findMyUserById(user_id);
        if (myUser == null) {
            throw new ApiException("user not found");
        }
        return blogRepository.findAllByUser(myUser);
    }

    //endpoint: get blog by id
    public Blog getBlogById(Integer user_id, Integer blog_id) {
        MyUser myUser = authRepository.findMyUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (myUser == null) {
            throw new ApiException("User not found");
        }
        if (blog == null) {
            throw new ApiException("blog not found");
        }
        return blog;
    }

    //endpoint: get blog by title
    public Blog getBlogByTitle(Integer user_id, String blog_title) {
        MyUser myUser = authRepository.findMyUserById(user_id);
        Blog blog = blogRepository.findBlogByTitle(blog_title);
        if (myUser == null) {
            throw new ApiException("User not found");
        }
        if (blog == null) {
            throw new ApiException("blog not found");
        }
        return blog;
    }

}
