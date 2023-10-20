package com.example.TP_API_01.Model;

import jakarta.persistence.*;

@Entity
@Table(name ="administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento", referencedColumnName = "id")
    private String documento;

    public Administrador() {
    }

    public Administrador(Integer id, String documento) {
        this.id = id;
        this.documento = documento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
