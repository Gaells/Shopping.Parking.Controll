package br.com.shopping.gerenciadorvagas.service

import br.com.shopping.gerenciadorvagas.repository.VagaRepository
import br.com.shopping.gerenciadorvagas.model.Vaga
import br.com.shopping.gerenciadorvagas.model.StatusVaga
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service


@Service
class VagaService(private val vagaRepository: VagaRepository) {

  @Transactional(readOnly = true)
  fun listarTodas(): List<Vaga> {
    return vagaRepository.findAll()
  }

  @Transactional(readOnly = true)
  fun listarDisponiveis(): List<Vaga> {
    return vagaRepository.findByStatus(StatusVaga.LIVRE)
  }

  @Transactional
  fun criarVaga(numeroVaga: String, setor: String): Vaga {
    val novaVaga = Vaga(numeroVaga = numeroVaga, setor = setor)
        return vagaRepository.save(novaVaga)
  }

  @Transactional
    fun registrarEntrada(idVaga: Long, placa: String): Vaga {
        val vaga = vagaRepository.findById(idVaga)
            .orElseThrow { Exception("Vaga não encontrada com o ID: $idVaga") }

        if (vaga.status == StatusVaga.OCUPADA) {
            throw IllegalStateException("A vaga ${vaga.numeroVaga} já está ocupada.")
        }

        vaga.status = StatusVaga.OCUPADA
        vaga.placaVeiculo = placa
        
        return vagaRepository.save(vaga)
    }

    @Transactional
    fun registrarSaida(idVaga: Long): Vaga {
        val vaga = vagaRepository.findById(idVaga)
            .orElseThrow { Exception("Vaga não encontrada com o ID: $idVaga") }

        if (vaga.status == StatusVaga.LIVRE) {
            throw IllegalStateException("A vaga ${vaga.numeroVaga} já está livre.")
        }

        vaga.status = StatusVaga.LIVRE
        vaga.placaVeiculo = null
        
        return vagaRepository.save(vaga)
    }

    @Transactional(readOnly = true)
    fun listarPorSetor(setor: String): List<Vaga> {
        return vagaRepository.findBySetor(setor)
    }

}
