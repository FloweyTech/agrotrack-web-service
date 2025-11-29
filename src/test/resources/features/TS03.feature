Feature: Completar perfil inicial del usuario
  Para registrar rol y datos básicos
  Como developer
  Quiero implementar el endpoint PUT "/api/users/{userId}/profile"

  Scenario Outline: Perfil completado correctamente
    Given usuario autenticado con userId "<userId>"
    And envío rol "<rol>" y datos de contacto válidos
    When realizo la solicitud PUT a "/api/users/<userId>/profile"
    Then se responde con estado 200 OK
    And se guarda "Perfil completado"

    Examples:
      | userId | rol        |
      | 1      | agricultor |
      | 2      | supervisor |

  Scenario Outline: Error por datos inválidos
    Given usuario autenticado con userId "<userId>"
    And existen campos obligatorios faltantes
    When intento guardar el perfil
    Then se responde con estado 400 Bad Request
    And se retorna detalle de validaciones

    Examples:
      | userId |
      | 1      |
      | 2      |
