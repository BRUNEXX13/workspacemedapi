package com.example.med.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.med.api.model.Usuario;
import com.example.med.api.repository.UsuarioRepository;

//@Service para dizer que e um componente do Spring
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.findOne(codigo);
		if (usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
		return usuarioRepository.save(usuarioSalvo);
	}

}