Feature: Adquirir suscripción
  Para habilitar las funcionalidades disponibles según mi rol y plan contratado
  Como Usuario
  Quiero adquirir un plan de suscripción

  Scenario Outline: Compra exitosa
    Given el usuario con ID "<usuarioId>" está autenticado
    When selecciona un plan "<plan_seleccionado>" y realiza el pago correctamente con "<metodo_pago>"
    Then el resultado debe ser "suscripción activada"
    And el sistema activa la suscripción
    And muestra un mensaje de confirmación

    Examples:
      | usuarioId | plan_seleccionado | metodo_pago |
      | 101       | Premium           | Tarjeta     |
      | 102       | Básico            | PayPal      |

  Scenario Outline: Pago fallido
    Given el usuario con ID "<usuarioId>" está autenticado
    When intenta pagar el plan "<plan_seleccionado>" y el proceso falla
    Then el resultado debe ser "pago rechazado"
    And el sistema no activa la suscripción
    And muestra un mensaje de error

    Examples:
      | usuarioId | plan_seleccionado |
      | 103       | Premium           |
      | 104       | Básico            |