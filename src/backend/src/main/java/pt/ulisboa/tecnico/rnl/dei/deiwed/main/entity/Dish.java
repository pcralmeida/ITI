package pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto.DishDto;

@Entity
@Table(name = "dishes")
public class Dish {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "unitPrice", nullable = false)
	private String unitPrice;

	@Column(name = "unitWeight", nullable = false)
	private String unitWeight;

	@Column(name = "vegetarian", nullable = false)
	private boolean vegetarian;

	// TODO: maybe add more fields? ...or maybe not? what makes sense here?
	// TODO: add a list of events this attendee is attending? (by copilot)
	// TODO: add if the attendee is vegan/intolerant?

	protected Dish() {
	}

	public Dish(String name, String description, String unitPrice, String unitWeight, boolean vegetarian) {
		this.name = name;
		this.description = description;
        this.unitPrice = unitPrice;
        this.unitWeight = unitWeight;
        this.vegetarian = vegetarian;
	}

	public Dish(DishDto dishDto) {
		this(dishDto.getName(), dishDto.getDescription(), dishDto.getUnitPrice(), dishDto.getUnitPrice(), dishDto.isVegetarian());
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitWeight() {
        return this.unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

	public boolean isVegetarian() {
		return this.vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	@Override
	public String toString() {
		return String.format(
				"Attendee[id=%d, name='%s', description='%s', unitPrice='%s', unitWeight='%s', vegetarian='%s']", id, name, description, unitPrice, unitWeight, vegetarian);
	}
}