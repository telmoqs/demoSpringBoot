package com.example.demo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private final PessoaRepository pessoaRepository;

	public PessoaController(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@GetMapping
	public List<Pessoa> getPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Pessoa getPessoa(@PathVariable Long id) {
		return pessoaRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping
	public ResponseEntity createClient(@RequestBody Pessoa pessoa) throws URISyntaxException {
		Pessoa savedClient = pessoaRepository.save(pessoa);
		return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Pessoa client) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(RuntimeException::new);
		pessoa.setNome(client.getNome());
		pessoa.setEmail(client.getEmail());
		pessoa = pessoaRepository.save(client);

		return ResponseEntity.ok(pessoa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteClient(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
