package com.lenovo.itac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.lenovo.itac.entity.SnInfoEntity;
import com.lenovo.itac.http.response.ResponseEntity;
import com.lenovo.itac.model.MoInfoModel;
import com.lenovo.itac.model.SnInfoModel;
import com.lenovo.itac.service.MoInfoService;
import com.lenovo.itac.util.CommonUtils;

@RestController
@RequestMapping(value="/mo", method=RequestMethod.POST)
public class MoInfoController {
	
	@Autowired
	private MoInfoService moInfoService;

	@RequestMapping(value="/querymo", method=RequestMethod.POST)
	public ResponseEntity queryMos(HttpServletRequest request) {
		ResponseEntity response = new ResponseEntity();
		
		String mos = request.getParameter("mos");
		if (StringUtils.isEmpty(mos)) {
			response.setCode(ResponseEntity.CODE_INFO_MO_EMPTY);
		} else {
			String[] arr = mos.split(CommonUtils.CHARACTER_NEW_LINE);
			List<MoInfoModel> entities = moInfoService.queryMoInfoByMos(arr);
			
			response.setData(entities);
			
			response.setTotal(entities.size());
		}
		
		return response;
	}
	
	@RequestMapping(value="/querysn", method=RequestMethod.POST)
	public ResponseEntity querySns(HttpServletRequest request) {
		ResponseEntity response = new ResponseEntity();
		
		String mos = request.getParameter("mos");
		if (StringUtils.isEmpty(mos)) {
			response.setCode(ResponseEntity.CODE_INFO_MO_EMPTY);
		} else {
			String[] arr = mos.split(CommonUtils.CHARACTER_NEW_LINE);
			List<SnInfoEntity> entities = moInfoService.querySnInfoByMos(arr);
			List<SnInfoModel> models = convert(entities);
			
			response.setData(models);
			response.setTotal(models.size());
		}
		
		return response;
	}
	
	public List<SnInfoModel> convert(List<SnInfoEntity> entities) {
		List<SnInfoModel> models = Lists.newArrayList();
		
		if (null != entities) {
			for (SnInfoEntity e : entities) {
				SnInfoModel m = new SnInfoModel();
				m.setMo(e.getMo());
				m.setSn(e.getSn());
				m.setWorkStep(e.getWorkStep());
				m.setLastWsTime(CommonUtils.format(e.getLastWsTime()));
				models.add(m);
			}
		}
		
		return models;
	}
}