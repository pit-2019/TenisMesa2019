package com.cibertec.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cibertec.bean.CategoriaBean;
import com.cibertec.bean.ComentarioBean;
import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.ResponseListBean;
import com.cibertec.bean.ResponseObjectBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.service.OpcionService;
import com.cibertec.service.UsuarioService;

@RestController
public class LoginController {

	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	OpcionService opcionService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(ModelMap modelMap,HttpSession session) {
		LOGGER.info("login");
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/confirmar_usuario", method = { RequestMethod.GET })
	public ModelAndView confirmarUsuario(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_usuario");
		try {
			model.setViewName("confirmarUsuario");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value="/rest/insertarUsuario.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> insertarUsuario(@RequestBody UsuarioBean bean){
		LOGGER.info("insertarUsuario");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			//bean.setIdRol(3);//ROL USUARIO
			UsuarioBean validaObj = usuarioService.getUsuarioByUsername(bean);
			if(validaObj==null) {
				if(usuarioService.insertarUsuario(bean)) {
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Usuario registrado correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar usuario");
				}	
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Usuario ya existe");
			}
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updateUsuario.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> updateUsuario(@RequestBody UsuarioBean bean){
		LOGGER.info("updateUsuario");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			UsuarioBean validIdObj = usuarioService.getUsuarioById(bean);
			if(validIdObj!=null) {
				if(!validIdObj.getUsername().trim().equalsIgnoreCase(bean.getUsername().trim())) {
					UsuarioBean validaObj = usuarioService.getUsuarioByUsername(bean);
					if(validaObj==null) {
						if(usuarioService.updateUsuario(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Usuario actualizado correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al registrar usuario");
						}	
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Usuario ya existe");
					}
				}else {
					if(usuarioService.updateUsuario(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Usuario actualizado correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al registrar usuario");
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
	
	@RequestMapping(value="/rest/getUsuarios.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<UsuarioBean> getUsuario(){
		LOGGER.info("getUsuario");		
		List<UsuarioBean> usuarioList = null;
		ResponseListBean<UsuarioBean> result = new ResponseListBean<UsuarioBean>();	
		try {	
			usuarioList = usuarioService.getAll();
			result.setRows(usuarioList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getUsuario.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<UsuarioBean> getUsuario(@RequestParam("id") String id){
		LOGGER.info("getUsuario");		
		UsuarioBean usuario = null;
		ResponseObjectBean<UsuarioBean> result = new ResponseObjectBean<>();
		try {	
			usuario = new UsuarioBean();
			usuario.setId(Integer.parseInt(id));
			usuario = usuarioService.getUsuarioById(usuario);
			result.setObjeto(usuario);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/deleteUsuario.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteUsuario(@RequestParam("id")String id){
		LOGGER.info("deleteUsuario");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			UsuarioBean bean = new UsuarioBean();
			bean.setId(Integer.parseInt(id));
			if(usuarioService.deleteUsuario(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Usuario numero "+id+" eliminado correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar usuario");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap modelMap,HttpSession session) {
		LOGGER.info("logout");
		ModelAndView model = new ModelAndView();
		UsuarioBean bean = new UsuarioBean();
		bean.setUsername("anom");
		bean.setPassword("anom");
		List<OpcionBean> listaOpciones = opcionService.getAllByUsername(bean);
		LOGGER.info(listaOpciones.toString());
		session.setAttribute("listaOpciones", listaOpciones);
		session.removeAttribute("usuario");
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value="/rest/login-init.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> loginInit(HttpSession session,@RequestBody UsuarioBean bean){
		LOGGER.info("loginInit");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			List<OpcionBean> listaOpciones = opcionService.getAllByUsername(bean);
			LOGGER.info(listaOpciones.toString());
			if(listaOpciones.size()>0) {
				UsuarioBean usuarioBean = usuarioService.getUsuarioByUsername(bean);
				LOGGER.info(usuarioBean.toString());
				session.setAttribute("listaOpciones", listaOpciones);
				session.setAttribute("usuario", usuarioBean);
				result.setTipoMensaje("SUCCESS");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("usuario o clave incorrecto");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value = "/registrar_usuario.html", method = RequestMethod.GET)
	public ModelAndView registrarUsuario(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarUsuario");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_usuario");
		return model;
	}
	
	@RequestMapping(value = "/actualizar_usuario", method = { RequestMethod.GET })
	public ModelAndView actualizarUsuario(ModelMap modelMap,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarUsuario");
		LOGGER.info(id);
		try {
			modelMap.addAttribute("idUsuario", id);
			
			model.addAllObjects(modelMap);
			model.setViewName("registrar_usuario");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/confirmar_usuario_registro", method = { RequestMethod.GET })
	public ModelAndView confirmarUsuarioRegistro(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_usuario_registro");
		try {
			model.setViewName("confirmarUsuarioRegistro");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_usuario", method = { RequestMethod.GET })
	public ModelAndView confirmarDeleteUsuario(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_usuario");
		try {
			model.setViewName("confirmarDeleteUsuario");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/usuarios.html", method = RequestMethod.GET)
	public ModelAndView usuarios(ModelMap modelMap,HttpSession session) {
		LOGGER.info("usuarios");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios");
		return model;
	}
}
