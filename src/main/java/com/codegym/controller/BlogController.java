package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Comment;
import com.codegym.model.Genre;
import com.codegym.service.IAppUserService;
import com.codegym.service.IBlogService;
import com.codegym.service.ICommentService;
import com.codegym.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    IBlogService iBlogService;
    @Autowired
    IGenreService iGenreService;
    @Autowired
    ICommentService iCommentService;
    @Autowired
    IAppUserService iAppUserService;



    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/home")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView blog() {
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        modelAndView.addObject("listUser", iAppUserService.findAll());
        modelAndView.addObject("listComment", iCommentService.findAll());
        return modelAndView;
    }


    @GetMapping("/userHome")
    public ModelAndView blogHome() {
        ModelAndView modelAndView = new ModelAndView("/userHome");
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        modelAndView.addObject("listUser", iAppUserService.findAll());
        modelAndView.addObject("listComment", iCommentService.findAll());
        return modelAndView;
    }

    @GetMapping("/easyMath")
    public ModelAndView easyMath() {
        return new ModelAndView("/easyMath");
    }
    @GetMapping("/kiemtra")
    public ModelAndView kiemtra() {
        return new ModelAndView("/kiemtra");
    }


    @ModelAttribute
    public ArrayList<Genre> listGenre() {
        return (ArrayList<Genre>) iGenreService.findAll();
    }


    @GetMapping
    public ArrayList<Blog> showBlogs() {
        return (ArrayList<Blog>) iBlogService.findAll();
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(iBlogService.save(blog), HttpStatus.CREATED);
    }


    //xoá
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id) {
        Optional<Blog> blogOptional = iBlogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBlogService.remove(id);
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        iBlogService.remove(id);
        return new ModelAndView("redirect:/api/blogs/home");
    }

    //edit
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit", "listGenre", iGenreService.findAll());
        modelAndView.addObject("list", iBlogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{index}")
    public ModelAndView edit(@ModelAttribute Blog blog) {
        iBlogService.save(blog);
        return new ModelAndView("redirect:/home");
    }


    @GetMapping("/home/{id}")
    public ModelAndView homeLike(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/home");
        iBlogService.likes(id);
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        return new ModelAndView("redirect:/api/blogs/home");
    }

    @GetMapping("/dislikes/{id}")
    public ModelAndView dislike(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/home");
        iBlogService.dislikes(id);
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        return new ModelAndView("redirect:/api/blogs/home");
    }


    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/show");
        iBlogService.show(id);
        modelAndView.addObject("list", iBlogService.findById(id).get());
        return modelAndView;
    }

//    @GetMapping("/comments/{idBlog}")
//    public ResponseEntity<List<Comment>> findCommentByBlogId(@PathVariable long idBlog) {
//        return new ResponseEntity<>(iCommentService.findCommentByIdBlog(idBlog), HttpStatus.OK);
//    }

    @GetMapping("/comment/{id}")
    public ModelAndView findAllCommentByIdBlog(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("/showComment");
        modelAndView.addObject("listComment", iCommentService.findCommentByIdBlog(id));
        return modelAndView;
    }


    @PostMapping("/createComment")
    public ModelAndView create(@ModelAttribute Comment comment){
        iCommentService.save(comment);
        ModelAndView modelAndView = new ModelAndView("redirect:/api/blogs/user");
        return modelAndView;
    }



    //test like,unlike trong blog
    @GetMapping("/likeblog/{id}")
    public ModelAndView likeBlog(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/home");
        iBlogService.likes(id);
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        return new ModelAndView("redirect:/api/blogs/user");
    }

    @GetMapping("/unlikeblog/{id}")
    public ModelAndView unlikeBlog(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/home");
        iBlogService.dislikes(id);
        modelAndView.addObject("listGenre", new Blog());
        modelAndView.addObject("list", iBlogService.findAll());
        return new ModelAndView("redirect:/api/blogs/user");
    }


    //test like,unlike = ajax trong blog
    @DeleteMapping("/deleteBlog{id}")
    public ResponseEntity deleteblog(@PathVariable(name = "id") long id) {
        iBlogService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("increaseLike{id}")
    public ResponseEntity<Blog> increaseLikeByBlog (@PathVariable(name = "id") long id){
        Blog blog = iBlogService.findById(id).get();
        blog.setLikes(blog.getLikes()+1);
        iBlogService.save(blog);
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

    @PutMapping("increaseUnLike{id}")
    public ResponseEntity<Blog> increaseViewByBlog (@PathVariable(name = "id") long id){
        Blog blog = iBlogService.findById(id).get();
        blog.setDislike(blog.getDislike()+1);
        iBlogService.save(blog);
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }
}
