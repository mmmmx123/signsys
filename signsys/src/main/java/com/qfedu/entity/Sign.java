package com.qfedu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Sign {
    private Integer id;

    private String name;

    private String morningsign;

    private String eveningsign;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date morningsigntime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date eveningsigntime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMorningsign() {
        return morningsign;
    }

    public void setMorningsign(String morningsign) {
        this.morningsign = morningsign == null ? null : morningsign.trim();
    }

    public String getEveningsign() {
        return eveningsign;
    }

    public void setEveningsign(String eveningsign) {
        this.eveningsign = eveningsign == null ? null : eveningsign.trim();
    }

    public Date getMorningsigntime() {
        return morningsigntime;
    }

    public void setMorningsigntime(Date morningsigntime) {
        this.morningsigntime = morningsigntime;
    }

    public Date getEveningsigntime() {
        return eveningsigntime;
    }

    public void setEveningsigntime(Date eveningsigntime) {
        this.eveningsigntime = eveningsigntime;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}