package org.libertas.pojo;

import org.libertas.dao.EventoDao;

public class testEvento {
	public static void main(String[] args) {
		System.out.println("inicio");
		EventoDao edao = new EventoDao();
		edao.listar();
		System.out.println("fim");
	}

}
