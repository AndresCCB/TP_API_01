package com.example.TP_API_01.Model;



import com.example.TP_API_01.Views.EdificioView;
import com.example.TP_API_01.Views.Estado;
import com.example.TP_API_01.Views.ImagenView;
import com.example.TP_API_01.Views.ReclamoView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reclamos")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReclamo")
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "documento")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "codigo")
    private Edificio edificio;
    @Column(name = "ubicacion", length = 300)
    private String ubicacion;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "identificador")
    private Unidad unidad;

    @Transient
    private Estado estado;

    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;

    public Reclamo() {imagenes = new ArrayList<Imagen>(); }

    public Reclamo(Persona persona, Edificio edificio, String ubicacion, String descripcion, Unidad unidad) {
        this.persona = persona;
        this.edificio = edificio;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.estado = Estado.nuevo;
        imagenes = new ArrayList<Imagen>();
    }

    public void agregarImagen(String direccion, String tipo) {
        Imagen imagen = new Imagen(direccion, tipo);
        imagenes.add(imagen);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public List<Imagen> getImagenes(){
        return this.imagenes;
    }

    public void cambiarEstado(Estado estado) {
        this.estado = estado;
    }

    public ReclamoView toView() {
        List<ImagenView> viewimagen = new ArrayList<>();

        for (Imagen imagen: imagenes){
            viewimagen.add(imagen.toView());
        }

        return new ReclamoView(numero,persona.toView(),edificio.toView(), ubicacion,descripcion,unidad.toView(),estado,viewimagen);
    }
}

