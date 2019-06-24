package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.NoticiaBean;

@Mapper
public interface NoticiaMapper {
	
	@Select("SELECT id, id_tag as idTag, titulo, descripcion, fecha_publicacion as fechaPublicacion,"
			+ "comentario_habilitado as comentarioHabilitado, image_preview as imagePreview from noticia")
	public List<NoticiaBean> getAll();
	@Select("SELECT id, id_tag as idTag, titulo, descripcion, fecha_publicacion as fechaPublicacion,"
			+ "comentario_habilitado as comentarioHabilitado, image_preview as imagePreview from noticia where id_tag=#{idTag}")
	public List<NoticiaBean> getAllbyTag(NoticiaBean bean);
	
	@Select("SELECT id, id_tag as idTag, titulo, descripcion, fecha_publicacion as fechaPublicacion,"
			+ "comentario_habilitado as comentarioHabilitado, image_preview as imagePreview from noticia where titulo like #{titulo}")
	public List<NoticiaBean> getNoticiaByDescripcion(NoticiaBean bean);
	
	@Select("SELECT n.id, n.id_tag as idTag, n.titulo, n.descripcion, n.fecha_publicacion as fechaPublicacion,"
			+ "n.comentario_habilitado as comentarioHabilitado, n.image_preview as imagePreview, t.descripcion as descripcionTag from noticia n "
			+ "inner join tag t "
			+ "on n.id_tag=t.id "
			+ "where n.id=#{id}")
	public NoticiaBean getNoticiaById(NoticiaBean bean);
	
	@Insert("INSERT into noticia(id_tag,titulo,descripcion,fecha_publicacion,comentario_habilitado,image_preview) "
			+ "VALUES(#{idTag}, #{titulo}, #{descripcion}, #{fechaPublicacion}, #{comentarioHabilitado}, "
			+ "#{imagePreview,jdbcType=BLOB})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public void insertarNoticia(NoticiaBean bean);
	
	@Update("UPDATE noticia SET id_tag=#{idTag}, titulo=#{titulo}, descripcion=#{descripcion}, "
			+ "comentario_habilitado=#{comentarioHabilitado}, image_preview=#{imagePreview,jdbcType=BLOB} WHERE id=#{id}")
	public boolean updateNoticia(NoticiaBean bean);
	
	@Delete("DELETE FROM noticia where id=#{id}")
	public boolean deleteNoticia(NoticiaBean bean);
	
	@Delete("DELETE FROM comentario where id_noticia=#{id}")
	public boolean deleteComentarioNoticia(NoticiaBean bean);
	
	@Select("SELECT id, id_tag as idTag, titulo, descripcion, fecha_publicacion as fechaPublicacion,"
			+ "comentario_habilitado as comentarioHabilitado, image_preview as imagePreview from noticia where titulo = #{titulo}")
	public NoticiaBean validRepiteRegister(NoticiaBean bean);
}
