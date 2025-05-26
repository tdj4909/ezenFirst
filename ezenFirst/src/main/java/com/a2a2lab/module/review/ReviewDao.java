package com.a2a2lab.module.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface ReviewDao {
	
	// 관리자 리뷰 관리
	public int countReviewsByVo(@Param("searchVo") SearchVo searchVo);
	public List<ReviewDto> findReviewsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);

	// 리뷰 통계
	public int countAllReview();
	public int findAvgRatingByProductId(String productId);
	
	// Select를 제외한 CRUD
	public int saveReview(ReviewDto dto);
	public int softDeleteReview(String reviewId);
	public int softDeleteReviewByMemberId(String memberId);
	public int softDeleteReviewByProductId(String productId);
	public int hardDeleteReview(String reviewId);
	
	// 캐싱
	public List<ReviewDto> getReviewListByProductId(String productId);

}
