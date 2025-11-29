Feature: Invitar Miembro a Organización
  Para sumar Agricultores a mi organización
  Como Agrónomo
  Quiero enviar invitaciones

  Scenario Outline: 1. Invitación enviada correctamente (Creación de estado)
    Given el Agrónomo con ID "<agronomoId>" es OWNER de la organización "<orgId>"
    And el Agricultor con email "<agricultorEmail>" está registrado en el sistema
    When el Agrónomo selecciona al Agricultor y envía la invitación
    Then el resultado debe ser "invitación creada"
    And el sistema crea la invitación con estado "Pendiente"
    And la invitación aparece en la sección de notificaciones del Agricultor
    And el sistema muestra al Agrónomo "Invitación enviada"

    Examples:
      | agronomoId | orgId | agricultorEmail     |
      | 101        | 50    | farmer1@mail.com    |
      | 102        | 51    | newfarmer@app.com   |

  Scenario Outline: 2. Agricultor acepta la invitación (Cambio de estado y pertenencia)
    Given el Agricultor con ID "<agricultorId>" tiene una invitación "Pendiente" de la organización "<orgId>"
    When el Agricultor abre la invitación y presiona "Aceptar"
    Then el resultado debe ser "miembro agregado"
    And el sistema agrega al Agricultor como miembro de la organización "<orgId>" con rol "Farmer"
    And cambia el estado de la invitación a "Aceptada"
    And notifica al Agrónomo que el Agricultor se unió

    Examples:
      | agricultorId | orgId |
      | 201          | 50    |
      | 202          | 52    |

  Scenario Outline: 3. Agricultor rechaza la invitación (Cambio de estado y no pertenencia)
    Given el Agricultor con ID "<agricultorId>" tiene una invitación "Pendiente" de la organización "<orgId>"
    When el Agricultor abre la invitación y presiona "Rechazar"
    Then el resultado debe ser "invitación rechazada"
    And el sistema cambia el estado de la invitación a "Rechazada"
    And el Agricultor no es agregado a la organización "<orgId>"
    And notifica al Agrónomo que la invitación fue rechazada

    Examples:
      | agricultorId | orgId |
      | 203          | 53    |
      | 204          | 54    |