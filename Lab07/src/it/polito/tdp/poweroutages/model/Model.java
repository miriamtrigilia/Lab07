package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	List<Blackout> listaParziale;
	List<Blackout> best;
	List<Blackout> listaeventi;
	
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<Blackout> getSequenza(Nerc value, int anni, int ore) {
		
		this.best = null;
		
		int nercId = value.getId();
		
		// inizializzo la lista con il primo elemento, qualsiasi va bene.
		listaParziale = new ArrayList<Blackout>();
		listaeventi = this.podao.getListaBlackoutByNerc(nercId);
		
		this.ricorri(listaParziale, nercId, anni, ore);
	
		return this.best; 
	}

	private void ricorri(List<Blackout> parziale, int nercId, int anni, int ore) {

		
		int vittimeTotali = calcolaVittime(parziale);
		
		if(this.best == null || vittimeTotali > calcolaVittime(best))
			this.best = new ArrayList<Blackout>(parziale);
		
		for(Blackout b: listaeventi) {
			if(this.aggiuntaValida(b,parziale,anni,ore) && !parziale.contains(b)) {
				parziale.add(b);
				this.ricorri(parziale, nercId, anni, ore);
				parziale.remove(b); 
			}
		}
				
	}
	
	public int calcolaVittime(List<Blackout> parziale) {
		int somma = 0;
		for(Blackout b : parziale) {
			somma += b.calcolaVittime();
		}
		return somma;
	}
	
	public int calcolaOreTotali(List<Blackout> parziale) {
		int somma = 0;
		for(Blackout b : parziale) {
			somma += b.getOreBlackout();
		}
		return somma;
	}

	private boolean aggiuntaValida(Blackout b,List<Blackout> lista, int anni, int ore) {
		if(lista.size()>0) {
			if(b.getDataI().isBefore(lista.get(lista.size()-1).getDataI())) // aggiungo solo se sto considerando un anno piu grande dell'ultimo
				return false;
			if((lista.get(0).getDataI().until(b.getDataI(), ChronoUnit.YEARS)) >= anni)
				return false;
		}
	
		if((b.getOreBlackout()+this.calcolaOreTotali(lista)) > ore ) {
			return false;
		}
		
		return true;
		
	}

	
	

}
