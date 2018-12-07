package com.codingdojo.mvc.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, message="Title must be present")
    private String title;
    @Size(min = 1, message="Preparation must be present")
    private String prep;
    private String ingredient;

    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    
    public Recipe() {
    	
    }
    
    public Recipe(String title, String prep, String ingredient) {
        this.title = title;
        this.prep = prep;
        this.ingredient = ingredient;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.setUpdatedAt(new Date());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrep() {
		return prep;
	}

	public void setPrep(String prep) {
		this.prep = prep;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
}