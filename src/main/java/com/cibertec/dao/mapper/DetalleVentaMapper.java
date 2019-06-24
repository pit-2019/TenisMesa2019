package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.DetalleVentaBean;


public interface DetalleVentaMapper {
	@Select("SELECT id, id_producto as idProducto, id_venta as idVenta from detalle_venta")
	public List<DetalleVentaBean> getAll();
	@Insert("INSERT into detalle_venta(id_producto,id_venta,cantidad,precio) "
			+ "VALUES(#{idProducto}, #{idVenta}, #{cantidad}, #{precio})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarDetalleVenta(DetalleVentaBean bean);
	@Update("UPDATE venta SET id_producto=#{idProducto}, id_venta=#{idVenta}  WHERE id=#{id}")
	public boolean updateDetalleVenta(DetalleVentaBean bean);
	@Delete("DELETE FROM detalle_venta where id=#{id}")
	public boolean deleteDetalleVenta(DetalleVentaBean bean);
}
