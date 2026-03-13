package com.aloha.post.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.aloha.post.dto.Page;
import com.aloha.post.dto.Post;
import com.aloha.post.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/Post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 메인화면
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 게시글 목록
    @GetMapping("/list")
    public String getList(Model model, Page page) throws Exception {
        // List<Post> list = postService.list();
        List<Post> list = postService.page(page);
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        log.info("*** list: {}", list);

        String pageUri = UriComponentsBuilder.fromPath("/Post/list")
                                            .queryParam("size", page.getSize())
                                            .queryParam("count", page.getCount())
                                            .build()
                                            .toUriString();
        model.addAttribute("pageUri", pageUri);
        
        return "Post/list";
    }
    
    // 게시글 조회 {no}
    @GetMapping("/read")
    public String select(@RequestParam("PostNo") Long no, Model model) throws Exception {
        Post post = postService.select(no);
        model.addAttribute("post", post);
        log.info("*** post: {}", post);
        return "Post/read";
    }

    // 게시글 등록
    @GetMapping("/insert")
    public String insert() {
        return "Post/insert";
    }
    @PostMapping("/insert")
    public String insert(Post post) throws Exception {
        boolean result = postService.insert(post);
        if ( result ) {
            log.info("*** insert result: {}", result);
            Long no = post.getNo();
            return "redirect:/Post/read?PostNo=" + no;
        }
        log.error("@@@@ insert result: {}", result);
        return "redirect:/Post/insert?error";
    }

    // 게시글 수정 {no}
    @GetMapping("/update")
    public String update(@RequestParam("PostNo") Long no, Model model) throws Exception {
        Post post = postService.select(no);
        model.addAttribute("post", post);
        log.info("***update post: {}", post);
        return "Post/update";
    }
    @PostMapping("/update")
    public String update(Post post) throws Exception {
        boolean result = postService.update(post);
        if (result) {
            log.info("*** update result: {}", result);
            Long no = post.getNo();
            return "redirect:/Post/read?PostNo=" + no;
        }        
        Long no = post.getNo();
        log.error("@@@@ update result: {}", result);
        return "redirect:/Post/update?no=" + no + "&error";
    }

    // 게시글 삭제 {no}
    @PostMapping("/delete")
    public String delete(@RequestParam("PostNo") Long no) throws Exception {
        boolean result = postService.delete(no);
        if (result) {
            log.info("*** delete result: {}", result);
            return "redirect:/Post/list";
        }
        log.error("@@@@ delete result: {}", result);
        return "redirect:/Post/read?PostNo=" + no + "&error";
    }
}
