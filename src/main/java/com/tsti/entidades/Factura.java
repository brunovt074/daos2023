package com.tsti.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="facturas")
public class Factura {

	@Id
	private Long nro_factura;
}
