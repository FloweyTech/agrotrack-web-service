Feature: Registrar datos de la planta
  Para llevar control del estado fenológico del cultivo
  Como Agricultor
  Quiero registrar cantidad de hojas y botones

  Scenario Outline: Registro exitoso
    Given la parcela "<parcelaId>" está en monitoreo activo
    When el Agricultor registra la cantidad de hojas "<hojas>" y botones "<botones>"
    Then el resultado debe ser "registro guardado"
    And el sistema guarda el registro

    Examples:
      | parcelaId | hojas | botones |
      | 101       | 5     | 2       |
      | 102       | 10    | 0       |

  Scenario Outline: Datos inválidos
    Given la parcela "<parcelaId>" está en monitoreo activo
    When el Agricultor ingresa valores negativos o vacíos: hojas "<hojas>" y botones "<botones>"
    Then el resultado debe ser "registro rechazado"
    And el sistema rechaza el registro
    And muestra un mensaje de error

    Examples:
      | parcelaId | hojas | botones |
      | 103       | -1    | 5       |
      | 104       | ""    | 10      |