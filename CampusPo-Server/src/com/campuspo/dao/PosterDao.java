package com.campuspo.dao;

import java.util.List;

public interface PosterDao {
	//��ȡstartPosition������¼�ķ���ʱ����ǰ��25����¼��������������
	public List getPosterList(int startPosition , final int length);
	
}
