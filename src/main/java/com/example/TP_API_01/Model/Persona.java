package com.example.TP_API_01.Model;


import com.example.TP_API_01.Views.PersonaView;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin (origins = "http://localhost:3000")
@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @Column(name = "documento", length = 20, nullable = false)
    private String documento;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "mail", length = 100)
    private String mail;
    @Column(name = "contrasenia", length = 100)
    private String contrasenia;

    public Persona() {}

    public Persona(String documento, String nombre, String mail, String contrasenia) {
        this.documento = documento;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public void cambiarPassword(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }


    public String getMail() {
        return mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public PersonaView toView() {
        return new PersonaView(documento, nombre);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
