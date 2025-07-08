package br.com.shopping.gerenciadorvagas.repository

import br.com.shopping.gerenciadorvagas.model.Vaga
import br.com.shopping.gerenciadorvagas.model.StatusVaga
import org.springframework.data.jpa.repository.JpaRepository

interface VagaRepository : JpaRepository<Vaga, Long> {
  
  fun findByStatus(status: StatusVaga): List<Vaga>

  fun findBySetor(setor: String): List<Vaga>
}