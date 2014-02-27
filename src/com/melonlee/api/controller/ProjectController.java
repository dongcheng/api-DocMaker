package com.melonlee.api.controller;import javax.annotation.Resource;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.servlet.ModelAndView;import com.melonlee.api.dao.MainDao;@Controllerpublic class ProjectController {	@Resource	private MainDao mainDao;		/*	 * 根据shopID获取店主推荐	 */	@RequestMapping(value = "savePro", method = RequestMethod.POST)	public ModelAndView save(@RequestParam(value = "title", required = false) String title) {				Integer proID = mainDao.createPro(title);				ModelMap model = new ModelMap();				model.addAttribute("proID", proID);				model.addAttribute("proName", title);				return new ModelAndView("createModule.jsp",model);	}	}