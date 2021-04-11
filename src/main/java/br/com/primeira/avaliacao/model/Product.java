package br.com.primeira.avaliacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_PRODUCT")
@NamedQueries({@NamedQuery(name = "Product.findAll", query = "SELECT P FROM TB_PRODUCT P ORDER BY P.id")})

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable{

	private static final long serialVersionUID = -5000046140022957662L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "BARCODE")
	private String barCode;
}
