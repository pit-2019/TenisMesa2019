package com.cibertec.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.cibertec.bean.ComentarioBean;
import com.cibertec.bean.NoticiaBean;
import com.cibertec.bean.ResponseListBean;
import com.cibertec.bean.ResponseObjectBean;
import com.cibertec.bean.TagBean;
import com.cibertec.service.ComentarioService;
import com.cibertec.service.NoticiaService;
import com.cibertec.service.TagService;

@RestController
public class BlogController {
	
	@Autowired
	NoticiaService noticiaService;
	
	@Autowired
	ComentarioService comentarioService;
	
	@Autowired
	TagService tagService;
	
	private Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
	
	@RequestMapping(value = "/blog.html", method = RequestMethod.GET)
	public ModelAndView blog(ModelMap modelMap,HttpSession session) {
		LOGGER.info("blog");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("blog");
		return model;
	}
	
	@RequestMapping(value = "/registrar_noticia.html", method = RequestMethod.GET)
	public ModelAndView registrarNoticia(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarNoticia");
		List<TagBean> tagList = tagService.getAll();
		session.setAttribute("tagList", tagList);
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_noticia");
		return model;
	}
	
	@RequestMapping(value = "/noticias.html", method = RequestMethod.GET)
	public ModelAndView noticias(ModelMap modelMap,HttpSession session) {
		LOGGER.info("noticias");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("noticias");
		return model;
	}
	
	@RequestMapping(value = "/blog-single.html", method = RequestMethod.GET)
	public ModelAndView blogSingle(ModelMap modelMap,HttpSession session) {
		LOGGER.info("blogSingle");
		
		ModelAndView model = new ModelAndView();
		
		modelMap.addAttribute("saludo", "hola");
		model.addObject(modelMap);
		
		model.setViewName("blog-single");
		return model;
	}
	
