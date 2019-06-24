package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.DetalleVentaBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.VentaBean;


@Mapper
public interface ProductoMapper {
	@Select("SELECT id, descripcion, precio, id_categoria as idCategoria, image_view as imageView, destacado, detalle from producto")
	public List<ProductoBean> getAll();
	@Select("SELECT id, descripcion, precio, id_categoria as idCategoria, image_view as imageView, detalle from producto where id_categoria=#{idCategoria}")
	public List<ProductoBean> getAllbyCategoria(ProductoBean bean);
	
	@Select("SELECT id, descripcion, precio, id_categoria as idCategoria, image_view as imageView, detalle from producto where descripcion LIKE #{descripcion} ")
	public List<ProductoBean> getAllbyDescripcion(ProductoBean bean);
	
	@Select("SELECT id, descripcion, precio, id_categoria as idCategoria, image_view as imageView, destacado, detalle from producto where id=#{id}")
	public ProductoBean getProductoById(ProductoBean bean);
	
	@Insert("INSERT into producto(descripcion,precio,id_categoria,image_view,destacado, detalle) "
			+ "VALUES(#{descripcion}, #{precio}, #{idCategoria}, #{imageView,jdbcType=BLOB}, #{destacado}, #{detalle})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarProducto(ProductoBean bean);
	
	@Update("UPDATE producto SET id_categoria=#{idCategoria}, precio=#{precio}, descripcion=#{descripcion}, image_view=#{imageView,jdbcType=BLOB}, destacado=#{destacado}, detalle=#{detalle} WHERE id=#{id}")
	public boolean updateProducto(ProductoBean bean);
	
	@Delete("DELETE FROM producto where id=#{id}")
	public boolean deleteProducto(ProductoBean bean);
	
	@Delete("DELETE FROM detalle_venta where id=#{id}")
	public boolean deleteDetalleVentaProducto(DetalleVentaBean bean);
	
	@Select("SELECT v.id "
			+ "FROM venta v inner join detalle_venta dv "
			+ "on v.id=dv.id_venta inner join producto p "
			+ "on p.id = dv.id_producto where p.id=#{id}")
	public List<VentaBean> getVentaProducto(ProductoBean bean);
	
	@Select("SELECT dv.id "
			+ "FROM venta v inner join detalle_venta dv "
			+ "on v.id=dv.id_venta where v.id=#{id}")
	public List<DetalleVentaBean> getVentaDetalleProducto(VentaBean bean);
	
	@Delete("DELETE FROM venta where id=#{id}")
	public boolean deleteVentaProducto(VentaBean bean);
	
	@Select("SELECT id, descripcion, precio, id_categoria as idCategoria, image_view as imageView, destacado, detalle from producto where descripcion=#{descripcion}")
	public ProductoBean validRepiteRegister(ProductoBean bean);
	
}
