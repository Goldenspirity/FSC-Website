package com.sdzee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.News;
import com.sdzee.beans.Tournament;
import com.sdzee.dao.DAOFactory;
import com.sdzee.daoImplementations.NewsDAOImplementation;
import com.sdzee.daoImplementations.TournamentDAOImplementation;

@SuppressWarnings("serial")
public class Testss extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test createTournament
		DAOFactory df = DAOFactory.getInstance();
		/*TournamentDAOImplementation td1 = new TournamentDAOImplementation(df);
		Tournament t = new Tournament();
		String[] s = {"Team 1","Team 2","Team 3","Team 4","Team 5","Team 6","Team 7","Team 8"};
		t.setNumberOfTeams(8);
		t.setTeams(s);
		t.setId(td1.lastId() + 1);
		t.setTitle("FSC CUP 1");
		Date date = new Date();
		String date2 = "";
		String OLD_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		date2 = sdf.format(date);
		// System.out.println(date);

		t.setDateTournament(date2);
		t.setScores(new HashMap<Integer, Integer[]>());
		for (int i = 0; i < 24; i++) {
			Integer[] ts = {-1,-1};
			t.getScores().put(i,ts);
		}
		t.setSpots(new HashMap<Integer, String>());
		for (int i = 0; i < 32; i++) {
			if (i > 7 && i < 16) {
				t.getSpots().put(i, s[i-8]);
			} else {
				t.getSpots().put(i,  null);
			}
		}
		td1.createTournament(t);*/
		//td1.createTournament(t);

		
		// Test update
		// Tournament t3 = td1.getTournament(1);

		// td1.updateTournament(t3);
		
		NewsDAOImplementation nd = new NewsDAOImplementation(df);
		
		News n = new News();
		String content = "update";
		Date date = new Date();
		String date2 = "";
		String OLD_FORMAT = "yy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		date2 = sdf.format(date);
		String title = "title3";
		String image = "url";
		n.setId(1);
		n.setContent(content);
		n.setDate(date2);
		n.setTitle(title);
		n.setImage(image);
		
		// nd.createNews(n);
		// nd.updateNews(n);
		n = nd.getNews(1);
		//nd.deleteNews(4);
		

		
		// Test getTournament
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// DateTime dt = new DateTime();
		// DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );

		// String dts = formatter.print(dt); 
		// out.println(dts);
		// DAOFactory df = DAOFactory.getInstance();
		// TournamentDAOImplementation td = new TournamentDAOImplementation(df);
		// out.println(t.getScores().get(1)[0] + "<br />");
		// showTournament(1,out, td1);
		
		// Test inProgress
		boolean b = nd.exists(6);
		out.println(n.getId());
		out.println("<br />");
		out.println(n.getTitle());
		out.println("<br />");
		out.println(n.getContent());
		out.println("<br />");
		out.println(n.getImage());
		out.println("<br />");
		out.println(n.getDate());
		out.println("<br />");
		out.println(n.getLastEdit());
		
		// Test delete
		/*TournamentDAOImplementation td2 = new TournamentDAOImplementation(df);
		Tournament t4 = td2.getTournament(1);
		td.deleteTournament(t4);*/
				
	}
	
	private void showTournament(int id, PrintWriter out, TournamentDAOImplementation td) {
		Tournament t2 = new Tournament();
		t2 = td.getTournament(id);
		out.println("Id : " + t2.getId() + "<br />");
		out.println("Title : " + t2.getTitle() + "<br />");
		out.println("Number of team : " + t2.getNumberOfTeams() + "<br />");
		out.println("Date : " + t2.getDateTournament()+ "<br />");
		String[] teams = t2.getTeams();
		Map<Integer, String> spots = t2.getSpots();
		Map<Integer, Integer[]> scores = t2.getScores();
		for (int i = 0; i < teams.length; i++) {
			out.println("Team n°" + i + " : " + teams[i]+ "<br />");
		}
		for (int i = 0; i < spots.size(); i++) {
			out.println("Spot n°" + i + " : " + spots.get(i)+ "<br />");
		}
		for (int i = 0; i < scores.size(); i++) {
			out.println("Match n° " + i + ":"+ "<br />");
			out.println("Equipe du haut : " + scores.get(i)[0]+ "<br />");
			out.println("Equipe du bas : " + scores.get(i)[1]+ "<br />");
		}
	}

}
