package com.mindtree.demoRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlienRepository {
	List<Alien> aliens;
static Connection con;
	public AlienRepository() {
		/*aliens=new ArrayList<Alien>();
		Alien a1 = new Alien();
		a1.setName("Name");
		a1.setPoints(50);
		a1.setId(60);

		Alien a2 = new Alien();
		a2.setName("Name2");
		a2.setPoints(60);
		a2.setId(50);
		aliens.add(a1);
		aliens.add(a2);*/
		String url= "jdbc:mysql://localhost:3306";
		String username="root";
		String password="root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url+"/restdb",username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public 	List<Alien> getAliens(){
		return aliens;
	}
	
	public Alien getRepository(int id){
		for(Alien a: aliens){
			if(a.getId()==id){
				return a;
			}
		}
		return null;
	}

	public void create(Alien a1) {
		// TODO Auto-generated method stub
		
	}

	public Alien getAlien(int id) {
		System.out.println("getAlien from repo called");
		for(Alien a: aliens){
			System.out.println(a.getId());
			if(a.getId()==id){
				System.out.println("id found");
				return a;
			}
		}
		return null;
	}
}
