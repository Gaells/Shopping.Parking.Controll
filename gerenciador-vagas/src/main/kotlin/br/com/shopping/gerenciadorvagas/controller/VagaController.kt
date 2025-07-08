package br.com.shopping.gerenciadorvagas.controller

import br.com.shopping.gerenciadorvagas.service.VagaService
import br.com.shopping.gerenciadorvagas.model.Vaga
import br.com.shopping.gerenciadorvagas.dtos.VagaRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/vagas")
class VagaController(private val vagaService: VagaService) {

  @GetMapping
  fun listarTodasAsVagas(): ResponseEntity<List<Vaga>> {
    return ResponseEntity.ok(vagaService.listarTodas())
  }

  @GetMapping("/disponiveis")
    fun listarVagasDisponiveis(): ResponseEntity<List<Vaga>> {
        return ResponseEntity.ok(vagaService.listarDisponiveis())
    }

    @PostMapping
    fun criarVaga(
       @RequestBody vagaRequest: VagaRequest
    ): ResponseEntity<Vaga> {
        val vagaCriada = vagaService.criarVaga(vagaRequest.numeroVaga, vagaRequest.setor)
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaCriada)
    }

    @PostMapping("/{id}/entrada")
    fun registrarEntrada(@PathVariable id: Long, @RequestParam placa: String): ResponseEntity<Vaga> {
        return ResponseEntity.ok(vagaService.registrarEntrada(id, placa))
    }

    @PostMapping("/{id}/saida")
    fun registrarSaida(@PathVariable id: Long): ResponseEntity<Vaga> {
        return ResponseEntity.ok(vagaService.registrarSaida(id))
    }
    
    @GetMapping("/setor/{setor}")
    fun listarVagasPorSetor(@PathVariable setor: String): ResponseEntity<List<Vaga>> {
        return ResponseEntity.ok(vagaService.listarPorSetor(setor))
    }
}