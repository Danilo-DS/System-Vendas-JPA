package br.com.primeira.avaliacao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DS
 *
 */

@Entity(name = "TB_SALE")
@NamedQueries(value = {@NamedQuery(name = "Sale.findAll", query = "SELECT S FROM TB_SALE S ORDER BY S.id")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", foreignKey = @ForeignKey(name = "FK_SALE_CLIENT"))
	private Client client;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "SALE_PRODUCTS", joinColumns = {@JoinColumn(name = "ID_SALE", foreignKey = @ForeignKey(name = "FK_SALE"))},
	inverseJoinColumns = {@JoinColumn(name = "ID_SALE_PRODUCT", foreignKey = @ForeignKey(name = "FK_SALEPRODUCT"))})
	private List<SaleProduct> saleProduct;
	
	@Column(name = "QUANTITY_TOTAL_SALE")
	private Integer quantityTotalProduct;
	
	@Column(name = "TOTAL_SALE", columnDefinition = "DECIMAL(10,2)")
	private Double totalSale;
}
