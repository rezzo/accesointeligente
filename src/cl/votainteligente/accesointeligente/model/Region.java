package cl.votainteligente.accesointeligente.model;

import net.sf.gilead.pojo.gwt.LightEntity;

public class Region extends LightEntity {
	private static final long serialVersionUID = -2868652187921539280L;

	private Integer id;
	private Integer number;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}