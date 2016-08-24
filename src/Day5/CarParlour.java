package Day5;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="PARLOUR")
@Table(name="PARLOUR")
public class CarParlour {
	
	@Id
	@Column(name="SHOWROOM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id = 0;
	
	@OneToMany
	@JoinTable(name="CAR_PARLOUR",joinColumns= @JoinColumn(name="SHOWROOM_ID"))
	@Cascade(CascadeType.ALL)
	private List<Car> cars = null;
	
	private String manager;
	private String location;

	public CarParlour(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
