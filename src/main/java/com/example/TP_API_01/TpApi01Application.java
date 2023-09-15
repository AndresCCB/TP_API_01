package com.example.TP_API_01;

import com.example.TP_API_01.Controllers.EdificioService;
import com.example.TP_API_01.Controllers.PersonaService;
import com.example.TP_API_01.Controllers.ReclamoService;
import com.example.TP_API_01.Controllers.UnidadService;
import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.ReclamoException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Edificio;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Repositories.EdificioRepository;
import com.example.TP_API_01.Views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class TpApi01Application {

	public static void main(String[] args) {
		//SpringApplication.run(TpApi01Application.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(TpApi01Application.class, args);

		EdificioService edificioService = context.getBean(EdificioService.class);
		UnidadService unidadService = context.getBean(UnidadService.class);
		PersonaService personaService = context.getBean(PersonaService.class);
		ReclamoService reclamoService = context.getBean(ReclamoService.class);

		try {

			int codigoEdificio = 1;

			List<UnidadView> unidadesPorEdificio = edificioService.getUnidadesPorEdificio(codigoEdificio);
			List<PersonaView> habilitadosPorEdificio = edificioService.habilitadosPorEdificio(codigoEdificio);
			List<PersonaView> dueniosPorEdificio = edificioService.dueniosPorEdificio(codigoEdificio);
			List<PersonaView> habitantesPorEdificio = edificioService.habitantesPorEdificio(codigoEdificio);
			List<EdificioView> edificios = edificioService.obtenerEdificios();

		} catch (EdificioException e) {
			e.printStackTrace();
		}
		try{
			String documento = "1234456";
			String nombre= "Andres";


			Persona personaPorId= personaService.buscarPersona(documento);
			Persona agregarPersona = personaService.agregarPersona(documento,nombre);
			personaService.eliminarPersona(documento);
		} catch (PersonaException e) {
			e.printStackTrace();
		}
		try {
			//Edificio
			int codigoEdificio = 1;
           //unidad
			int codigoUnidad = 1;
			String pisounidad = "1";
			String numerounidad ="1";
			String documento = "123456";
			//reclamo
			int numeroreclamo = 1;
			Estado estado =Estado.desestimado;
			//imagen
			String path ="direccionImagen";
			String tipo = "tipo";
			List<ReclamoView> reclamosPorEdificio = reclamoService.reclamosPorEdificio(codigoEdificio);
			List<ReclamoView> reclamosPorUnidad = reclamoService.reclamosPorUnidad(codigoUnidad, pisounidad,numerounidad);
			List<ReclamoView> reclamosPorPersona = reclamoService.reclamosPorPersona(documento);
			ReclamoView reclamosPorNumero = reclamoService.reclamosPorNumero(numeroreclamo);
			Reclamo agregarreclamo= reclamoService.agregarReclamo(codigoEdificio,codigoUnidad, pisounidad, numerounidad,documento,"Paraguay 1957", "goteras");
			reclamoService.agregarImagenAReclamo(numeroreclamo,path, tipo);
			reclamoService.cambiarEstado(numeroreclamo,estado);
			Reclamo buscarReclamo = reclamoService.buscarReclamo(numeroreclamo);
		} catch (UnidadException e) {
			e.printStackTrace();
		} catch (PersonaException e) {
			e.printStackTrace();
		} catch (EdificioException e) {
			e.printStackTrace();
		} catch (ReclamoException e) {
			e.printStackTrace();
		}

		try {
			//Edificio
			int codigoEdificio = 1;
			//unidad
			int codigoUnidad = 1;
			String pisounidad = "1";
			String numerounidad ="1";
			String documento = "123456";
			//reclamo
			int numeroreclamo = 1;
			Estado estado =Estado.desestimado;
			//imagen
			String path ="direccionImagen";
			String tipo = "tipo";

			Unidad buscarUnidad = unidadService.buscarUnidad(codigoUnidad,pisounidad,numerounidad);
			List<PersonaView> dueniosPorUnidad = unidadService.dueniosPorUnidad(codigoUnidad,pisounidad,numerounidad);
			List<PersonaView> inquilinosPorUnidad = unidadService.inquilinosPorUnidad(codigoUnidad,pisounidad,numerounidad);
			unidadService.transferirUnidad(codigoUnidad,pisounidad,numerounidad,documento);
			unidadService.agregarDuenioUnidad(codigoUnidad, pisounidad,numerounidad,documento);
			unidadService.agregarInquilinoUnidad(codigoUnidad, pisounidad,numerounidad,documento);
			unidadService.liberarUnidad(codigoUnidad,pisounidad,numerounidad);
			unidadService.habitarUnidad(codigoUnidad,pisounidad,numerounidad);

		} catch (UnidadException e) {
			e.printStackTrace();
		} catch (PersonaException e) {
			e.printStackTrace();
		}
	}

}
