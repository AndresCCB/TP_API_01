package com.example.TP_API_01.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name ="administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    private Persona documento;

    public Administrador() {
    }

    public Administrador(Integer id, Persona documento) {
        this.id = id;
        this.documento = documento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getDocumento() {
        return documento;
    }

    public void setDocumento(Persona documento) {
        this.documento = documento;
    }
}
