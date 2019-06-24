package com.cibertec.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cibertec.bean.PerfilBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.ResponseListBean;
import com.cibertec.bean.ResponseObjectBean;
import com.cibertec.service.PerfilService;

@RestController
public class PerfilController {
	private Logger LOGGER = LoggerFactory.getLogger(PerfilController.class);
	
	@Autowired
	PerfilService perfilService;
	
	@RequestMapping(value="/rest/getPerfil.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<PerfilBean> getPerfil(@RequestParam("id") String id){
		LOGGER.info("getPerfil");		
		PerfilBean perfil= null;
		ResponseObjectBean<PerfilBean> result = new ResponseObjectBean<>();
		try {	
			perfil = new PerfilBean();
			perfil.setId(Integer.parseInt(id));
			perfil = perfilService.getPerfilById(perfil);
			result.setObjeto(perfil);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getPerfiles.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<PerfilBean> getPerfiles(){
		LOGGER.info("getPerfiles");		
		List<PerfilBean> perfilList = null;
		ResponseListBean<PerfilBean> result = new ResponseListBean<PerfilBean>();	
		try {	
			perfilList = perfilService.getAll();
			result.setRows(perfilList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/insertarPerfil.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<PerfilBean> insertarPerfil(@RequestParam("archivo") MultipartFile archivo, @RequestParam("nombre") String nombre,
			@RequestParam("apellidoPaterno") String apellidoPaterno,@RequestParam("apellidoMaterno") String apellidoMaterno, 
			@RequestParam("fechaNacimiento") Date fechaNacimiento,@RequestParam("pais") String pais,
			@RequestParam("direccion") String direccion,@RequestParam("ranking") String ranking,
			@RequestParam("descripcion") String descripcion){
		LOGGER.info("insertarProducto");	
		ResponseObjectBean<PerfilBean> result = new ResponseObjectBean<PerfilBean>();	
		try {	
			PerfilBean bean = new PerfilBean();
			bean.setNombre(nombre);
			bean.setApellidoPaterno(apellidoPaterno);
			bean.setApellidoMaterno(apellidoMaterno);
			bean.setFechaNacimiento(fechaNacimiento);
			bean.setPais(pais);
			bean.setDireccion(direccion);
			bean.setRanking(ranking);
			bean.setDescripcion(descripcion);
			
			if(archivo.getSize()>0) {
				bean.setImageView(archivo.getBytes());
			}		
			
			LOGGER.info(bean.toString());
			
			if(!perfilService.validRepiteRegister(bean)) {
				if(perfilService.insertarPerfil(bean)) {
					result.setObjeto(bean);
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Perfil registrado correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar perfil");
				}	
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Perfil ya existe");
			}
			
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updatePerfil.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<String> updatePerfil(@RequestParam("archivo") MultipartFile archivo, @RequestParam("nombre") String nombre,
			@RequestParam("apellidoPaterno") String apellidoPaterno,@RequestParam("apellidoMaterno") String apellidoMaterno, 
			@RequestParam("fechaNacimiento") Date fechaNacimiento,@RequestParam("pais") String pais,
			@RequestParam("direccion") String direccion,@RequestParam("ranking") String ranking,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("id") String id){
		LOGGER.info("updatePerfil");
		
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {
			PerfilBean bean = new PerfilBean();
			bean.setId(Integer.parseInt(id));
			bean = perfilService.getPerfilById(bean);
			bean.setNombre(nombre);
			bean.setApellidoPaterno(apellidoPaterno);
			bean.setApellidoMaterno(apellidoMaterno);
			bean.setFechaNacimiento(fechaNacimiento);
			bean.setPais(pais);
			bean.setDireccion(direccion);
			bean.setRanking(ranking);
			bean.setDescripcion(descripcion);
			
			if(archivo.getSize()>0) {
				bean.setImageView(archivo.getBytes());
			}	
			LOGGER.info(bean.toString());

			PerfilBean objValidId = perfilService.getPerfilById(bean);
			if(objValidId!=null) {
				if((!objValidId.getNombre().trim().equalsIgnoreCase(bean.getNombre().trim())) &&
						(!objValidId.getApellidoPaterno().trim().equalsIgnoreCase(bean.getApellidoPaterno().trim()) &&
								(!objValidId.getApellidoMaterno().trim().equalsIgnoreCase(bean.getApellidoMaterno().trim())))) {
					
					if(!perfilService.validRepiteRegister(bean)) {
						if(perfilService.updatePerfil(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Perfil actualizado correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al actualizar perfil");
						}
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Perfil ya existe");
					}
				}else {
					if(perfilService.updatePerfil(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Perfil actualizado correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al actualizar perfil");
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
	
	@RequestMapping(value="/rest/deletePerfil.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deletePerfil(@RequestParam("id")String id){
		LOGGER.info("deletePerfil");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			PerfilBean bean = new PerfilBean();
			bean.setId(Integer.parseInt(id));
			if(perfilService.deletePerfil(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Producto numero "+id+" eliminada correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar perfil");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}

	@RequestMapping(value = "/actualizar_perfil", method = { RequestMethod.GET })
	public ModelAndView actualizarPerfil(ModelMap modelMap,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarPerfil");
		LOGGER.info(id);
		try {
			modelMap.addAttribute("idPerfil", id);
			
			model.addAllObjects(modelMap);
			model.setViewName("registrar_perfil");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/confirmar_perfil", method = { RequestMethod.GET })
	public ModelAndView confirmarPerfil(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_perfil");
		try {
			model.setViewName("confirmarPerfil");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_perfil", method = { RequestMethod.GET })
	public ModelAndView confirmarDeletePerfil(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_perfil");
		try {
			model.setViewName("confirmarDeletePerfil");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}

	@RequestMapping(value = "/registrar_perfil.html", method = RequestMethod.GET)
	public ModelAndView registrarPerfil(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarPerfil");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_perfil");
		return model;
	}
	
	@RequestMapping(value = "/perfiles.html", method = RequestMethod.GET)
	public ModelAndView perfiles(ModelMap modelMap,HttpSession session) {
		LOGGER.info("perfiles");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("perfiles");
		return model;
	}

	@RequestMapping(value = "/perfil-public.html", method = RequestMethod.GET)
	public ModelAndView perfilPublic(ModelMap modelMap,HttpSession session) {
		LOGGER.info("perfilPublic");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("perfil-public");
		return model;
	}
	
}
