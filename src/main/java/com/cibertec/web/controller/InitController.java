package com.cibertec.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cibertec.app.UtilScraping;
import com.cibertec.bean.CategoriaBean;
import com.cibertec.bean.NoticiaLink;
import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.RolBean;
import com.cibertec.bean.TagBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.service.CategoriaService;
import com.cibertec.service.OpcionService;
import com.cibertec.service.TagService;
import com.cibertec.service.UsuarioService;

@RestController
public class InitController {
	private Logger LOGGER = LoggerFactory.getLogger(InitController.class);
	
	@Autowired
	OpcionService opcionService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	UsuarioService usuarioService;
	


	public static final String url = "https://www.marca.com/tenis.html";
	public static final int maxPages = 4;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView init(HttpSession session) {
		LOGGER.info("init");
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/enlaces.html", method = RequestMethod.GET)
	public ModelAndView scrapping(ModelMap modelMap,HttpSession session) {
		LOGGER.info("home");		
		ModelAndView model = new ModelAndView();
		

		List<NoticiaLink> lista = new ArrayList<NoticiaLink>();
		

		//for (int i = 1; i < maxPages; i++) {
			 	 	 	
			String urlPage = url;// + i;

			// Compruebo si me da un 200 al hacer la petición
			if (UtilScraping.getStatusConnectionCode(urlPage) == 200) {

				// Obtengo el HTML de la web en un objeto Document2
				Document document = UtilScraping.getHtmlDocument(urlPage);

				// Busco todas las historias de meneame que estan dentro de:
				Elements entradas = document.select(".auto-items .content-item");

				NoticiaLink obj = null;
				// Paseo cada una de las entradas
				for (Element elem : entradas) {
					String imagen  = elem.getElementsByClass("multimedia-item").html();
					String titulo = elem.getElementsByClass("mod-title").text();
					String autor = elem.getElementsByClass("mod-author").text();
					String fecha = "";
					
					obj = new NoticiaLink();
					obj.setTitulo(titulo);
					obj.setAutor(autor);
					obj.setFecha(fecha);
					obj.setImagen(imagen);
					
					if(noExiste(lista, obj)){
							lista.add(obj);		
					}
				}

			} else {
				//break;
			}
		//}

		//enviar a la vista
		model.addObject("listaNoticias", lista);
		
		
		model.setViewName("enlaces");
		return model;
	}
	private boolean noExiste(List<NoticiaLink> data ,  NoticiaLink x){
		for (NoticiaLink aux : data) {
			if(aux.getTitulo() == x.getTitulo() && aux.getFecha() == x.getFecha() && aux.getAutor() == x.getAutor()){
				return false;
			}
		}
		return true;
	}
	
	
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView home(ModelMap modelMap,HttpSession session) {
		LOGGER.info("home");		
		ModelAndView model = new ModelAndView();
		List<OpcionBean> lista = (List<OpcionBean>) session.getAttribute("listaOpciones");
		//CARGANDO LISTA TIPOS
		List<TagBean> tagList = tagService.getAll();
		List<CategoriaBean> categoriaList = categoriaService.getAll();
		List<RolBean> rolList = usuarioService.getAllRol();
		session.setAttribute("tagList", tagList);
		session.setAttribute("categoriaList", categoriaList);
		session.setAttribute("rolList", rolList);
		
		if(lista==null || lista.size()==0) {
			UsuarioBean bean = new UsuarioBean();
			bean.setUsername("anom");
			bean.setPassword("anom");
			List<OpcionBean> listaOpciones = opcionService.getAllByUsername(bean);
			LOGGER.info(listaOpciones.toString());
			session.setAttribute("listaOpciones", listaOpciones);	
		}
		model.setViewName("home");
		return model;
	}
}
