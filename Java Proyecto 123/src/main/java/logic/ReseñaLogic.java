package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class Rese�aLogic {
	
	private DataRese�a db= new DataRese�a();
	
	public LinkedList<Rese�a> getAll(){
		return db.getAll();
	}
	
	public Rese�a getOne(Rese�a obj) {
		return this.getOne(obj.getId());
		
	} 
	
	public Rese�a getOne(int i) {
		return db.getOne(i);
		
	} 
		
	public Rese�a add(Rese�a obj) {
			return db.add(obj);
		
	}
	
	public void update(Rese�a obj) {
		 db.update(obj);
		
	}
	public void delete(Rese�a obj) {
		 db.delete(obj);
		
	}

}
