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

	public static void main(String[] args) throws PersonaException, EdificioException, ReclamoException, UnidadException {
		//SpringApplication.run(TpApi01Application.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(TpApi01Application.class, args);

		EdificioService edificioService = context.getBean(EdificioService.class);
		UnidadService unidadService = context.getBean(UnidadService.class);
		PersonaService personaService = context.getBean(PersonaService.class);
		ReclamoService reclamoService = context.getBean(ReclamoService.class);


		/*

			int codigoEdificio = 1;
/*
			List<UnidadView> unidadesPorEdificio = edificioService.getUnidadesPorEdificio(codigoEdificio);
			for (UnidadView unidadView: unidadesPorEdificio){
				System.out.println(unidadView.toString());
			}

 */
/*
			List<PersonaView> habilitadosPorEdificio = edificioService.habilitadosPorEdificio(codigoEdificio);
			System.out.println(habilitadosPorEdificio);


 */

		/*
		//Caused by: java.util.ConcurrentModificationException

			List<PersonaView> dueniosPorEdificio = edificioService.dueniosPorEdificio(codigoEdificio);
			for (PersonaView personaView: dueniosPorEdificio){
				System.out.println(personaView.toString());
			}

		 */

 /*
		Caused by: java.util.ConcurrentModificationException

			List<PersonaView> habitantesPorEdificio = edificioService.habitantesPorEdificio(codigoEdificio);
			System.out.println(habitantesPorEdificio);


  */


/*
			List<EdificioView> edificios = edificioService.obtenerEdificios();
			System.out.println(edificios);



 */



/*
			String documento = "1234456";
			String nombre= "Andres";


			Persona agregarPersona = personaService.agregarPersona(documento,nombre);
			System.out.println( personaService.buscarPersona(documento).toString());
			personaService.eliminarPersona(documento);



 */


/*

			personaService.agregarPersona("123456","Andres");
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
			System.out.println(reclamosPorEdificio);
			List<ReclamoView> reclamosPorUnidad = reclamoService.reclamosPorUnidad(codigoUnidad, pisounidad,numerounidad);
			System.out.println(reclamosPorUnidad);
			List<ReclamoView> reclamosPorPersona = reclamoService.reclamosPorPersona(documento);
			System.out.println(reclamosPorPersona);
			ReclamoView reclamosPorNumero = reclamoService.reclamosPorNumero(numeroreclamo);
			System.out.println(reclamosPorNumero);

			//Aun no se han probado los proximos 3 reclamos
			Reclamo agregarreclamo= reclamoService.agregarReclamo(codigoEdificio,codigoUnidad, pisounidad, numerounidad,documento,"Paraguay 1957", "goteras");

			reclamoService.agregarImagenAReclamo(numeroreclamo,path, tipo);

			reclamoService.cambiarEstado(numeroreclamo,estado);


			Reclamo buscarReclamo = reclamoService.buscarReclamo(numeroreclamo);

 */





/*

			//Edificio
			int codigoEdificio = 1;
			//unidad
			int codigoUnidad = 1;
			String pisounidad = "10";
			String numerounidad ="6";
			String documento = "123456";
			//reclamo
			int numeroreclamo = 1;
			Estado estado =Estado.desestimado;
			//imagen
			String path ="direccionImagen";
			String tipo = "tipo";

			Unidad buscarUnidad = unidadService.buscarUnidad(codigoUnidad,pisounidad,numerounidad);
			System.out.println(buscarUnidad.toView());
			List<PersonaView> dueniosPorUnidad = unidadService.dueniosPorUnidad(codigoUnidad,pisounidad,numerounidad);
			System.out.println(dueniosPorUnidad);
			List<PersonaView> inquilinosPorUnidad = unidadService.inquilinosPorUnidad(codigoUnidad,pisounidad,numerounidad);
			System.out.println(inquilinosPorUnidad);
			/*
			//los siguientes 5 metodos no se han probado
			unidadService.transferirUnidad(codigoUnidad,pisounidad,numerounidad,documento);
			unidadService.agregarDuenioUnidad(codigoUnidad, pisounidad,numerounidad,documento);
			unidadService.agregarInquilinoUnidad(codigoUnidad, pisounidad,numerounidad,documento);
			unidadService.liberarUnidad(codigoUnidad,pisounidad,numerounidad);
			unidadService.habitarUnidad(codigoUnidad,pisounidad,numerounidad);

			 */



	}



}
