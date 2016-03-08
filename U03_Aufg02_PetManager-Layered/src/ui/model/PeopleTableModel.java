package ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domain.ListUpdate;
import domain.PeopleList;
import domain.Person;

public class PeopleTableModel extends AbstractTableModel implements Observer{
	private static final long serialVersionUID = -7806713335094648742L;
	private PeopleList peopleList;
	
	private String[] columns = {"ID", "Name","Job","Pets"};

	public PeopleTableModel(PeopleList peopleList) {
		this.peopleList = peopleList;
		peopleList.addObserver(this);
	}
	
	public void removePersonAtRow(int[] rowsView, int[] rowsModel) {
		List<Person> toRemove = new ArrayList<Person>();
		for(int i = 0; i < rowsModel.length ; ++i) {
			toRemove.add(peopleList.getPersonAt(rowsModel[i]));
		}
		peopleList.removePeople(toRemove);
	}
	
	public void reloadPerson(Person personToReload){
		Person temp = Person.findByID(personToReload.getID());
		for(int i = 0; i < peopleList.getPeopleList().size(); i++){
			if(peopleList.getPersonAt(i).equals(personToReload)){
				peopleList.setPerson(i, temp);
				break;
			}
		}
	}


	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public int getRowCount() {
		return peopleList.getPeopleList().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Person p = peopleList.getPersonAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getID();
		case 1:
			return p.name;
		case 2:
			return p.job;
		case 3:
			return p.getPets().size();
		default:
			return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex > 0 && columnIndex < 3;
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Person p = peopleList.getPersonAt(rowIndex);
		switch (columnIndex) {
		case 1:
			p.name = value.toString();
			break;
		case 2:
			p.job = value.toString();
			break;
		}
		peopleList.setPerson(rowIndex, p);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof ListUpdate) {
			ListUpdate update = (ListUpdate) arg;
			if(update.hasChanges()) {
				for(int updated : update.getUpdatedElements()) {
					fireTableRowsUpdated(updated, updated);
				}
				if(update.getInsertedElements().length == 1) {
					int pos = update.getInsertedElements()[0];
					fireTableRowsInserted(pos, pos);
				} else {
					fireTableDataChanged();
				}
				if(update.getDeletedElements().length == 1) {
					int pos = update.getDeletedElements()[0];
					fireTableRowsDeleted(pos, pos);
				} else {
					fireTableDataChanged();
				}			
			} else {
				fireTableDataChanged();
			} 
		}
	}
	


	public Person getPersonAt(int index) {
		return peopleList.getPersonAt(index);
	}
	
	
}
