package br.com.primeira.avaliacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DS
 *
 */

@Entity(name = "TB_SALE_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Transient
	private Product product;
	
	@Column(name = "DESCRIPTION_PRODUCT")
	private String nameProduct;
	
	@Column(name = "QUANTITY_PRODUCT")
	private Integer quantityProduct;
	
	@Column(name = "PRICE_PRODUCT")
	private Double priceProduct;
}
