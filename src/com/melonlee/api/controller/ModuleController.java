package com.melonlee.api.controller;import java.util.ArrayList;import javax.annotation.Resource;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.servlet.ModelAndView;import com.melonlee.api.bean.ModuleBean;import com.melonlee.api.dao.MainDao;import com.melonlee.api.utils.DateFormatUtils;@Controllerpublic class ModuleController {	@Resource	private MainDao mainDao;	@RequestMapping(value = "saveModule", method = RequestMethod.POST)	public ModelAndView save(			@RequestParam(value = "id", required = false) int id,			@RequestParam(value = "pID", required = false) int pID,			@RequestParam(value = "title", required = false) String title,			@RequestParam(value = "baseUrl", required = false) String baseUrl,			@RequestParam(value = "descStr", required = false) String descStr) {				if(id>0){		    mainDao.updateModule(id,DateFormatUtils.enCoding(title),DateFormatUtils.enCoding(baseUrl),DateFormatUtils.enCoding(descStr));		}else{		    mainDao.createModule(pID,DateFormatUtils.enCoding(title),DateFormatUtils.enCoding(baseUrl),DateFormatUtils.enCoding(descStr));		}		return modules(pID);	}			@RequestMapping(value = "modules", method = {RequestMethod.GET,RequestMethod.POST})	public ModelAndView modules(@RequestParam(value = "pID", required = false) int pID) {		ArrayList<ModuleBean> modules = mainDao.getModules(pID);				ModelMap model = new ModelMap();				model.addAttribute("modules", modules);				model.addAttribute("proID", pID);				return new ModelAndView("modules.jsp",model);	}		@RequestMapping(value = "getModule", method = RequestMethod.GET)	public ModelAndView getPro(			@RequestParam(value = "id", required = false) int id) {				ModuleBean module = mainDao.getModule(id);		ModelMap model = new ModelMap();		model.addAttribute("module", module);		return new ModelAndView("createModule.jsp", model);	}	}