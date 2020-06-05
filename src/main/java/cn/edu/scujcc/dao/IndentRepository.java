
package cn.edu.scujcc.dao;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Indent;

@Repository
public interface IndentRepository extends MongoRepository<Indent,String>{
	
	List<Indent>findByIndentname(String indentname);
	List<Indent>findByCategory(String category);

	List<Indent> findByIndentnameAndCategory(String indentname, String category);
	
}