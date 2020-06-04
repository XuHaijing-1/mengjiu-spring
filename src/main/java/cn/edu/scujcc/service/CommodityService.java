package cn.edu.scujcc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.api.CommodityController;
import cn.edu.scujcc.dao.CommodityRepository;
import cn.edu.scujcc.model.Commodity;
import cn.edu.scujcc.model.Comment;

@Service
public class CommodityService {

	public static final Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@Autowired
	private CommodityRepository repo;
	
	/**
	 * 获取所有商品数据
	 * 
	 * @return商品List
	 */
	@Cacheable("commoditys")
	public List<Commodity>getAllCommoditys(){
		logger.debug("准备从数据库读取所有商品信息...");
		return repo.findAll();
	}
	
	/**
	 * 获取一个商品的数据
	 * 
	 * 
	 * @param CommodityId 商品信息
	 * @return 商品对象，若未找到返回null
	 */
	@Cacheable("commoditys")
	public Commodity getCommodity(String commodityId) {
		logger.debug("准备从数据库读取商品"+commodityId);
		Optional<Commodity> result=repo.findById(commodityId);

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
	 * @param CommodityId 待删除的商品数据编号
	 * @return 若成功删除则返回true，否则返回false
	 */
	public boolean deleteCommodity(String commodityId) {
		boolean result=true;
		repo.deleteById(commodityId);
		
		return result;
	}
	
	/**
	 * 保存商品
	 * 
	 * @param c 待保存的商品对象（没有id值）
	 * @return  保存后的商品（有id值）
	 */
	public Commodity createCommodity(Commodity c) {
		return repo.save(c);
	}
	
	/**
	 * 更新指定的商品信息
	 * @param c 新的商品信息，用于更新已存在的同一视频
	 * @return 更新后的商品信息
	 */
	public Commodity updateCommodity(Commodity c) {
		Commodity saved=getCommodity(c.getId());
		if(saved!=null) {
			if(c.getCommodityname()!=null) {
			saved.setCommodityname(c.getCommodityname());
			}
			if(c.getCategory()!=null) {
			saved.setCategory(c.getCategory());
			}
			if(c.getProduction()!=null) {
				saved.setProduction(c.getProduction());
			}
			if(c.getComments()!=null) {
				if(saved.getComments()!=null) { //把新订单追加到老订单后面
					saved.getComments().addAll(c.getComments());
				}else {//用新订单代替老订单
					saved.setComments(c.getComments());
				}
			}
			logger.debug(saved.toString());
		}
		if(c.getCover()!=null) {
			saved.setCover(c.getCover());
		}
			saved.setPrice(c.getPrice());
		
		return repo.save(saved); //保存更新后的实体对象
	}
	
	public List<Commodity>commoditynamecxCommodity(String commodityname){
		return repo.findByCommodityname(commodityname);
	}
	public List<Commodity>categorycxCommodity(String category){
		return repo.findByCategory(category);
	}
	
	/**
	 * 获取今天的订单火爆信息
	 * @return
	 */
	public List<Commodity>getLatestCommentsCommodity(){
		LocalDateTime now=LocalDateTime.now();
		LocalDateTime today=LocalDateTime.of(now.getYear(),
				now.getMonthValue(),now.getDayOfMonth(),0,0);
		return repo.findByCommentsDtAfter(today);
	}

	/**
	 * 搜索方法
	 * @param Commodityname
	 * @param Category
	 * @return
	 */
	 public List<Commodity> search(String commodityname,String category){
		 return repo.findByCommoditynameAndCategory(commodityname, category);
	 }
	 
	 /**
	  * 向指定商品添加一条订单
	  * @param CommodityId 指定的商品编号
	  * @param comment  将要新增的订单对象
	  */
	 public Commodity addComment(String commodityId, Comment comment) {
		 Commodity saved = getCommodity(commodityId);
		 if(saved!=null) {
			 saved.addComment(comment);
			 return repo.save(saved);
		 }
		 return null;
	 }
	 
	 
	 
	 /**
	  * 返回指定商品的热门订单
	  * @param CommodityId 指定商品的编号
	  * @return 热门订单的列表
	  */
	 public List<Comment> hotComments(String commodityId){
		 List<Comment>result=new ArrayList<>();
		 Commodity saved=getCommodity(commodityId);
		 if(saved!=null&&saved.getComments()!=null) {
			 //根据订单的star进行排序
			 saved.getComments().sort(new Comparator<Comment>() {
				 @Override
				 public int compare(Comment o1,Comment o2) {
					 if(o1.getStar()==o2.getStar()) {
						 return 0;
					 }else if(o1.getStar()<o2.getStar()) {
						 return 1;
					 }else {
						 return -1;
					 }
				 }
			 });
			 if(saved.getComments().size()>4) {
				 result = saved.getComments().subList(0, 4);
			 }else {
				 result = saved.getComments();
			 }
		 }
		 return result;
	 }
	 
}