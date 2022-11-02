package pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto;

import java.io.Serializable;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity.Dish;

// Data Transfer Object, to communicate with frontend
public class DishDto implements Serializable {
	private long id;
	private String name;
	private String description;
	private String unitPrice;
	private String unitWeight;
	private boolean vegetarian;

	public DishDto() {
	}

	public DishDto(long id, String name, String description, String unitPrice, String unitWeight, boolean vegetarian) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitWeight = unitWeight;
		this.vegetarian = vegetarian;
	}

	public DishDto(Dish dish) {
		this(dish.getId(), dish.getName(), dish.getDescription(),
				dish.getUnitPrice(), dish.getUnitWeight(), dish.isVegetarian());
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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
				"DishDto[id=%d, name='%s', description='%s', unitPrice='%s', unitWeight='%s', vegetarian='%s']", id, name, description, unitPrice, unitWeight,vegetarian);
	}
}
