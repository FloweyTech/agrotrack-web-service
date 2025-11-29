Feature: Adquirir suscripción
  Para activar planes y límites según el plan contratado
  Como desarrollador
  Quiero implementar el endpoint POST "/api/subscriptions"

  Scenario Outline: Pago aprobado
    Given medio de pago válido "<medioPago>"
    And el usuario envía un planId "<planId>" y paymentInfo "<paymentInfo>" correctos
    When se realiza la solicitud POST a "/api/subscriptions"
    Then se responde con estado 201
    And se emite el mensaje "Suscripción adquirida"

    Examples:
      | medioPago | planId | paymentInfo      |
      | VISA      | 101    | PAY-OK-123       |
      | MASTERCARD| 202    | TRANS-VALID-987  |

  Scenario Outline: Pago rechazado
    Given tarjeta rechazada "<medioPago>"
    When proceso el cobro con paymentInfo "<paymentInfo>"
    Then se responde con estado 402
    And se retorna el motivo del rechazo

    Examples:
      | medioPago | paymentInfo     |
      | VISA      | PAY-ERR-001     |
      | AMEX      | FAIL-CARD-998   |
