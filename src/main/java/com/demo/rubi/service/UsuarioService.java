/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.rubi.service;

import com.demo.rubi.dto.DomicilioDTO;
import com.demo.rubi.dto.UsuarioResponseDTO;
import com.demo.rubi.model.Usuario;
import com.demo.rubi.repository.UsuarioRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO toResponseDTO(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setApellidoPaterno(u.getApellidoPaterno());
        dto.setApellidoMaterno(u.getApellidoMaterno());
        dto.setNombreUsuario(u.getNombreUsuario());
        dto.setFechaNacimiento(u.getFechaNacimiento());

        if (u.getFechaNacimiento() != null) {
            dto.setEdad(Period.between(u.getFechaNacimiento(), LocalDate.now()).getYears());
        }

        if (u.getDomicilio() != null) {
            DomicilioDTO d = new DomicilioDTO();
            d.setId(u.getDomicilio().getId());
            d.setCalle(u.getDomicilio().getCalle());
            d.setNumeroExterior(u.getDomicilio().getNumeroExterior());
            d.setNumeroInterior(u.getDomicilio().getNumeroInterior());
            d.setColonia(u.getDomicilio().getColonia());
            d.setCodigoPostal(u.getDomicilio().getCodigoPostal());
            d.setCiudad(u.getDomicilio().getCiudad());
            dto.setDomicilio(d);
        }
        return dto;
    }

}
