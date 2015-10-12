package com.danco.botva;

public class Enemy {
private Integer id;
private Float value;

public Enemy(Integer id, Float value) {
	this.id = id;
	this.value = value;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Float getValue() {
	return value;
}
public void setValue(Float value) {
	this.value = value;
}
@Override
public String toString() {
	return "Enemy [id=" + id + ", value=" + value + "]";
}

}
