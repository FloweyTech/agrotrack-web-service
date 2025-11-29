Feature: Crear Parcela
  Para gestionar el cultivo y su monitoreo
  Como Agrónomo
  Quiero crear una parcela

  Scenario Outline: Creación exitosa
    Given el Agrónomo pertenece a una organización
    When completa el formulario de parcela con nombre "<nombre>", área "<area>" y ubicación "<ubicacion>" válidos
    Then el resultado debe ser "parcela registrada"
    And el sistema registra la parcela con un identificador único
    And muestra "Parcela creada"

    Examples:
      | nombre    | area | ubicacion     |
      | Lote Sur  | 5.5  | Lat: 12, Lon: 77|
      | Campo 2   | 10.0 | Lat: 10, Lon: 75|

  Scenario Outline: Datos inválidos
    Given el Agrónomo pertenece a una organización
    When ingresa un área "<area>" o deja campos obligatorios vacíos
    Then el resultado debe ser "error de validación"
    And el sistema rechaza la acción
    And muestra "Datos de parcela inválidos"

    Examples:
      | area |
      | 0    |
      | -2   |
      | ""   |