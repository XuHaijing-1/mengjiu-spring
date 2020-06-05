
package cn.edu.scujcc.dao;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Indent;

@Repository
public interface IndentRepository extends MongoRepository<Indent,String>{
	
	List<Indent>findByCommodityname(String commodityname);
	List<Indent>findByCategory(String category);

	List<Indent> findByCommoditynameAndCategory(String commodityname, String category);
	

	public List<Indent>findByCommentsDtAfter(LocalDateTime theDt);
	
}