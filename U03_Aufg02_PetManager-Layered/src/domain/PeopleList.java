package domain;

import java.util.List;
import java.util.Observable;


public class PeopleList extends Observable {
	
	private List<Person> personList;
	
	public PeopleList() {
		personList = Person.findAll();
	}
	
	public List<Person> getPeopleList() {
		return personList;
	}
	
	public Person getPersonAt(int position) {
		return personList.get(position);
	}
	
	public void addPerson(Person person) {
		personList.add(0,person);
		person.save();
		setChanged();
		ListUpdate up = new ListUpdate();
		up.addInserts(new int[] {0});
		notifyObservers(up);
	}
	
	public void removePeople(List<Person> pepoleToRemove) {
		int[] removeIndexes = new int[pepoleToRemove.size()];
		for(int i = 0; i < pepoleToRemove.size(); i++) {
			Person p = pepoleToRemove.get(i);
			removeIndexes[i] = personList.indexOf(p);
			p.delete();
		}
		personList.removeAll(pepoleToRemove);
		setChanged();
		ListUpdate up = new ListUpdate();
		up.addDeletes(removeIndexes);
		notifyObservers(up);
	}
	
	public void setPerson(int position, Person personToReplace) {
		personList.set(position, personToReplace);
		setChanged();
		ListUpdate up = new ListUpdate();
		up.addUpdates(new int[] {position});
		notifyObservers(up);
	}
	
	public double getAveragePetsPerPerson() {
		double sum = 0;
		for(Person p : personList) {
			sum += p.getPets().size();
		}
		return sum / personList.size();
	}
}
