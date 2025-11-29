Feature: Completar perfil
  Para terminar de configurar mi cuenta
  Como usuario recién registrado
  Quiero completar mi perfil inicial con mis datos básicos (nombre, rol, contacto)

  Scenario Outline: Completar datos básicos
    Given la cuenta con ID "<usuarioId>" fue creada
    When el usuario agrega nombre "<nombre>", rol "<rol>" y datos de contacto
    Then el resultado debe ser "perfil completado"
    And el sistema guarda la información
    And marca el perfil como completo

    Examples:
      | usuarioId | nombre     | rol        |
      | 101       | Juan Pérez | AGRONOMIST |
      | 102       | Ana Torres | FARMER     |

  Scenario Outline: Datos incompletos
    Given el perfil del usuario "<usuarioId>" está en edición
    When se omiten campos obligatorios como el "<campo_omitido>"
    Then el resultado debe ser "acción rechazada por datos faltantes"
    And el sistema rechaza la acción
    And muestra un mensaje de error

    Examples:
      | usuarioId | campo_omitido |
      | 103       | nombre        |
      | 104       | rol           |