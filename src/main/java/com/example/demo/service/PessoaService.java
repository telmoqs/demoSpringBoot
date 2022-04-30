package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Optional<Pessoa> findById(Long id) {
		return this.pessoaRepository.findById(id);
	}

	public Pessoa save(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}

	public void deleteById(Long id) {
		this.pessoaRepository.deleteById(id);
	}
}
