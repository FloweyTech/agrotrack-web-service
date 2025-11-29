Feature: Solicitar reporte general
  Para ver indicadores globales de la organización
  Como Agrónomo
  Quiero solicitar un reporte general de la organización

  Scenario Outline: Solicitud exitosa
    Given el Agrónomo con ID "<agronomoId>" tiene rol de owner
    When solicita el reporte general de un periodo "<periodo>"
    Then el resultado debe ser "generación iniciada"
    And el sistema inicia la generación del reporte

    Examples:
      | agronomoId | periodo     |
      | 101        | Trimestral  |
      | 102        | Anual       |

  Scenario Outline: Sin permisos
    Given el usuario con ID "<usuarioId>" no tiene rol de owner
    When intenta solicitar el reporte general
    Then el resultado debe ser "solicitud rechazada por permisos"
    And el sistema rechaza la acción
    And muestra un mensaje de permisos insuficientes

    Examples:
      | usuarioId |
      | 201       |
      | 202       |