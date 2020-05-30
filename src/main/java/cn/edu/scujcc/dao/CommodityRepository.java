package cn.edu.scujcc.dao;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Commodity;

@Repository
public interface CommodityRepository extends MongoRepository<Commodity,String>{
	
	List<Commodity>findByCommodityname(String commodityname);
	List<Commodity>findByCategory(String category);

	List<Commodity> findByCommoditynameAndCategory(String commodityname, String category);
	

	public List<Commodity>findByCommentsDtAfter(LocalDateTime theDt);
	
}
