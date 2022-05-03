package com.itwill.tomorrowHome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.domain.Qna;
import com.itwill.tomorrowHome.service.QnaService;
import com.itwill.tomorrowHome.util.PageMakerDto;

@RestController
public class QnaRestController {
	@Autowired
	private QnaService qnaService;

	/*
	 * 게시글 리스트 반환 (REST)
	 */
	@RequestMapping("/qna_list_rest")
	public Map<String, Object> qna_list_rest(@RequestParam(required = false, defaultValue = "1") Integer pageno,
			String search_type, String search_value) {
		Map<String, Object> resultMap = new HashMap<>();
		PageMakerDto<Qna> qnaList = null;
		try {
			qnaList = qnaService.selectQnaList(pageno, search_type, search_value);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", qnaList);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 게시글 삭제 (REST)
	 */
	@RequestMapping("/qna_delete_rest")
	public Map<String, Object> qna_delete_rest(Integer pageno, Integer q_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if (pageno == null || q_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			int result = qnaService.deleteQna(q_no);
			if (result == 1) {
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "게시글을 삭제하였습니다");
			} else {
				resultMap.put("errorCode", -2);
				resultMap.put("errorMsg", "게시글이 삭제되지 않았습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -3);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

}
