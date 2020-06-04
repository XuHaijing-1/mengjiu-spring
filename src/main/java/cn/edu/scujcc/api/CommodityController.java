package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Commodity;
import cn.edu.scujcc.model.Comment;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.service.CommodityService;
//import cn.edu.scujcc.service.UserService;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
	public static final Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@Autowired
	private CommodityService service;
	
	@Autowired
//	private UserService userService;
	
	
	@GetMapping
	public Result<List<Commodity>> getAllCommoditys() {
		logger.info("正在读取所有商品信息...");
		Result<List<Commodity>> result=new Result<List<Commodity>>();
		List<Commodity> commoditys = service.getAllCommoditys();
		result.setData(commoditys);
		return result;
	}
	
	
	@GetMapping("/{id}")
	public Result<Commodity> getCommodity(@PathVariable String id) {
		logger.info("正在读取"+id+"的商品信息...");
		Result<Commodity> result =new Result<>();
		Commodity c=service.getCommodity(id);
		if(c!= null) {
			result=result.ok();
			result.setData(c);
		}else {
			logger.error("找不到指定商品");
			result=result.error();
			result.setMessage("找不到指定的商品信息！");
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	public Result<Commodity> deleteCommodity(@PathVariable String id) {
		logger.info("即将删除商品：id="+id);
		Result<Commodity> result =new Result<>();
		boolean del=service.deleteCommodity(id);
		if(del) {
			result= result.ok();
		}else {
			result.setStatus(Result.ERROR);
		}
		return result;
	}
	
	@PostMapping
	public Commodity createCommodity(@RequestBody Commodity c) {
		System.out.println("即将新建商品，商品数据："+c);
		Commodity saved=service.createCommodity(c);
		return saved;
	}
	
	@PutMapping
	public Commodity updateCommodity(@RequestBody Commodity c) {
		System.out.println("即将更新商品，商品数据："+c);
		Commodity updated =service.updateCommodity(c);
		return updated;
	}
	
	@GetMapping("/commodityname/{commodityname}")
	public List<Commodity>commoditynamecx(@PathVariable String commodityname){
		return service.commoditynamecxCommodity(commodityname);
	}
	@GetMapping("/category/{category}")
	public List<Commodity>categorycx(@PathVariable String category){
		return service.categorycxCommodity(category);
	}
	
	@GetMapping("/s/{commodityname}/{category}")
	public List<Commodity>search(@PathVariable String commodityname,@PathVariable String category){
		return service.search(commodityname, category);
	}
	
	/**
	 * 查询热门订单商品
	 * @return
	 */
	@GetMapping("/hot")
	public List<Commodity>getHostCommoditys(){
		return service.getLatestCommentsCommodity();
	}
	
	/**
	 * 新增订单
	 * @param CommodityId 被订单的商品编号
	 * @param comment 将要新增的订单对象
	 */
	@PostMapping("/{CommodityId}/comment")
	public Commodity addComment(@PathVariable String commodityId,@RequestBody Comment comment) {
		logger.debug("将为商品"+commodityId+"新增一条订单："+comment);
		// 把订单保存到数据库
		return service.addComment(commodityId, comment);
	}
	
	/**
	 * 热门指定商品的热门订单（前4条）
	 * @param CommodityId 指定的商品编号
	 * @param comment 4条热门订单的列表（数组）
	 */
	@GetMapping("/{CommodityId}/hotcomments")
	public List<Comment> hotComment(@PathVariable String commodityId){
		logger.debug("热门订单"+commodityId);
		//从数据库获取热门订单
		return service.hotComments(commodityId);
	}
	
	
	
	
}
