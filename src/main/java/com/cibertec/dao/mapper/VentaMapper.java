package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.VentaBean;

@Mapper
public interface VentaMapper {
	@Select("SELECT id, monto_total as montoTotal, fecha_registro as fechaRegistro, usuario_registro as usuarioRegistro from venta")
	public List<VentaBean> getAll();
	
	@Select("SELECT v.id,v.fecha_registro as fechaCompra,p.descripcion as descripcionProducto, "
			+ "c.descripcion as descripcionCategoria ,dv.cantidad, dv.precio, v.usuario_registro as username "
			+ "FROM integrador_db.detalle_venta dv inner join venta v on dv.id_venta = v.id "
			+ "inner join producto p on dv.id_producto = p.id "
			+ "inner join categoria c on p.id_categoria = c.id "
			+ "where v.usuario_registro like #{username}")
	public List<CompraBean> getVentaByUsuario(CompraBean bean);
	
	@Insert("INSERT into venta(monto_total,fecha_registro,usuario_registro) "
			+ "VALUES(#{montoTotal}, #{fechaRegistro}, #{usuarioRegistro})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarVenta(VentaBean bean);
	@Update("UPDATE venta SET monto_total=#{montoTotal}  WHERE id=#{id}")
	public boolean updateVenta(VentaBean bean);
	@Delete("DELETE FROM venta where id=#{id}")
	public boolean deleteVenta(VentaBean bean);
}
