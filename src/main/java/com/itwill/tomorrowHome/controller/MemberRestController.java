package com.itwill.tomorrowHome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Member;
import com.itwill.tomorrowHome.service.MemberService;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService memberService;

	/*
	 * 멤버 로그인
	 */
	@PostMapping("/member_login_rest")
	public Map<String, Object> member_login_rest(HttpSession session, HttpServletResponse response, String id, String password, String idRemember) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int result = memberService.loginMember(id, password);
			if (result > 0) {
				Member loginMember = memberService.findMember(id);
				session.setAttribute("sM_id", loginMember.getM_id());
				session.setAttribute("sMember", loginMember);
				// id기억하기
				Cookie idCookie = null;
				if (idRemember != null && idRemember.equals("on")) {
					idCookie = new Cookie("id", id);
					idCookie.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(idCookie);
				} else {
					idCookie = new Cookie("id", null); 
					idCookie.setMaxAge(0);
					response.addCookie(idCookie);
				}
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "로그인 성공");
			} else {
				if (result == -1) {
					resultMap.put("errorCode", -1);
					resultMap.put("errorMsg", "비밀번호가 일치하지 않습니다");
				} else if (result == -2) {
					resultMap.put("errorCode", -2);
					resultMap.put("errorMsg", "존재하지 않는 ID입니다");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -3);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 회원가입
	 */
	@RequestMapping("member_register_rest")
	public Map<String, Object> member_register_rest(@ModelAttribute("fMember") Member member) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int result = memberService.createMember(member);
			if (result == -1) {
				resultMap.put("errorCode", -1);
				resultMap.put("errorMsg", "이미 존재하는 ID입니다");
			} else if (result == 1) {
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", member.getM_id() + "님, 회원가입을 환영합니다 😊");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 회원정보수정
	 */
	@LoginCheck
	@RequestMapping("member_account_update_rest")
	public Map<String, Object> member_account_update_rest(@ModelAttribute Member member, String new_password, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		Member sMember = (Member)session.getAttribute("sMember");
		if(!member.getM_password().equals(sMember.getM_password())) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "비밀번호가 일치하지 않습니다");
			return resultMap;
		}
		try {
			if (!new_password.equals("")) {
				member.setM_password(new_password);
			} 
			memberService.updateMember(member);
			session.setAttribute("sMember", member);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "정보가 변경되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 회원 탈퇴
	 */
	@LoginCheck
	@RequestMapping("member_withdrawal_rest")
	public Map<String, Object> member_withdrawal_rest(String id, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			memberService.deleteMember(id);
			session.invalidate();
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "탈퇴가 완료되었습니다.\n" + id + "님 그동안 감사합니다 😊");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

}
