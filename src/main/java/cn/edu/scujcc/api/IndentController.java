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

import cn.edu.scujcc.model.Indent;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.service.IndentService;
//import cn.edu.scujcc.service.UserService;

@RestController
@RequestMapping("/indent")
public class IndentController {
	public static final Logger logger = LoggerFactory.getLogger(IndentController.class);
	
	@Autowired
	private IndentService service;
	
	@Autowired
//	private UserService userService;
	
	
	@GetMapping
	public Result<List<Indent>> getAllIndents() {
		logger.info("正在读取所有商品订单信息...");
		Result<List<Indent>> result=new Result<List<Indent>>();
		List<Indent> indents = service.getAllIndents();
		result.setData(indents);
		return result;
	}
	
	
	@GetMapping("/{id}")
	public Result<Indent> getIndent(@PathVariable String id) {
		logger.info("正在读取"+id+"的商品订单信息...");
		Result<Indent> result =new Result<>();
		Indent c=service.getIndent(id);
		if(c!= null) {
			result=result.ok();
			result.setData(c);
		}else {
			logger.error("找不到指定商品订单");
			result=result.error();
			result.setMessage("找不到指定的商品订单信息！");
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	public Result<Indent> deleteIndent(@PathVariable String id) {
		logger.info("即将删除商品订单：id="+id);
		Result<Indent> result =new Result<>();
		boolean del=service.deleteIndent(id);
		if(del) {
			result= result.ok();
		}else {
			result.setStatus(Result.ERROR);
		}
		return result;
	}
	
	@PostMapping
	public Indent createIndent(@RequestBody Indent c) {
		System.out.println("即将新建商品订单，商品订单数据："+c);
		Indent saved=service.createIndent(c);
		return saved;
	}
	
	@PutMapping
	public Indent updateIndent(@RequestBody Indent c) {
		System.out.println("即将更新商品订单，商品订单数据："+c);
		Indent updated =service.updateIndent(c);
		return updated;
	}
	
	@GetMapping("/indentname/{indentname}")
	public List<Indent>indentnamecx(@PathVariable String indentname){
		return service.indentnamecxIndent(indentname);
	}
	@GetMapping("/category/{category}")
	public List<Indent>categorycx(@PathVariable String category){
		return service.categorycxIndent(category);
	}
	
	@GetMapping("/s/{indentname}/{category}")
	public List<Indent>search(@PathVariable String indentname,@PathVariable String category){
		return service.search(indentname, category);
	}
	
	/**
	 * 查询热门订单商品订单
	 * @return
	 */
	@GetMapping("/hot")
	public List<Indent>getHostIndents(){
		return service.getLatestCommentsIndent();
	}
}
