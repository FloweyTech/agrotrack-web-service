Feature: Activar cuenta
  Para empezar a usar todas las funcionalidades
  Como usuario
  Quiero que mi cuenta se active automáticamente al cumplir los requisitos

  Scenario Outline: Activación automática
    Given el usuario "<usuarioId>" completó su registro, perfil y suscripción
    When el sistema verifica los requisitos
    Then el resultado debe ser "cuenta activada"
    And la cuenta se activa
    And se notifica al usuario

    Examples:
      | usuarioId |
      | 101       |
      | 102       |

  Scenario Outline: Requisito faltante
    Given al usuario "<usuarioId>" le falta completar algún requisito: "<requisito_faltante>"
    When el sistema verifica el estado
    Then el resultado debe ser "cuenta pendiente de activación"
    And la cuenta sigue pendiente
    And muestra qué falta para la activación

    Examples:
      | usuarioId | requisito_faltante |
      | 103       | perfil             |
      | 104       | suscripción        |