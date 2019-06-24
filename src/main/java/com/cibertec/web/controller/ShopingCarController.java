package com.cibertec.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.UniqueElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cibertec.bean.CategoriaBean;
import com.cibertec.bean.ComentarioBean;
import com.cibertec.bean.CompraBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.ResponseListBean;
import com.cibertec.bean.ResponseObjectBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.bean.VentaBean;
import com.cibertec.service.CategoriaService;
import com.cibertec.service.ProductoService;
import com.cibertec.service.VentaService;

@RestController
public class ShopingCarController {
	
	@Autowired
	ProductoService productoService;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	VentaService ventaService;
	
	private Logger LOGGER = LoggerFactory.getLogger(ShopingCarController.class);

	@RequestMapping(value="/rest/getProducto.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<ProductoBean> getProducto(@RequestParam("id") String id){
		LOGGER.info("getProducto");		
		ProductoBean producto = null;
		ResponseObjectBean<ProductoBean> result = new ResponseObjectBean<>();
		try {	
			producto = new ProductoBean();
			producto.setId(Integer.parseInt(id));
			producto = productoService.getProductoById(producto);
			result.setObjeto(producto);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value = "/cart.html", method = RequestMethod.GET)
	public ModelAndView home(ModelMap modelMap,HttpSession session) {
		LOGGER.info("cart");
		
		ModelAndView model = new ModelAndView();
		
		modelMap.addAttribute("saludo", "hola");
		model.addObject(modelMap);
		
		model.setViewName("cart");
		return model;
	}
	
	@RequestMapping(value="/rest/getProductos.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<ProductoBean> getProductos(){
		LOGGER.info("getProductos");		
		List<ProductoBean> productoList = null;
		ResponseListBean<ProductoBean> result = new ResponseListBean<ProductoBean>();	
		try {	
			productoList = productoService.getAll();
			result.setRows(productoList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getProductosByDescripcion.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<ProductoBean> getProductosByDescripcion(@RequestParam("descripcion") String descripcion){
		LOGGER.info("getProductosByDescripcion");		
		List<ProductoBean> productoList = null;
		ResponseListBean<ProductoBean> result = new ResponseListBean<ProductoBean>();	
		try {	
			ProductoBean bean = new ProductoBean();
			bean.setDescripcion("%"+descripcion+"%");
			productoList = productoService.getAllbyDescripcion(bean);
			result.setRows(productoList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getCategorias.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<CategoriaBean> getCategoria(){
		LOGGER.info("getCategoria");		
		List<CategoriaBean> categoriaList = null;
		ResponseListBean<CategoriaBean> result = new ResponseListBean<CategoriaBean>();	
		try {	
			categoriaList = categoriaService.getAll();
			result.setRows(categoriaList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getCategoria.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<CategoriaBean> getCategoria(@RequestParam("id") String id){
		LOGGER.info("getCategoria");		
		CategoriaBean categoria = null;
		ResponseObjectBean<CategoriaBean> result = new ResponseObjectBean<>();
		try {	
			categoria = new CategoriaBean();
			categoria.setId(Integer.parseInt(id));
			categoria = categoriaService.getCategoriaById(categoria);
			result.setObjeto(categoria);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/insertarCategoria.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> insertarCategoria(@RequestBody CategoriaBean bean){
		LOGGER.info("insertarCategoria");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			if(!categoriaService.validRepiteRegister(bean)) {
				if(categoriaService.insertarCategoria(bean)) {
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Categoria registrada correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar categoria");
				}
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Categoria ya existe");
			}
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updateCategoria.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> updateCategoria(@RequestBody CategoriaBean bean){
		LOGGER.info("updateCategoria");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			CategoriaBean objValidId = categoriaService.getCategoriaById(bean);
			if(objValidId!=null) {
				if(!objValidId.getDescripcion().trim().equalsIgnoreCase(bean.getDescripcion().trim())) {
					if(!categoriaService.validRepiteRegister(bean)) {
						if(categoriaService.updateCategoria(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Categoria actualizada correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al actualizadar categoria");
						}
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Categoria ya existe");
					}
				}else {
					if(categoriaService.updateCategoria(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Categoria actualizada correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al actualizadar categoria");
					}
				}
			}
			
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/deleteCategoria.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteCategoria(@RequestParam("id")String id){
		LOGGER.info("deleteCategoria");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			CategoriaBean bean = new CategoriaBean();
			bean.setId(Integer.parseInt(id));
			if(categoriaService.deleteCategoria(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Categoria numero "+id+" eliminada correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar categoria");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/insertarProducto.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<ProductoBean> insertarProducto(@RequestParam("archivo") MultipartFile archivo, @RequestParam("precio") String precio,
			@RequestParam("descripcion") String descripcion,@RequestParam("idCategoria") String idCategoria, @RequestParam("destacado") String destacado,
			@RequestParam("detalle") String detalle){
		LOGGER.info("insertarProducto");	
		ResponseObjectBean<ProductoBean> result = new ResponseObjectBean<ProductoBean>();	
		try {	
			ProductoBean bean = new ProductoBean();
			bean.setDescripcion(descripcion);
			bean.setPrecio(new BigDecimal(precio));
			bean.setDestacado(destacado);
			bean.setIdCategoria(Integer.parseInt(idCategoria));
			bean.setDetalle(detalle);
			if(archivo.getSize()>0) {
				bean.setImageView(archivo.getBytes());
			}		
			LOGGER.info(bean.toString());
			
			if(!productoService.validRepiteRegister(bean)) {
				if(productoService.insertarProducto(bean)) {
					result.setObjeto(bean);
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Producto registrado correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar producto");
				}	
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error producto ya existe");
			}
			
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updateProducto.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<String> updateProducto(@RequestParam("archivo") MultipartFile archivo, @RequestParam("precio") String precio,
			@RequestParam("descripcion") String descripcion,@RequestParam("idCategoria") String idCategoria, @RequestParam("destacado") String destacado,
			@RequestParam("id") String id,@RequestParam("detalle") String detalle){
		LOGGER.info("updateProducto");
		
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {
			ProductoBean bean = new ProductoBean();
			bean.setId(Integer.parseInt(id));
			bean = productoService.getProductoById(bean);
			bean.setDescripcion(descripcion);
			bean.setPrecio(new BigDecimal(precio));
			bean.setDestacado(destacado);
			bean.setIdCategoria(Integer.parseInt(idCategoria));
			bean.setDetalle(detalle);
			if(archivo.getSize()>0) {
				bean.setImageView(archivo.getBytes());
			}	
			LOGGER.info(bean.toString());
			
			ProductoBean objValidId = productoService.getProductoById(bean);
			if(objValidId!=null) {
				if(!objValidId.getDescripcion().trim().equalsIgnoreCase(bean.getDescripcion().trim())) {
					if(!productoService.validRepiteRegister(bean)) {
						if(productoService.updateProducto(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Producto actualizado correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al actualizar producto");
						}
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error producto ya existe");
					}
				}else {
					if(productoService.updateProducto(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Producto actualizado correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al actualizar producto");
					}
				}
			}
			

			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/deleteProducto.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteProducto(@RequestParam("id")String id){
		LOGGER.info("deleteProducto");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			ProductoBean bean = new ProductoBean();
			bean.setId(Integer.parseInt(id));
			if(productoService.deleteProducto(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Producto numero "+id+" eliminada correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar producto");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getComprasByUsuario.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<CompraBean> getCompraByUsuario(HttpSession session){
		LOGGER.info("getCompraByUsuario");		
		List<CompraBean> compraList = null;
		ResponseListBean<CompraBean> result = new ResponseListBean<CompraBean>();	
		UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuario");
		try {	
			if(usuario!=null) {
				LOGGER.info(usuario.toString());
				CompraBean bean = new CompraBean();
				if(usuario.getIdRol()==2) {
					bean.setUsername("%%");
				}else {
					bean.setUsername(usuario.getUsername());	
				}				
				compraList = ventaService.getVentaByUsuario(bean);
				result.setRows(compraList);
			}else {
				result.setEstadoRespuesta("ERROR");
				result.setMensajeRespueta("Error al obtener datos");
			}	
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/insertVenta.json",method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseObjectBean<VentaBean> insertVenta(HttpSession session,
			@RequestBody VentaBean ventaBean){
		
		LOGGER.info("insertVenta");
		
		UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuario");
		ResponseObjectBean<VentaBean> bean = new ResponseObjectBean<VentaBean>();

		LOGGER.info(ventaBean.toString());
		try {
			if(usuario!=null) {
				ventaBean.setUsuarioRegistro(usuario.getUsername());
				ventaBean.setFechaRegistro(new Date());
				if(ventaService.insertarVenta(ventaBean)) {
					bean.setObjeto(ventaBean);
					bean.setTipoMensaje("SUCCESS");
					bean.setMensaje("Compra efectuada exitosamente");
				}else {
					bean.setTipoMensaje("ERROR");
					bean.setMensaje("Error al registrar compra");
				}
			}else {
				bean.setTipoMensaje("ERROR");
				bean.setMensaje("Error al registrar venta, debe logearse");
			}	
		} catch (Exception e) {
			LOGGER.info(e.getCause()+" - "+e.getMessage());
		}
		
		return bean;
	}

	@RequestMapping(value = "/actualizar_producto", method = { RequestMethod.GET })
	public ModelAndView actualizarProducto(ModelMap modelMap,HttpSession session,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarProducto");
		LOGGER.info(id);
		try {
			List<CategoriaBean> categoriaList = categoriaService.getAll();
			session.setAttribute("categoriaList", categoriaList);
			modelMap.addAttribute("idProducto", id);			
			model.addAllObjects(modelMap);
			model.setViewName("registrar_producto");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/confirmar_producto", method = { RequestMethod.GET })
	public ModelAndView confirmarProducto(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_producto");
		try {
			model.setViewName("confirmarProducto");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_producto", method = { RequestMethod.GET })
	public ModelAndView confirmarDeleteProducto(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_producto");
		try {
			model.setViewName("confirmarDeleteProducto");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_categoria", method = { RequestMethod.GET })
	public ModelAndView confirmarCategoria(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_categoria");
		try {
			model.setViewName("confirmarCategoria");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_categoria", method = { RequestMethod.GET })
	public ModelAndView confirmarDeleteCategoria(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_categoria");
		try {
			model.setViewName("confirmarDeleteCategoria");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}

	@RequestMapping(value = "/registrar_producto.html", method = RequestMethod.GET)
	public ModelAndView registrarProducto(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarProducto");
		List<CategoriaBean> categoriaList = categoriaService.getAll();
		session.setAttribute("categoriaList", categoriaList);
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_producto");
		return model;
	}
	
	@RequestMapping(value = "/productos.html", method = RequestMethod.GET)
	public ModelAndView productos(ModelMap modelMap,HttpSession session) {
		LOGGER.info("productos");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("productos");
		return model;
	}
	
	@RequestMapping(value = "/compras.html", method = RequestMethod.GET)
	public ModelAndView compras(ModelMap modelMap,HttpSession session) {
		LOGGER.info("compras");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("compras");
		return model;
	}
	
	@RequestMapping(value = "/registrar_categoria.html", method = RequestMethod.GET)
	public ModelAndView registrarCategoria(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarCategoria");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_categoria");
		return model;
	}
	
	@RequestMapping(value = "/actualizar_categoria", method = { RequestMethod.GET })
	public ModelAndView actualizarCategoria(ModelMap modelMap,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarCategoria");
		LOGGER.info(id);
		try {
			modelMap.addAttribute("idCategoria", id);
			
			model.addAllObjects(modelMap);
			model.setViewName("registrar_categoria");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/categorias.html", method = RequestMethod.GET)
	public ModelAndView categorias(ModelMap modelMap,HttpSession session) {
		LOGGER.info("categorias");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("categorias");
		return model;
	}
	
	@RequestMapping(value = "/confirmar_venta", method = { RequestMethod.GET })
	public ModelAndView confirmarVenta(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_venta");
		try {
			model.setViewName("confirmarVenta");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_tarjeta_credito", method = { RequestMethod.GET })
	public ModelAndView confirmarTarjetaCredito(HttpServletRequest context,@RequestParam("importe") String importe, ModelMap modelMap) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_tarjeta_credito");
		LOGGER.info("importe->"+importe);
		try {
			modelMap.addAttribute("storageImporte", importe);
			model.addAllObjects(modelMap);
			model.setViewName("confirmarTarjetaCredito");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
}

