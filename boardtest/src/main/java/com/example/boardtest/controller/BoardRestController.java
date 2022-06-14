package com.example.boardtest.controller;

import com.example.boardtest.dto.BoardDto;
import com.example.boardtest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {
    @Autowired
    private BoardService boardService;

    @GetMapping("api/post/{no}")
    public BoardDto detail(@PathVariable("no") Long no) {
        BoardDto boardDto = boardService.getPost(no);
        return boardDto;
    }

    @GetMapping("api/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @PutMapping("api/post/edit/{no}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("api/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);
        return "redirect:/";
    }

    @RequestMapping(value="/api/post/list", method=RequestMethod.GET)
    public List<BoardDto> list() {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        return boardDtoList;
    }

    @GetMapping("api/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("api/post")
    public void write(BoardDto boardDto) {
        boardService.savePost(boardDto);
    }
}
