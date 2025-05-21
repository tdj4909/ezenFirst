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
	
	public int countAllReview() {
		return dao.countAllReview();
	}
	
	public int softDeleteReview(String id) {
		return dao.softDeleteReview(id);
	}
	
	public int hardDeleteReview(String id) {
		return dao.hardDeleteReview(id);
	}
	
	public List<ReviewDto> getReviewListByProductId(String productId) {
		return dao.getReviewListByProductId(productId);
	}
	
	public int saveReview(ReviewDto dto) {
		return dao.saveReview(dto);
	}
	
	public int findAvgRatingByProductId(String productId) {
		return dao.findAvgRatingByProductId(productId);
	}

}
