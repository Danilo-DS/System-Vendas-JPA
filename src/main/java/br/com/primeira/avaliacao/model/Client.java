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

/**
 * @author DS
 *
 */

@Entity(name = "TB_CLIENT")
@NamedQueries({@NamedQuery(name = "Client.findAll", query = "SELECT C FROM TB_CLIENT C ORDER BY C.id")})

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client implements Serializable {

	private static final long serialVersionUID = -1090660103241306264L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "CPF")
	private String cpf;
	
}
