package com.a2a2lab.module.review;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
	
	public int countReviewsByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countReviewsByVo(pageVo, searchVo);
	}
	
	public List<ReviewDto> findReviewsByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findReviewsByVo(pageVo, searchVo);
	}
	
	public int softDeleteReview(String id) {
		return dao.softDeleteReview(id);
	}
	
	public int hardDeleteReview(String id) {
		return dao.hardDeleteReview(id);
	}
	
	
//	public int insert(ReviewDto dto) {
//		return dao.insert(dto);
//	}
//	
//	public int uelete(ReviewDto dto) {
//		return dao.uelete(dto);
//	}
//	
//	public int delete(ReviewDto dto) {
//		return dao.delete(dto);
//	}
//
//	public int selectOneCount(ReviewVo vo) {
//		return dao.selectOneCount(vo);
//	}
//	
//	public int findAvgRatingByMenuSeq(String menu_seq) {
//		return dao.findAvgRatingByMenuSeq(menu_seq);
//	}
//	
//	public int reviewCount() {
//		return dao.reviewCount();
//	}
//	
//	public List<ReviewDto> selectList(ReviewVo vo) {
//		return dao.selectList(vo);
//	}
//	
//	public List<ReviewDto> getReviewListByMenuSeq(String menu_seq) {
//		return dao.getReviewListByMenuSeq(menu_seq);
//	}

}
