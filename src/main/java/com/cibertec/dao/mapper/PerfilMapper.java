package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.PerfilBean;


@Mapper
public interface PerfilMapper {
	
	@Select("SELECT id, nombre, apellido_paterno as apellidoPaterno, apellido_materno as apellidoMaterno, "
			+ "fecha_nacimiento as fechaNacimiento, pais, "
			+ "direccion, ranking, descripcion, image_view as imageView from perfil")
	public List<PerfilBean> getAll();
	@Select("SELECT id, nombre, apellido_paterno as apellidoPaterno, apellido_materno as apellidoMaterno, "
			+ "fecha_nacimiento as fechaNacimiento, pais, "
			+ "direccion, ranking, descripcion, image_view as imageView from perfil where descripcion=#{descripcion}")
	public List<PerfilBean> getAllbyDescripcion(PerfilBean bean);
	
	@Select("SELECT id, nombre, apellido_paterno as apellidoPaterno, apellido_materno as apellidoMaterno, "
			+ "fecha_nacimiento as fechaNacimiento, pais, direccion, ranking, descripcion, image_view as imageView from perfil where id=#{id}")
	public PerfilBean getPerfilById(PerfilBean bean);
	
	@Insert("INSERT into perfil(nombre,apellido_paterno,apellido_materno,fecha_nacimiento,pais,direccion,ranking,descripcion, image_view) "
			+ "VALUES(#{nombre}, #{apellidoPaterno}, #{apellidoMaterno}, #{fechaNacimiento}, #{pais}, #{direccion}, "
			+ "#{ranking}, #{descripcion}, #{imageView,jdbcType=BLOB})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarPerfil(PerfilBean bean);
	
	@Update("UPDATE perfil SET nombre=#{nombre}, apellido_paterno=#{apellidoPaterno}, apellido_materno=#{apellidoMaterno}, "
			+ "fecha_nacimiento=#{fechaNacimiento}, pais=#{pais}, "
			+ "direccion=#{direccion}, ranking=#{ranking}, descripcion=#{descripcion}, image_view=#{imageView,jdbcType=BLOB} WHERE id=#{id}")
	public boolean updatePerfil(PerfilBean bean);
	
	@Delete("DELETE FROM perfil where id=#{id}")
	public boolean deletePerfil(PerfilBean bean);
	
	@Select("SELECT id, nombre, apellido_paterno as apellidoPaterno, apellido_materno as apellidoMaterno, "
			+ "fecha_nacimiento as fechaNacimiento, pais, direccion, ranking, descripcion, image_view as imageView from perfil where nombre=#{nombre} "
			+ "and apellido_paterno=#{apellidoPaterno} and apellido_materno=#{apellidoMaterno}")
	public PerfilBean validRepiteRegister(PerfilBean bean);
}
