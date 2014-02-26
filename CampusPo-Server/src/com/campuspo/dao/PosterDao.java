package com.campuspo.dao;

import java.util.List;

public interface PosterDao {
	//获取startPosition这条记录的发布时间以前的25条记录（包括他自身）。
	public List getPosterList(int startPosition , final int length);
	
}
