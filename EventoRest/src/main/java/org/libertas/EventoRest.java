package org.libertas;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.EventoDao;
import org.libertas.pojo.Evento;


import com.google.gson.Gson;

/**
 * Servlet implementation class ExemploRest
 */
@WebServlet("/EventoRest/*")
public class EventoRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private void enviaResposta(HttpServletResponse response, String json, int codigo) throws IOException {
		response.addHeader("Content-Type", "application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		
		response.setStatus(codigo);
		
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(json.getBytes("UTF-8"));
		out.close();
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoRest() {
       super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Evento> lista = new LinkedList<>();
		String json;
		int id = 0;
			if (request.getPathInfo()!=null) {
				String info = request.getPathInfo().replace("/", "");
				id = Integer.parseInt(info);
		}
		
		Gson gson = new Gson();
		EventoDao pdao = new EventoDao();
		
		if (id == 0) {
			lista = pdao.listar();
			// converte o objeto JAVA em JSON
			 json = gson.toJson(lista);
		} else {
			Evento p = pdao.consultar(id);
			json = gson.toJson(p);
		}
		
		
		// envia resposta
		enviaResposta(response, json, 200);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String json = request.getReader().lines().collect(Collectors.joining());
			Gson gson = new Gson();
			Evento p = (Evento) gson.fromJson(json, Evento.class);
			//inserir novo evento
			EventoDao pdao = new EventoDao();
			pdao.inserir(p);
			// enviar resposta sucesso
			enviaResposta(response, "Inserido com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String json = request.getReader().lines().collect(Collectors.joining());
			Gson gson = new Gson();
			Evento e = (Evento) gson.fromJson(json, Evento.class);
			//alterando evento
			EventoDao pdao = new EventoDao();
			pdao.alterar(e);
			// enviar resposta sucesso
			enviaResposta(response, "Alterado com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}

	
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//pega o ID a ser excluido, passado na URL
			int id = 0;
			if (request.getPathInfo()!=null) {
				String info = request.getPathInfo().replace("/", "");
				id = Integer.parseInt(info);
			}
			
			Evento p = new Evento();
			p.setIdevento(id);
			//deleta evento
			EventoDao pdao = new EventoDao();
			pdao.excluir(p);
			// enviar resposta sucesso
			enviaResposta(response, "Excluído com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}
}
