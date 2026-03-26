/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.rubi;

import com.demo.rubi.model.Domicilio;
import com.demo.rubi.model.Usuario;
import com.demo.rubi.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Solo inserta si la tabla está vacía
        if (usuarioRepository.count() > 0) {
            System.out.println("LA TABLA YA TIENEN DATOS");
            return;
        }

        String[] nombres = {
            "Juan", "María", "Carlos", "Ana", "Luis",
            "Sofía", "Pedro", "Laura", "Miguel", "Valeria",
            "Jorge", "Fernanda", "Roberto", "Gabriela", "Alejandro",
            "Daniela", "Francisco", "Mariana", "Ricardo", "Paola",
            "Arturo", "Claudia", "Sergio", "Verónica", "Raúl",
            "Lucía", "Héctor", "Natalia", "Eduardo", "Rebeca",
            "Iván", "Patricia", "Óscar", "Adriana", "Diego",
            "Mónica", "Javier", "Carolina", "Ernesto", "Silvia",
            "Rodrigo", "Elena", "Felipe", "Beatriz", "Guillermo",
            "Rosa", "Andrés", "Leticia", "Manuel", "Alicia"
        };

        String[] apellidosPaternos = {
            "García", "Martínez", "López", "González", "Hernández",
            "Pérez", "Sánchez", "Ramírez", "Torres", "Flores",
            "Rivera", "Cruz", "Reyes", "Morales", "Ortiz",
            "Gutiérrez", "Chávez", "Ramos", "Mendoza", "Vargas",
            "Castillo", "Romero", "Jiménez", "Navarro", "Domínguez",
            "Aguilar", "Vega", "Medina", "Campos", "Núñez",
            "Ruiz", "Rojas", "Molina", "Silva", "Herrera",
            "Cabrera", "Delgado", "Fuentes", "Espinoza", "Lara",
            "Contreras", "Ríos", "Miranda", "Acosta", "Mejía",
            "Luna", "Ibarra", "Figueroa", "Ponce", "Guerrero"
        };

        String[] apellidosMaternos = {
            "Ruiz", "Díaz", "Moreno", "Muñoz", "Álvarez",
            "Romero", "Alonso", "Gutiérrez", "Navarro", "Torres",
            "Domínguez", "Vázquez", "Ramos", "Gil", "Serrano",
            "Blanco", "Molina", "Morales", "Suárez", "Ortega",
            "Delgado", "Castro", "Rubio", "Medina", "Soto",
            "Iglesias", "Garrido", "Cortés", "Calvo", "Herrero",
            "Nava", "Reyna", "Becerra", "Sandoval", "Cárdenas",
            "Velázquez", "Trejo", "Esquivel", "Padilla", "Salinas",
            "Bravo", "Cervantes", "Montes", "Estrada", "Villanueva",
            "Peña", "Ayala", "León", "Carrillo", "Maldonado"
        };

        String[] calles = {
            "Av. Independencia", "Calle Reforma", "Blvd. Juárez",
            "Calle Hidalgo", "Av. Morelos", "Calle Allende",
            "Av. Revolución", "Calle Guerrero", "Blvd. Madero",
            "Calle Zaragoza"
        };

        String[] colonias = {
            "Centro", "Reforma", "Del Valle", "Jardines",
            "Santa María", "San Juan", "La Paz", "El Rosario",
            "Vista Hermosa", "Las Flores"
        };

        String[] ciudades = {
            "Oaxaca", "Ciudad de México", "Guadalajara",
            "Monterrey", "Puebla"
        };

        String[] codigosPostales = {
            "68000", "06600", "44100", "64000", "72000",
            "68050", "06700", "44200", "64100", "72100"
        };

        for (int i = 0; i < 50; i++) {
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(calles[i % calles.length]);
            domicilio.setNumeroExterior(String.valueOf((i + 1) * 10));
            domicilio.setNumeroInterior(i % 3 == 0 ? "Int. " + (i + 1) : null);
            domicilio.setColonia(colonias[i % colonias.length]);
            domicilio.setCodigoPostal(codigosPostales[i % codigosPostales.length]);
            domicilio.setCiudad(ciudades[i % ciudades.length]);

            Usuario usuario = new Usuario();
            usuario.setNombre(nombres[i]);
            usuario.setApellidoPaterno(apellidosPaternos[i]);
            usuario.setApellidoMaterno(apellidosMaternos[i]);
            usuario.setNombreUsuario(
                    (nombres[i].toLowerCase()
                            .replace("á", "a").replace("é", "e")
                            .replace("í", "i").replace("ó", "o")
                            .replace("ú", "u"))
                    + (i + 1)
            );
            LocalDate inicio = LocalDate.now().minusYears(60);
            LocalDate fin = LocalDate.now().minusYears(18);

            long dias = fin.toEpochDay() - inicio.toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(dias + 1);

            LocalDate fechaAleatoria = inicio.plusDays(randomDay);

            usuario.setFechaNacimiento(fechaAleatoria);
            usuario.setContrasena(passwordEncoder.encode("Password" + (i + 1)));
            usuario.setDomicilio(domicilio);

            usuarioRepository.save(usuario);
        }
    }
}
