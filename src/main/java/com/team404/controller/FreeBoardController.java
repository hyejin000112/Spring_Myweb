package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;

@Controller
@RequestMapping("freeBoard")
public class FreeBoardController {
	/*
	 * 화면처리 -> 테이블생성 -> 등록처리 
	 * */
	
	@Autowired
	@Qualifier("freeBoardService")
	private FreeBoardService freeBoardService;
	//목록화면
	@RequestMapping("/freeList")
	public String freeList(Model model) {
		ArrayList<FreeBoardVO> list = freeBoardService.getList();
		model.addAttribute("boardList",list);
		return "freeBoard/freeList";
	}
	
	//등록화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
	//수정화면
	@RequestMapping("/freeModify")
	public String freeModify() {
		return "freeBoard/freeModify";
	}
	
	//상세화면
	@RequestMapping("/freeDetail")
	public String freeDetail(@RequestParam("bno")int bno,Model model) {
		model.addAttribute("detail",freeBoardService.getContent(bno));
		
		return "freeBoard/freeDetail";
	}
	
	//등록처리
	@RequestMapping(value="/registForm",method=RequestMethod.POST)
	public String registForm(FreeBoardVO vo,RedirectAttributes RA) {
		freeBoardService.regist(vo);
		
		//등록성공 여부(msg) - 1회성 사용 가느,ㅇ.
		RA.addFlashAttribute("msg","정상 등록 처리되었습ㄴ;ㅣ다,");
		return "redirect:/freeBoard/freeList";
		
	}
	
	
	
	
}
