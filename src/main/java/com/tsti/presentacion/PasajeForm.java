package com.tsti.presentacion;

public class PasajeForm {
    private Long vueloId;
    private Long clienteId;

    public PasajeForm() {
    }

    public PasajeForm(Long vueloId, Long clienteId) {
        this.vueloId = vueloId;
        this.clienteId = clienteId;
    }

    public Long getVueloId() {
        return vueloId;
    }

    public void setVueloId(Long vueloId) {
        this.vueloId = vueloId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}