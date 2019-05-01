package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "code",
    "unit",
    "price"})
@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @Column
    @JsonProperty("name")
    private String name;
    @Column
    @JsonProperty("code")
    private String code;
    @Column
    @JsonProperty("unit")
    private Integer unit;
    @Column
    @JsonProperty("price")
    private BigDecimal price;
    @Column
    @JsonIgnore
    private Integer amount;

    public Stock() {

    }

    public Stock(Integer id, String name, String code, Integer unit, BigDecimal price, Integer amount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("unit")
    public Integer getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    public Integer getAmount() {
        return amount;
    }

    @JsonIgnore
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
