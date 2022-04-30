package com.example.demo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> getPessoas() {
		return pessoaService.findAll();
	}

	@GetMapping("/{id}")
	public Pessoa getPessoa(@PathVariable Long id) {
		return pessoaService.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping
	public ResponseEntity createClient(@RequestBody Pessoa pessoa) throws URISyntaxException {
		Pessoa savedClient = pessoaService.save(pessoa);
		return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Pessoa client) {
		Pessoa pessoa = pessoaService.findById(id).orElseThrow(RuntimeException::new);
		pessoa.setNome(client.getNome());
		pessoa.setEmail(client.getEmail());
		pessoa = pessoaService.save(client);

		return ResponseEntity.ok(pessoa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteClient(@PathVariable Long id) {
		pessoaService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
