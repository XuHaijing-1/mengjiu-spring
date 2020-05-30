package cn.edu.scujcc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * 商品模型对象。
 * @author 憨态可拘
 *
 */
public class Commodity implements Serializable {
	private static final long serialVersionUID = 7245499099716015024L;
	@Id
	private String id;   //商品id
	private String commodityname;  //商品名称
	private String category; //商品类别
	private String production;  //商品产地
	private List<Comment>comments;  //评论
	private String cover; //商品封面图片
	private int price=0;  //商品价格
	
	
	@Override
	public String toString() {
		return "Commodity [id=" + id + ", commodityname=" + commodityname + ", category=" + category + ", production="
				+ production + ", comments=" + comments + ", cover=" + cover + ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((commodityname == null) ? 0 : commodityname.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + price;
		result = prime * result + ((production == null) ? 0 : production.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commodity other = (Commodity) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (commodityname == null) {
			if (other.commodityname != null)
				return false;
		} else if (!commodityname.equals(other.commodityname))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price != other.price)
			return false;
		if (production == null) {
			if (other.production != null)
				return false;
		} else if (!production.equals(other.production))
			return false;
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommodityname() {
		return commodityname;
	}
	public void setCommodityname(String commodityname) {
		this.commodityname = commodityname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 返回所有评论
	 * @return
	 */
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	/**
	 * 向当前商品新增一个评论对象
	 * @param comment
	 */
	public void addComment(Comment comment) {
		if(this.comments==null) {
			this.comments=new ArrayList<>();
		 }
		this.comments.add(comment);
	}
	
}