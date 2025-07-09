package br.com.shopping.gerenciadorvagas.model

import jakarta.persistence.*

enum class StatusVaga {
    LIVRE,
    OCUPADA
}

@Entity
@Table(uniqueConstraints = [
  UniqueConstraint(columnNames = ["numeroVaga", "setor"])
])
class Vaga(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false)
  val numeroVaga: String,

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  var status: StatusVaga = StatusVaga.LIVRE,

  var placaVeiculo: String? = null,

  @Column(nullable = false)
  var setor: String
)