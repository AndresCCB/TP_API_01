package com.example.TP_API_01.Model;

import com.example.TP_API_01.Views.EdificioView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="edificios")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name="nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Unidad> unidades;

    public Edificio() {unidades = new ArrayList<>();}

    public Edificio(int codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        unidades = new ArrayList<Unidad>();
    }

    public void agregarUnidad(Unidad unidad) {
        unidades.add(unidad);
    }

    public Set<Persona> habilitados(){
        Set<Persona> habilitados = new HashSet<Persona>();
        for(Unidad unidad : unidades) {
            List<Persona> duenios = unidad.getDuenios();
            for(Persona p : duenios)
                habilitados.add(p);
            List<Persona> inquilinos = unidad.getInquilinos();
            for(Persona p : inquilinos)
                habilitados.add(p);
        }
        return habilitados;
    }



    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public Set<Persona> duenios() {
        Set<Persona> resultado = new HashSet<Persona>();
        for(Unidad unidad : unidades) {
            List<Persona> duenios = unidad.getDuenios();
            for(Persona p : duenios)
                resultado.add(p);
        }

        return resultado;
    }


    public Set<Persona> habitantes() {
        Set<Persona> resultado = new HashSet<Persona>();
        for(Unidad unidad : unidades) {
            if(unidad.estaHabitado()) {
                List<Persona> inquilinos = unidad.getInquilinos();
                if(inquilinos.size() > 0)
                    for(Persona p : inquilinos)
                        resultado.add(p);
                else {
                    List<Persona> duenios = unidad.getDuenios();
                    for(Persona p : duenios)
                        resultado.add(p);
                }
            }
        }
        return resultado;
    }



    public EdificioView toView() {
        return new EdificioView(codigo, nombre, direccion);
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
