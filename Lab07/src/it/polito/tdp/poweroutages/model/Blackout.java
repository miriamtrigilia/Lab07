package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Blackout {
	
		
		int nerc_id;
		int persone;
		LocalDateTime dataI;
		LocalDateTime dataF;
		
		public Blackout(int nerc_id, int customers, LocalDateTime dataI, LocalDateTime dataF) {
			super();
			this.nerc_id = nerc_id;
			this.persone = customers;
			this.dataI = dataI;
			this.dataF = dataF;
		}
		
		public int getNerc_id() {
			return nerc_id;
		}
		public void setNerc_id(int nerc_id) {
			this.nerc_id = nerc_id;
		}
		public int calcolaVittime() {
			return persone;
		}
		public void setCustomers(int customers) {
			this.persone = customers;
		}
		public LocalDateTime getDataI() {
			return dataI;
		}
		public void setDataI(LocalDateTime dataI) {
			this.dataI = dataI;
		}
		public LocalDateTime getDataF() {
			return dataF;
		}
		public void setDataF(LocalDateTime dataF) {
			this.dataF = dataF;
		}
		
		public int getOreBlackout() {
			return (int)this.dataI.until(dataF, ChronoUnit.HOURS);
		}

		@Override
		public String toString() {
			return dataF.getYear()+" "+dataI+" "+dataF+" "+this.getOreBlackout()+" " + persone;
			//return dataI.toString();
		}
		
		
		

}
