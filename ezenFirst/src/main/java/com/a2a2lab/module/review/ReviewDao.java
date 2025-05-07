package com.a2a2lab.module.review;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Repository
public interface ReviewDao {
	
	public int insert(ReviewDto dto);
	public int uelete(ReviewDto dto);
	public int delete(ReviewDto dto);
	public int selectOneCount(ReviewVo vo);
	public int findAvgRatingByMenuSeq(String menu_seq);
	public int reviewCount();
	public List<ReviewDto> selectList(ReviewVo vo);
	public List<ReviewDto> getReviewListByMenuSeq(String menu_seq);

}
