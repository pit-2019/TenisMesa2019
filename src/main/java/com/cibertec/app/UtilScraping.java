package com.cibertec.app;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UtilScraping {

	private static final Log log = LogFactory.getLog(UtilScraping.class);
	
	/**
	 * Con este m�todo compruebo el Status code de la respuesta que recibo al hacer la petici�n
	 * EJM:
	 * 		200 OK					300 Multiple Choices
	 * 		301 Moved Permanently	305 Use Proxy
	 * 		400 Bad Request			403 Forbidden
	 * 		404 Not Found			500 Internal Server Error
	 * 		502 Bad Gateway			503 Service Unavailable
	 * @param url
	 * @return Status Code
	 */
	public static int getStatusConnectionCode(String url) {
		
		Response response = null;
		
		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			log.info("Excepci�n al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}
	
	
	/**
	 * Con este m�todo devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitirá parsearlo con los m�todos de la librelia JSoup
	 * @param url
	 * @return Documento con el HTML
	 */
	public static Document getHtmlDocument(String url) {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			log.info("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
		}

		return doc;

	}
	
	
}
