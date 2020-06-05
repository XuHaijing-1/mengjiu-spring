package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.api.IndentController;
import cn.edu.scujcc.dao.IndentRepository;
import cn.edu.scujcc.model.Indent;

@Service
public class IndentService {

	public static final Logger logger = LoggerFactory.getLogger(IndentController.class);
	
	@Autowired
	private IndentRepository repo;
	
	/**
	 * 获取所有商品数据
	 * 
	 * @return商品List
	 */
	@Cacheable("indents")
	public List<Indent>getAllIndents(){
		logger.debug("准备从数据库读取所有商品信息...");
		return repo.findAll();
	}
	
	
	/**
	 * 获取一个商品的数据
	 * 
	 * 
	 * @param IndentId 商品信息
	 * @return 商品对象，若未找到返回null
	 */
	@Cacheable("indents")
	public Indent getIndent(String indentId) {
		logger.debug("准备从数据库读取商品"+indentId);
		Optional<Indent> result=repo.findById(indentId);

		logger.debug("读取的商品为："+result);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	/**
	 * 删除指定商品的数据
	 * 
	 * @param IndentId 待删除的商品数据编号
	 * @return 若成功删除则返回true，否则返回false
	 */
	public boolean deleteIndent(String indentId) {
		boolean result=true;
		repo.deleteById(indentId);
		
		return result;
	}
	
	/**
	 * 保存商品
	 * 
	 * @param c 待保存的商品对象（没有id值）
	 * @return  保存后的商品（有id值）
	 */
	public Indent createIndent(Indent c) {
		return repo.save(c);
	}
	
	/**
	 * 更新指定的商品信息
	 * @param c 新的商品信息，用于更新已存在的同一视频
	 * @return 更新后的商品信息
	 */
	public Indent updateIndent(Indent c) {
		Indent saved=getIndent(c.getId());
		if(saved!=null) {
			if(c.getIndentname()!=null) {
			saved.setIndentname(c.getIndentname());
			}
			if(c.getCategory()!=null) {
			saved.setCategory(c.getCategory());
			}
			if(c.getProduction()!=null) {
				saved.setProduction(c.getProduction());
			}
			logger.debug(saved.toString());
		}
		if(c.getCover()!=null) {
			saved.setCover(c.getCover());
		}
			saved.setPrice(c.getPrice());
		
		return repo.save(saved); //保存更新后的实体对象
	}
	
	public List<Indent>indentnamecxIndent(String indentname){
		return repo.findByIndentname(indentname);
	}
	public List<Indent>categorycxIndent(String category){
		return repo.findByCategory(category);
	}

	/**
	 * 搜索方法
	 * @param Indentname
	 * @param Category
	 * @return
	 */
	 public List<Indent> search(String indentname,String category){
		 return repo.findByIndentnameAndCategory(indentname, category);
	 }
	 
}