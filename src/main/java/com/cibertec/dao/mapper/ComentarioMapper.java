package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.ComentarioBean;

@Mapper
public interface ComentarioMapper {
	@Select("SELECT id, nombre, email, texto, id_noticia as idNoticia, fecha_registro as fechaRegistro from comentario")
	public List<ComentarioBean> getAll();
	@Select("SELECT id, nombre, email, texto, id_noticia as idNoticia, fecha_registro as fechaRegistro from comentario where id_noticia=#{idNoticia}")
	public List<ComentarioBean> getAllbyNoticia(ComentarioBean bean);
	
	@Select("SELECT id, nombre, email, texto, id_noticia as idNoticia,fecha_registro as fechaRegistro from comentario where id=#{id}")
	public ComentarioBean getComentarioById(ComentarioBean bean);
	
	@Insert("INSERT into comentario(nombre, email, texto, id_noticia, fecha_registro) "
			+ "VALUES(#{nombre}, #{email}, #{texto}, #{idNoticia}, #{fechaRegistro})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarComentario(ComentarioBean bean);
	
	@Update("UPDATE comentario SET nombre=#{nombre}, email=#{email}, texto=#{texto}, "
			+ "id_noticia=#{idNoticia}, fecha_registro=#{fechaRegistro} WHERE id=#{id}")
	public boolean updateComentario(ComentarioBean bean);
	
	@Delete("DELETE FROM comentario where id=#{id}")
	public boolean deleteComentario(ComentarioBean bean);
}
