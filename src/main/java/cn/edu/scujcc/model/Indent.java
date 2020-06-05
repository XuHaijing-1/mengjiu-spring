package cn.edu.scujcc.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
	/**
	 * 商品订单模型对象。
	 * @author 憨态可拘
	 *
	 */
public class Indent implements Serializable {
	private static final long serialVersionUID = 7245499099716015024L;
	@Id
	private String id;   //商品id
	private String indentname; //订单名称
	private String commodityname;  //商品名称
	private String category; //商品类别
	private String production;  //商品产地
	private String cover; //商品封面图片
	private int score; //评分
	private int abv; //酒精度
	private String describe; //描述
	private int price=0;  //商品价格
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  //日期时间格式
	private Date time;  //用户下单时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndentname() {
		return indentname;
	}
	public void setIndentname(String indentname) {
		this.indentname = indentname;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getAbv() {
		return abv;
	}
	public void setAbv(int abv) {
		this.abv = abv;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Indent [id=" + id + ", indentname=" + indentname + ", commodityname=" + commodityname + ", category="
				+ category + ", production=" + production + ", cover=" + cover + ", score=" + score + ", abv=" + abv
				+ ", describe=" + describe + ", price=" + price + ", time=" + time + "]";
	}
	
	
}
