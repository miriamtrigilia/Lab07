package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO dao = new PowerOutageDAO();
		System.out.println(model.getNercList());
		System.out.println(dao.getListaBlackoutByNerc(3));
		
		System.out.println(model.getSequenza(new Nerc(3, "d"),3, 100));
	}
}
