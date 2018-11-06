package core.base;

import java.util.List;

/**
 * 
* @Title: BaseMapper.java 
* @Package core.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 刘伟  15818570028@163.com   
* @date 2016年9月24日 下午11:57:45 
* @version V1.0
 */
public interface BaseMapper<T extends BaseModel> {
	List<T> selectAll();

	int deleteByPrimaryKey(String id);

	T selectByPrimaryKey(String id);

	int insert(T record);

	int updateByPrimaryKey(T record);
}
