package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.CategoriaBean;

@Mapper
public interface CategoriaMapper {
	@Select("SELECT id, descripcion from categoria")
	public List<CategoriaBean> getAll();
	
	@Select("SELECT id, descripcion from categoria where id=#{id}")
	public CategoriaBean getCategoriaById(CategoriaBean bean);
	
	@Insert("INSERT into categoria(descripcion) "
			+ "VALUES(#{descripcion})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarCategoria(CategoriaBean bean);
	
	@Update("UPDATE categoria SET descripcion=#{descripcion} WHERE id=#{id}")
	public boolean updateCategoria(CategoriaBean bean);
	
	@Delete("DELETE FROM categoria where id=#{id}")
	public boolean deleteCategoria(CategoriaBean bean);
	
	@Select("SELECT id, descripcion from categoria where descripcion=#{descripcion}")
	public CategoriaBean validRepiteRegister(CategoriaBean bean);
}
