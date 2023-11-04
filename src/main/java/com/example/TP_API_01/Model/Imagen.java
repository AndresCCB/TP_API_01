package com.example.TP_API_01.Model;

import com.example.TP_API_01.Views.ImagenView;
import jakarta.persistence.*;

@Entity
@Table(name="imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "path", length = 300, nullable = false)
    private String path;
    @Column(name = "tipo", length = 10)
    private String tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = " idreclamo" )
    private Reclamo reclamo;

    public Imagen() {
    }

    public Imagen(Integer numero, String path, String tipo) {
        this.numero = numero;
        this.path = path;
        this.tipo = tipo;
        this.reclamo = reclamo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public ImagenView toView(){
        return new ImagenView(numero,path,tipo);
    }
}

