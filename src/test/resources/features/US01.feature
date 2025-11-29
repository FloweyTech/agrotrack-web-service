Feature: Crear Organización
  Para centralizar miembros y parcelas
  Como Agrónomo
  Quiero crear una organización

  Scenario Outline: Organización creada correctamente y asignación de rol Owner
    Given el Agrónomo con ID "<agronomoId>" está autenticado
    And el Agrónomo no tiene una organización existente
    When crea una organización con nombre "<nombre_organizacion>" y datos válidos obligatorios
    Then el resultado debe ser "organización registrada y Owner asignado"
    And el sistema debe registrar la organización con un organizationId
    And el rol del Agrónomo debe ser "Owner" en la organización
    And el mensaje de confirmación es "Organización creada"

    Examples:
      | agronomoId | nombre_organizacion |
      | 101        | Soluciones AgroTech |
      | 102        | Campo Verde S.A.    |

  Scenario Outline: Error al crear organización por datos inválidos
    Given el Agrónomo con ID "<agronomoId>" está autenticado
    When envía el formulario sin el nombre o con caracteres inválidos "<nombre_invalido>"
    Then el resultado debe ser "error de validación"
    And el sistema muestra el mensaje "Revisa los campos requeridos"
    And no crea la organización

    Examples:
      | agronomoId | nombre_invalido |
      | 103        | ""              |
      | 104        | Org!@#          |