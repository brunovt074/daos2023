package com.tsti.dto;

public class PasajeResDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private VueloDTO vuelo;

    public PasajeResDTO(Long id, ClienteResponseDTO cliente, VueloDTO vuelo) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public VueloDTO getVuelo() {
        return vuelo;
    }

    public void setVuelo(VueloDTO vuelo) {
        this.vuelo = vuelo;
    }
}
