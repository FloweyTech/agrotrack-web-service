Feature: Registrar condiciones ambientales
  Para monitorear el ambiente de la parcela
  Como Agricultor
  Quiero registrar humedad y temperatura

  Scenario Outline: Registro exitoso
    Given la parcela "<parcelaId>" está en monitoreo activo
    When el Agricultor registra valores válidos de humedad "<humedad>" y temperatura "<temperatura>"
    Then el resultado debe ser "registro guardado"
    And el sistema guarda el registro

    Examples:
      | parcelaId | humedad | temperatura |
      | 101       | 65      | 25.5        |
      | 102       | 80      | 18.0        |

  Scenario Outline: Datos fuera de rango
    Given la parcela "<parcelaId>" está en monitoreo activo
    When el Agricultor ingresa valores imposibles o en formato inválido: humedad "<humedad>" y temperatura "<temperatura>"
    Then el resultado debe ser "registro rechazado"
    And el sistema rechaza la acción
    And muestra un mensaje de error

    Examples:
      | parcelaId | humedad | temperatura |
      | 103       | 150     | -5.0        |
      | 104       | "abc"   | 40.0        |