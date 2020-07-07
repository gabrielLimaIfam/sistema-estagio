package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class FichaDeAvaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(nullable = true, name = "media")
	private Integer media;

	public FichaDeAvaliacao(Integer id, Integer media) {
		this.id = id;
		this.media = media;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMedia() {
		return media;
	}

	public void setMedia(Integer media) {
		this.media = media;
	}
}
