package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.QnaDao;
import com.itwill.tomorrowHome.domain.Qna;
import com.itwill.tomorrowHome.util.PageMaker;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaDao qnaDao;

	/*
	 * 새로운 게시물을 추가
	 */
	@Override
	public int insertNewQna(Qna qna) throws Exception {
		return qnaDao.insertNewQna(qna);
	}

	/*
	 * 게시물 번호에 해당하는 게시물 정보를 반환
	 */
	@Override
	public Qna selectQna(int q_no, String workType) throws Exception {
		Qna qna = qnaDao.selectQna(q_no);
		if(workType.equals("view")) {
			qna.setQ_content(qna.getQ_content().replace("\n", "<br/>"));
		}else if(workType.equals("modifyForm")) {
			qna.setQ_content(qna.getQ_content().replace("\n", ">>").trim());
		}
		return qna;
	}

	/*
	 * 답글 게시물을 추가
	 */
	@Override
	public int insertReply(Qna qna) throws Exception {
		return qnaDao.insertReply(qna);
	}

	/*
	 * 게시물 리스트를 반환
	 */
	@Override
	public PageMakerDto<Qna> selectQnaList(int currentPage) throws Exception {
		// 1.전체글의 갯수
		int totRecordCount = qnaDao.selectQnaCount(); 
		// 2.paging계산(PageMaker 유틸클래스) 
		PageMaker pageMaker = new PageMaker(totRecordCount, currentPage, 10, 10);
		// 3.게시물데이타 얻기
		List<Qna> qnaList = qnaDao.selectQnaList(pageMaker.getPageBegin(), pageMaker.getPageEnd());
		PageMakerDto<Qna> pageMakerQnaList = new PageMakerDto<Qna>(qnaList, pageMaker, totRecordCount);
		// 게시글 제목 수정
		for(Qna qna : pageMakerQnaList.getItemList()) {
			getTitleString(qna);
		}
		return pageMakerQnaList;
	}

	/*
	 * 게시물을 삭제
	 */
	@Override
	public int deleteQna(int q_no) throws Exception {
		return qnaDao.deleteQna(q_no);
	}

	/*
	 * 게시물내용을 수정
	 */
	@Override
	public int updateQna(Qna qna) throws Exception {
		return qnaDao.updateQna(qna);
	}

	/*
	 * 게시물 조회수를 1 증가
	 */
	@Override
	public void updateReadCount(int q_no) throws Exception {
		qnaDao.updateReadCount(q_no);
	}

	/*
	 * 게시물 총 건수를 조회, 반환
	 */
	@Override
	public int selectQnaCount() throws Exception {
		return qnaDao.selectQnaCount();
	}

	/*
	 * 게시글 제목을 수정해 반환하는 메서드
	 */
	public Qna getTitleString(Qna qna) {
		StringBuilder title = new StringBuilder(128);
		String t = qna.getQ_title();
		if (t.length() > 15) {
			// t = t.substring(0,15);
			// t = t+"...";
			t = String.format("%s...", t.substring(0, 15));
		}
		// 답글공백삽입
		for (int i = 0; i < qna.getQ_depth(); i++) {
			title.append("&nbsp;&nbsp;");
		}

		if (qna.getQ_depth() > 0) {
			title.append("<img border='0' src='img/etc/re.gif'/>&nbsp;");
		}

		title.append(t.replace(" ", "&nbsp;"));
		qna.setQ_title(title.toString());
		
		return qna;
	}

}
