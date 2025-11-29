Feature: Modificar Parcela
  Para mantener los datos correctos de la parcela
  Como Agrónomo
  Quiero modificar una parcela

  Scenario Outline: Modificación exitosa
    Given la parcela con ID "<parcelaId>" ya existe en la organización
    When el Agrónomo actualiza el área a "<nuevaArea>" y la ubicación a "<nuevaUbicacion>" con datos válidos
    Then el resultado debe ser "modificación exitosa"
    And el sistema guarda los cambios
    And muestra "Parcela actualizada"

    Examples:
      | parcelaId | nuevaArea | nuevaUbicacion |
      | 101       | 6.0       | Lat: 12.1, Lon: 77.1 |
      | 102       | 9.5       | Lat: 10.5, Lon: 75.5 |

  Scenario Outline: Datos inválidos
    Given la parcela con ID "<parcelaId>" existe
    When el Agrónomo ingresa un área "<areaInvalida>" negativa o coordenadas inválidas
    Then el resultado debe ser "error de validación"
    And el sistema rechaza la acción
    And muestra "Datos de parcela inválidos"

    Examples:
      | parcelaId | areaInvalida |
      | 103       | -1           |
      | 104       | 0            |