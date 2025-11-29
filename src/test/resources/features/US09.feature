Feature: Iniciar monitoreo
  Para habilitar el registro de datos en la parcela
  Como Agrónomo
  Quiero iniciar el monitoreo de una parcela

  Scenario Outline: Inicio exitoso
    Given la parcela "<parcelaId>" no tiene un monitoreo activo
    When el Agrónomo pulsa "Iniciar monitoreo" para la parcela "<parcelaId>"
    Then el resultado debe ser "monitoreo activo"
    And el sistema activa el monitoreo para esa parcela

    Examples:
      | parcelaId |
      | 101       |
      | 202       |

  Scenario Outline: Ya activo
    Given la parcela "<parcelaId>" ya tiene un monitoreo en curso
    When el Agrónomo intenta activarlo otra vez
    Then el resultado debe ser "rechazo con advertencia"
    And el sistema rechaza la acción
    And muestra un mensaje de advertencia

    Examples:
      | parcelaId |
      | 303       |
      | 404       |