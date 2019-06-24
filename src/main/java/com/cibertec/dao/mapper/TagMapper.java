package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.TagBean;

@Mapper
public interface TagMapper {
	@Select("SELECT id, descripcion from tag")
	public List<TagBean> getAll();
	
	@Select("SELECT id, descripcion from tag where id=#{id}")
	public TagBean getTagById(TagBean bean);
	
	@Insert("INSERT into tag(descripcion) "
			+ "VALUES(#{descripcion})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarTag(TagBean bean);
	
	@Update("UPDATE tag SET descripcion=#{descripcion} WHERE id=#{id}")
	public boolean updateTag(TagBean bean);
	
	@Delete("DELETE FROM tag where id=#{id}")
	public boolean deleteTag(TagBean bean);
	
	@Select("SELECT id, descripcion from tag where descripcion=#{descripcion}")
	public TagBean validRepiteRegister(TagBean bean);
}
