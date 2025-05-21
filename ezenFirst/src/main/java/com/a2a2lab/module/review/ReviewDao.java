package com.a2a2lab.module.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface ReviewDao {
	
	public int countReviewsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 리뷰 개수 검색
	public List<ReviewDto> findReviewsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 리뷰 검색
	public int countAllReview();
	public int softDeleteReview(String reviewId);
	public int hardDeleteReview(String reviewId);
	public List<ReviewDto> getReviewListByProductId(String productId);
	public int saveReview(ReviewDto dto);
	public int findAvgRatingByProductId(String productId);

}
