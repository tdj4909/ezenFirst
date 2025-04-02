package com.a2a2lab.module.review;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
	
	public int uelete(ReviewDto dto) {
		return dao.uelete(dto);
	}
	
	public int delete(ReviewDto dto) {
		return dao.delete(dto);
	}

	public int selectOneCount(ReviewVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public List<ReviewDto> selectList(ReviewVo vo) {
		return dao.selectList(vo);
	}

}
