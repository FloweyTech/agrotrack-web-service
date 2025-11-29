Feature: Registrar cuenta
  Para acceder a la plataforma
  Como usuario nuevo
  Quiero registrar mi cuenta con correo y contraseña

  Scenario Outline: Registro exitoso
    Given el usuario ingresa correo "<correo>" válido y contraseña "<contraseña>" segura
    When envía el formulario de registro
    Then el resultado debe ser "cuenta creada pendiente de activación"
    And el sistema crea la cuenta pendiente de activación

    Examples:
      | correo            | contraseña     |
      | nuevo@mail.com    | SecurePass123! |
      | test@agrotrack.co | StrongKey$     |

  Scenario Outline: Error de registro
    Given el correo "<correo>" ya existe o la contraseña "<contraseña>" no cumple la política
    When el usuario intenta registrarse
    Then el resultado debe ser "registro rechazado"
    And el sistema rechaza la acción
    And muestra el motivo

    Examples:
      | correo            | contraseña |
      | existente@mail.com| Short     |
      | inválido@mail.com | 12345678   |