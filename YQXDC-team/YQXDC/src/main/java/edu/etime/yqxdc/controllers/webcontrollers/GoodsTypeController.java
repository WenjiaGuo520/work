package edu.etime.yqxdc.controllers.webcontrollers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.etime.yqxdc.pojo.GoodsType;
import edu.etime.yqxdc.pojo.SysUser;
import edu.etime.yqxdc.services.interfaces.GoodsTypeService;
import edu.etime.yqxdc.tools.Pagers;

/**
 * @author 菜品类型管理controller
 */
@Controller
@RequestMapping("web/goodstype")
public class GoodsTypeController {
	@Autowired
	private GoodsTypeService service;

	/**
	 * 分页带条件查询
	 * 
	 * @param sysUser
	 * @param index
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView page(GoodsType goodstype, Integer index, Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		if (index == null || index < 1) {
			index = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		if (goodstype != null && goodstype.getGtname() != null && !goodstype.getGtname().equals("")) {
			mav.addObject("condition", goodstype.getGtname());
			goodstype.setGtname("%" + goodstype.getGtname() + "%");
		}
		Pagers<GoodsType> page = service.selectPages(goodstype, index, pageSize);
		mav.addObject("page", page);
		mav.setViewName("goodstype/list");
		// System.out.println(goodstype);
		return mav;
	}

	/*
	 * 进入到添加菜品信息页面
	 */
	@RequestMapping("/toadd")
	public String toadd() {
		return "goodstype/add";
	}

	@RequestMapping("/add")
	public String add(GoodsType gt, Model model) {
		if (gt.getGtname() == null || gt.getGtname().equals("")) {
			model.addAttribute("msg", "添加菜品类型名不能为空");
			return "goodstype/add";
		} else {
			gt.setGtname(gt.getGtname());
			List<GoodsType> gt1 = service.selectGoodsTypeList(gt);
			if (gt1.size() == 0) {
				gt.setGtid(UUID.randomUUID().toString());
				int rtn = service.add(gt);
				if (rtn > 0) {
					return "redirect:/web/goodstype/list";
				} else {
					model.addAttribute("msg", "添加菜品类型信息失败");
					return "goodstype/add";
				}
			} else {
				model.addAttribute("msg", "添加菜品类型已存在");
				return "goodstype/add";
			}
		}
	}

	@RequestMapping("/toedit/{gtid}")
	public String toedit(@PathVariable(value = "gtid") String gtid, Model model) {
		GoodsType gt = service.selectGoodsTypeById(gtid);
		model.addAttribute("goodstype", gt);
		return "goodstype/edit";
	}

	@RequestMapping("/edit")
	public String edit(GoodsType gt, Model model) {
		if (gt.getGtname() == null || gt.getGtname().equals("")) {
			model.addAttribute("msg", "菜品类型名不能为空");
			return "goodstype/edit";
		} else {
			int rtn = service.updateGoodsType(gt);
			if (rtn > 0) {
				return "redirect:/web/goodstype/list";
			} else {
				model.addAttribute("msg", "修改失败，请重试");
				return "goodstype/edit";
			}
		}
	}
}
