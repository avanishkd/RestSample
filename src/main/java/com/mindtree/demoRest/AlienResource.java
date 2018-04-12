package com.mindtree.demoRest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens/")
public class AlienResource {
	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAliens() {
		/*
		 * Alien a1 = new Alien(); a1.setName("Name"); a1.setPoints(50);
		 * 
		 * Alien a2 = new Alien(); a2.setName("Name2"); a2.setPoints(60);
		 * List<Alien> aliens=Arrays.asList(a1,a2); return aliens;
		 */
		/*
		 * System.out.println("getAliens Called..."); return repo.getAliens();
		 */
		ArrayList<Alien> aliens = new ArrayList<Alien>();

		try {

			String sql = "Select * from alien";

			Statement st = AlienRepository.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aliens;

	}

	@GET
	@Path("alien/{id}")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien getAlien(@PathParam("id") int id) {

		/*
		 * System.out.println("getAliens Called..." +id); return
		 * repo.getAlien(id);
		 */

		System.out.println("called getAlien method");
		ArrayList<Alien> aliens = new ArrayList<Alien>();
		Alien a = new Alien();
		try {

			String sql = "Select * from alien where id=" + id;

			Statement st = AlienRepository.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println(rs);

			if (rs.next()) {

				System.out.println("inside resultset");
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@POST
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien createAlien(Alien a1) {
		System.out.println("create alien method called");
		System.out.println(a1);
		create(a1);
		return a1;

	}

	public void create(Alien a1) {
		String sql = "insert into alien values (?,?,?)";
		try {

			System.out.println("create method called");
			PreparedStatement st = AlienRepository.con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PUT
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien updateAlien(Alien a1) {
		System.out.println("update alien method called");
		if (getAlien(a1.getId()).getId() == 0) {
			create(a1);
		} else {
			update(a1);
		}
		System.out.println(a1);
		update(a1);
		return a1;

	}

	public void update(Alien a1) {
		String sql = "update alien set name= ?, point=? where id=?";
		try {

			System.out.println("create method called");
			PreparedStatement st = AlienRepository.con.prepareStatement(sql);

			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DELETE
	@Path("alien/{id}")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien killAlien(@PathParam("id") int id) {
		Alien a1 = getAlien(id);
		if (a1.getId() != 0) {
			delete(id);

		}
		return a1;
	}

	private void delete(int id) {
		String sql = "delete from alien where id =?";
		try {

			System.out.println("delete method called");
			PreparedStatement st = AlienRepository.con.prepareStatement(sql);
			st.setInt(1, id);

			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
