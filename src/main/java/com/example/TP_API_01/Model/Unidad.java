package com.example.TP_API_01.Model;


import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Views.EdificioView;
import com.example.TP_API_01.Views.UnidadView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "unidades")
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private Integer identificador;
    @Column(name = "piso", length = 5, nullable = false)
    private String piso;
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;
    @Column(name = "habitado", length = 1, nullable = false)
    private boolean habitado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "codigoedificio")
    private Edificio edificio;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "duenios",
            joinColumns = @JoinColumn(name = "identificador"),
            inverseJoinColumns = @JoinColumn(name = "documento")
    )
    private List<Persona> duenios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "inquilinos",
            joinColumns = @JoinColumn(name = "identificador"),
            inverseJoinColumns = @JoinColumn(name = "documento")
    )
    private List<Persona> inquilinos;



    public Unidad() {
    }

    public Unidad(int identificador, String piso, String numero, Edificio edificio) {
        this.identificador = identificador;
        this.piso = piso;
        this.numero = numero;
        this.habitado = false;
        this.edificio = edificio;
        this.duenios = new ArrayList<Persona>();
        this.inquilinos = new ArrayList<Persona>();
    }

    public void transferir(Persona nuevoDuenio) {
        duenios = new ArrayList<Persona>();
        duenios.add(nuevoDuenio);
    }

    public void agregarDuenio(Persona duenio) {
        duenios.add(duenio);
    }

    public void alquilar(Persona inquilino) throws UnidadException {
        if(!this.habitado) {
            this.habitado = true;
            inquilinos = new ArrayList<Persona>();
            inquilinos.add(inquilino);
        }
        else
            throw new UnidadException("La unidad esta ocupada");
    }

    public void agregarInquilino(Persona inquilino) {
        inquilinos.add(inquilino);
    }

    public boolean estaHabitado() {
        return habitado;
    }

    public void liberar() {
        this.inquilinos = new ArrayList<Persona>();
        this.habitado = false;
    }

    public void habitar() throws UnidadException {
        if(this.habitado)
            throw new UnidadException("La unidad ya esta habitada");
        else
            this.habitado = true;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getPiso() {
        return piso;
    }

    public String getNumero() {
        return numero;
    }


    public Edificio getEdificio() {
        return edificio;
    }

    public List<Persona> getDuenios() {
        return duenios;
    }



    public List<Persona> getInquilinos() {
        return inquilinos;
    }



    public UnidadView toView() {
        EdificioView auxEdificio = edificio.toView();
        return new UnidadView(identificador, piso, numero, habitado, auxEdificio);
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
