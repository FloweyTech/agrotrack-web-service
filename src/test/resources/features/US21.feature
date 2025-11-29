Feature: Solicitar reporte de parcela
  Para analizar el desempeño de la parcela
  Como Agrónomo
  Quiero solicitar un reporte de una parcela

  Scenario Outline: Solicitud exitosa
    Given el Agrónomo tiene acceso a la parcela "<parcelaId>"
    When selecciona un periodo desde "<fecha_inicio>" hasta "<fecha_fin>" y solicita el reporte
    Then el resultado debe ser "generación iniciada"
    And el sistema inicia la generación
    And muestra un mensaje de confirmación

    Examples:
      | parcelaId | fecha_inicio | fecha_fin  |
      | 101       | 2025-01-01   | 2025-03-31 |
      | 202       | 2024-11-01   | 2025-10-31 |

  Scenario Outline: Periodo inválido
    Given el Agrónomo solicita un reporte para la parcela "<parcelaId>"
    When elige fechas incorrectas (fecha desde "<fecha_desde>" mayor que fecha hasta "<fecha_hasta>")
    Then el resultado debe ser "solicitud rechazada"
    And el sistema rechaza la acción
    And muestra un mensaje de error

    Examples:
      | parcelaId | fecha_desde | fecha_hasta |
      | 303       | 2026-01-01  | 2025-12-31  |
      | 404       | 2025-07-01  | 2025-06-01  |