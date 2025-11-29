Feature: Invitación de miembros a una organización
  Para sumar Agrónomos o Agricultores a una organización
  Como desarrollador
  Quiero implementar el endpoint POST "/api/organizations/{orgId}/invitations"

  Scenario Outline: Invitación enviada correctamente
    Given org existente con id "<orgId>"
    And envío email "<email>" y rol "<rol>" válidos
    When se realiza una solicitud POST a "/api/organizations/<orgId>/invitations"
    Then responde 201 y manda invitación

    Examples:
      | orgId | email               | rol         |
      | 1     | agronomo@test.com   | Agrónomo    |
      | 2     | agricultor@mail.com | Agricultor  |

  Scenario Outline: Invitación rechazada porque ya es miembro
    Given usuario con email "<email>" ya pertenece a la org con id "<orgId>"
    When intento invitarlo
    Then responde 409 "usuario ya es miembro"

    Examples:
      | orgId | email             |
      | 1     | miembro@test.com  |
      | 3     | user@orgmail.com  |
