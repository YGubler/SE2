package applications;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import domain.PeopleList;

import persistence.ActiveRecordManager;
import ui.PetManagerFrame;
import ui.StatisticWindow;

public class PetManager {
	public static void main(String[] args) {
		createTablesIfMissing();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PeopleList peopleList = new PeopleList();
				PetManagerFrame editView = new PetManagerFrame(peopleList);
				editView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				editView.setVisible(true);
				StatisticWindow toolbar = new StatisticWindow(editView, peopleList);
				toolbar.setVisible(true);
				editView.setLocation(100, 100);		
			}
		});
	}

	private static void createTablesIfMissing() {
		String sql = "select * from people";
		try {
			ActiveRecordManager.execute(sql);
		} catch (SQLException e) {
			try {
				createTables();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private static void createTables() throws SQLException{
		Connection connection = ActiveRecordManager.getConnection();
		Statement stat = connection.createStatement();
		stat.executeUpdate("drop table if exists people;");
		stat.executeUpdate("drop table if exists pet;");
		stat.executeUpdate("create table people (id INTEGER PRIMARY KEY AUTOINCREMENT, name, job);");
		stat.executeUpdate("create table pet (id INTEGER PRIMARY KEY AUTOINCREMENT,owner INTEGER REFERENCES people(id), name, breed);");
	}
}
