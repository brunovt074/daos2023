package com.tsti.dto;

import java.math.BigDecimal;

/**
 * @author Bruno
 *
 */
public class CotizacionDolarDTO {
    private BigDecimal compra;
    private BigDecimal venta;
    private String agencia;
    private String nombre;
    private BigDecimal variacion;
    private boolean ventaCero;
    private String decimales;

    
    public CotizacionDolarDTO() {
    }
    
    public CotizacionDolarDTO(BigDecimal compra, BigDecimal venta, 
    					String nombre,String decimales) {
        this.compra = compra;
        this.venta = venta;
        this.nombre = nombre;
        this.decimales = decimales;
    }

    public CotizacionDolarDTO(BigDecimal compra, BigDecimal venta, String agencia, String nombre,
            BigDecimal variacion, boolean ventaCero, String decimales) {
        this.compra = compra;
        this.venta = venta;
        this.agencia = agencia;
        this.nombre = nombre;
        this.variacion = variacion;
        this.ventaCero = ventaCero;
        this.decimales = decimales;
    }

    public BigDecimal getCompra() {
        return compra;
    }

    public void setCompra(BigDecimal compra) {
        this.compra = compra;
    }

    public BigDecimal getVenta() {
        return venta;
    }

    public void setVenta(BigDecimal venta) {
        this.venta = venta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getVariacion() {
        return variacion;
    }

    public void setVariacion(BigDecimal variacion) {
        this.variacion = variacion;
    }

    public boolean isVentaCero() {
        return ventaCero;
    }

    public void setVentaCero(boolean ventaCero) {
        this.ventaCero = ventaCero;
    }

    public String getDecimales() {
        return decimales;
    }

    public void setDecimales(String decimales) {
        this.decimales = decimales;
    }
}