	@RequestMapping(value="/rest/getNoticias.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<NoticiaBean> getNoticias(){
		LOGGER.info("getNoticias");		
		List<NoticiaBean> noticiaList = null;
		ResponseListBean<NoticiaBean> result = new ResponseListBean<NoticiaBean>();	
		try {	
			noticiaList = noticiaService.getAll();
			result.setRows(noticiaList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	@RequestMapping(value="/rest/getNoticiasByDescripcion.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<NoticiaBean> getNoticiasByDescripcion(@RequestParam("titulo") String titulo){
		LOGGER.info("getNoticiasByDescripcion");		
		List<NoticiaBean> noticiaList = null;
		ResponseListBean<NoticiaBean> result = new ResponseListBean<NoticiaBean>();	
		try {	
			NoticiaBean bean = new NoticiaBean();
			bean.setTitulo("%"+titulo+"%");
			noticiaList = noticiaService.getNoticiaByDescripcion(bean);
			result.setRows(noticiaList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getComentarios.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<ComentarioBean> getComentarios(@RequestParam("idNoticia") String idNoticia){
		LOGGER.info("getComentarios");		
		List<ComentarioBean> comentarioList = null;
		ResponseListBean<ComentarioBean> result = new ResponseListBean<ComentarioBean>();	
		try {	
			ComentarioBean bean = new ComentarioBean();
			bean.setIdNoticia(Integer.parseInt(idNoticia));
			comentarioList = comentarioService.getAllbyNoticia(bean);
			result.setRows(comentarioList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getNoticia.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<NoticiaBean> getNoticia(@RequestParam("id") String id){
		LOGGER.info("getNoticia");		
		NoticiaBean noticia = null;
		ResponseObjectBean<NoticiaBean> result = new ResponseObjectBean<>();
		try {	
			noticia = new NoticiaBean();
			noticia.setId(Integer.parseInt(id));
			noticia = noticiaService.getNoticiaById(noticia);
			result.setObjeto(noticia);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	
	
	@RequestMapping(value="/rest/insertarComentario.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> insertarComentario(@RequestBody ComentarioBean bean){
		LOGGER.info("insertarComentario");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			bean.setFechaRegistro(new Date());
			if(comentarioService.insertarComentario(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Comentario registrado correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al registrar comentario");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/insertarNoticia.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<NoticiaBean> insertarNoticia(@RequestParam("archivo") MultipartFile archivo, @RequestParam("titulo") String titulo,
			@RequestParam("descripcion") String descripcion,@RequestParam("idTag") String idTag, @RequestParam("comentarioHabilitado") String comentarioHabilitado){
		LOGGER.info("insertarNoticia");	
		ResponseObjectBean<NoticiaBean> result = new ResponseObjectBean<NoticiaBean>();	
		try {	
			NoticiaBean bean = new NoticiaBean();
			bean.setTitulo(titulo);
			bean.setDescripcion(descripcion);
			bean.setFechaPublicacion(new Date());
			bean.setComentarioHabilitado(comentarioHabilitado);
			bean.setIdTag(Integer.parseInt(idTag));
			if(archivo.getSize()>0) {
				bean.setImagePreview(archivo.getBytes());
			}		
			LOGGER.info(bean.toString());
			
			if(!noticiaService.validRepiteRegister(bean)) {
				if(noticiaService.insertarNoticia(bean)) {
					result.setObjeto(bean);
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Noticia registrada correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar noticia");
				}	
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error noticia ya existe");
			}
			
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updateNoticia.json", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseObjectBean<String> updateNoticia(@RequestParam("archivo") MultipartFile archivo, @RequestParam("titulo") String titulo,
			@RequestParam("descripcion") String descripcion,@RequestParam("idTag") String idTag, @RequestParam("comentarioHabilitado") String comentarioHabilitado,
			@RequestParam("id") String id){
		LOGGER.info("updateNoticia");
		
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {
			NoticiaBean bean = new NoticiaBean();
			bean.setId(Integer.parseInt(id));
			bean = noticiaService.getNoticiaById(bean);
			bean.setTitulo(titulo);
			bean.setDescripcion(descripcion);
			bean.setFechaPublicacion(new Date());
			bean.setComentarioHabilitado(comentarioHabilitado);
			bean.setIdTag(Integer.parseInt(idTag));
			if(archivo.getSize()>0) {
				bean.setImagePreview(archivo.getBytes());
			}	
			LOGGER.info(bean.toString());

			NoticiaBean objValidId = noticiaService.getNoticiaById(bean);
			if(objValidId!=null) {
				if(!objValidId.getTitulo().trim().equalsIgnoreCase(bean.getTitulo().trim())) {
					if(!noticiaService.validRepiteRegister(bean)) {
						if(noticiaService.updateNoticia(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Noticia actualizada correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al actualizada noticia");
						}
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error noticia ya existe");
					}
				}else {
					if(noticiaService.updateNoticia(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Noticia actualizada correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al actualizada noticia");
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
	
	@RequestMapping(value="/rest/deleteNoticia.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteNoticia(@RequestParam("id")String id){
		LOGGER.info("deleteNoticia");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			NoticiaBean bean = new NoticiaBean();
			bean.setId(Integer.parseInt(id));
			if(noticiaService.deleteNoticia(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Noticia numero "+id+" eliminada correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar noticia");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value = "/actualizar_noticia", method = { RequestMethod.GET })
	public ModelAndView actualizarNoticia(ModelMap modelMap,HttpSession session,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarNoticia");
		LOGGER.info(id);
		try {
			List<TagBean> tagList = tagService.getAll();
			session.setAttribute("tagList", tagList);
			modelMap.addAttribute("idNoticia", id);
			model.addAllObjects(modelMap);
			model.setViewName("registrar_noticia");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/confirmar_noticia", method = { RequestMethod.GET })
	public ModelAndView confirmarNoticia(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_noticia");
		try {
			model.setViewName("confirmarNoticia");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_noticia", method = { RequestMethod.GET })
	public ModelAndView confirmarDeleteNoticia(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_noticia");
		try {
			model.setViewName("confirmarDeleteNoticia");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/registrar_tag.html", method = RequestMethod.GET)
	public ModelAndView registrarTag(ModelMap modelMap,HttpSession session) {
		LOGGER.info("registrarTag");
		ModelAndView model = new ModelAndView();
		model.setViewName("registrar_tag");
		return model;
	}
	
	@RequestMapping(value = "/actualizar_tag", method = { RequestMethod.GET })
	public ModelAndView actualizarTag(ModelMap modelMap,@RequestParam String id) {
		ModelAndView model = new ModelAndView();
		
		LOGGER.info("actualizarTag");
		LOGGER.info(id);
		try {
			modelMap.addAttribute("idTag", id);			
			model.addAllObjects(modelMap);
			model.setViewName("registrar_tag");

		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
		
	}
	
	@RequestMapping(value = "/tags.html", method = RequestMethod.GET)
	public ModelAndView tags(ModelMap modelMap,HttpSession session) {
		LOGGER.info("tags");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("tags");
		return model;
	}
	
	@RequestMapping(value = "/confirmar_tag", method = { RequestMethod.GET })
	public ModelAndView confirmarTag(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_tag");
		try {
			model.setViewName("confirmarTag");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value = "/confirmar_delete_tag", method = { RequestMethod.GET })
	public ModelAndView confirmarDeleteTag(HttpServletRequest context) {
		ModelAndView model = new ModelAndView();
		HttpSession session = context.getSession();
		
		LOGGER.info("confirmar_delete_tag");
		try {
			model.setViewName("confirmarDeleteTag");
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+": "+e.getCause());
		}
		return model;
	}
	
	@RequestMapping(value="/rest/getTags.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseListBean<TagBean> getTag(){
		LOGGER.info("getTag");		
		List<TagBean> tagList = null;
		ResponseListBean<TagBean> result = new ResponseListBean<TagBean>();	
		try {	
			tagList = tagService.getAll();
			result.setRows(tagList);
		} catch (Exception e) {
			result.setEstadoRespuesta(e.getMessage());
			result.setMensajeRespueta(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/getTag.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<TagBean> getTag(@RequestParam("id") String id){
		LOGGER.info("getTag");		
		TagBean tag = null;
		ResponseObjectBean<TagBean> result = new ResponseObjectBean<>();
		try {	
			tag = new TagBean();
			tag.setId(Integer.parseInt(id));
			tag = tagService.getTagById(tag);
			result.setObjeto(tag);
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/insertarTag.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> insertarTag(@RequestBody TagBean bean){
		LOGGER.info("insertarTag");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {
			if(!tagService.validRepiteRegister(bean)) {
				if(tagService.insertarTag(bean)) {
					result.setTipoMensaje("SUCCESS");
					result.setMensaje("Tag registrada correctamente");
				}else {
					result.setTipoMensaje("ERROR");
					result.setMensaje("Error al registrar tag");
				}
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Tag ya existe");
			}
			
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/updateTag.json", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> updateTag(@RequestBody TagBean bean){
		LOGGER.info("updateTag");		
		LOGGER.info(bean.toString());
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			TagBean obj = tagService.getTagById(bean);
			if(obj!=null) {
				if(!obj.getDescripcion().trim().equalsIgnoreCase(bean.getDescripcion().trim())) {
					if(!tagService.validRepiteRegister(bean)) {
						if(tagService.updateTag(bean)) {
							result.setTipoMensaje("SUCCESS");
							result.setMensaje("Tag actualizada correctamente");
						}else {
							result.setTipoMensaje("ERROR");
							result.setMensaje("Error al actualizadar tag");
						}	
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Tag ya existe");
					}
				}else {
					if(tagService.updateTag(bean)) {
						result.setTipoMensaje("SUCCESS");
						result.setMensaje("Tag actualizada correctamente");
					}else {
						result.setTipoMensaje("ERROR");
						result.setMensaje("Error al actualizadar tag");
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
	
	@RequestMapping(value="/rest/deleteTag.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteTag(@RequestParam("id")String id){
		LOGGER.info("deleteTag");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			TagBean bean = new TagBean();
			bean.setId(Integer.parseInt(id));
			if(tagService.deleteTag(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Tag numero "+id+" eliminada correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar tag");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
	
	@RequestMapping(value="/rest/deleteComentario.json", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public ResponseObjectBean<String> deleteComentario(@RequestParam("id")String id){
		LOGGER.info("deleteComentario");		
		LOGGER.info("ID->"+id);
		ResponseObjectBean<String> result = new ResponseObjectBean<String>();	
		try {	
			ComentarioBean bean = new ComentarioBean();
			bean.setId(Integer.parseInt(id));
			if(comentarioService.deleteComentario(bean)) {
				result.setTipoMensaje("SUCCESS");
				result.setMensaje("Comentario eliminado correctamente");
			}else {
				result.setTipoMensaje("ERROR");
				result.setMensaje("Error al eliminar comentario");
			}
		} catch (Exception e) {
			result.setTipoMensaje(e.getMessage());
			result.setMensaje(e.getCause().toString());
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}
}
