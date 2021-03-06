package com.lenovo.itac.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lenovo.itac.dao.GGYRDao;
import com.lenovo.itac.entity.GGYREntity;
import com.lenovo.itac.service.GGYRService;
import com.lenovo.itac.util.CommonUtils;

@Service
public class GGYRServiceImpl implements GGYRService{

	private static final int BATCH_SIZE = 50;
	@Autowired
	private GGYRDao ggyrDao;
	
	@Override
	public int getTotalCount(String[] mos) {
		List<String> moList = null;
		
		if (null != mos) {
			moList = Lists.newArrayList();
			for (String mo : mos) {
				String temp = mo.trim();
				if (CommonUtils.validateMO(temp)) {
					moList.add(temp);
				}
			}
		}
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("mos", moList);
		
		return ggyrDao.getTotalCount(params);
	}
	
	@Override
	public List<GGYREntity> queryByMOs(int page, int rows, String[] mos, String sort, String order) {
		// TODO Auto-generated method stub
		List<String> moList = null;
		
		if (null != mos) {
			moList = Lists.newArrayList();
			for (String mo : mos) {
				String temp = mo.trim();
				if (CommonUtils.validateMO(temp)) {
					moList.add(temp);
				}
			}
		}
		if (sort == null || order == null) {
			sort = "stamp";
			order = "desc";
		} else if (sort.equals("mo")) {
			sort = "WORKORDER_NUMBER";
		}
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("page", page);
		params.put("rows", rows);
		params.put("mos", moList);
		params.put("sort", sort);
		params.put("order", order);
		
		return ggyrDao.queryByPage(params);
	}
	
	@Override
	public List<GGYREntity> queryByMos(String[] mos) {
		List<GGYREntity> result = Lists.newArrayList();
		
		List<String> moList = Lists.newArrayList();
		int count = 0;
		for (String mo : mos) {
			String temp = mo.trim();
			if (CommonUtils.validateMO(temp)) {
				moList.add(temp);
				count++;
			}
			if (count == BATCH_SIZE) {
				List<GGYREntity> batchList = ggyrDao.queryByMos(moList);
				if (batchList != null && batchList.size() > 0) {
					result.addAll(batchList);
					moList.clear();
					count = 0;
				}
			}
		}
		if (count != 0) {
			List<GGYREntity> batchList = ggyrDao.queryByMos(moList);
			if (batchList != null && batchList.size() > 0) {
				result.addAll(batchList);
				moList.clear();
				count = 0;
			}
		}
		
		return result;
	}

	@Override
	public List<String> notExists(String[] mos) {
		List<String> moList = null;
		
		if (null != mos) {
			Set<String> moSet = Sets.newHashSet();
			moList = Lists.newArrayList();
			for (String mo : mos) {
				String temp = mo.trim();
				if (CommonUtils.validateMO(temp)) {
					moList.add(temp);
					moSet.add(mo);
				}
			}
			List<String> queryResult = ggyrDao.queryByList(moList);
			for (String mo : queryResult) {
				if (moSet.contains(mo)) {
					moSet.remove(mo);
				}
			}
			List<String> remains = Lists.newArrayList();
			Iterator<String> it = moSet.iterator();
			while(it.hasNext()) {
				remains.add(it.next());
			}
			return remains;
		}
		
		return null;
	}
}
