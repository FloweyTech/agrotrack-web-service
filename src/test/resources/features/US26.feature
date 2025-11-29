Feature: Recuperar cuenta
  Para restablecer el acceso si olvidé mis credenciales
  Como usuario
  Quiero recuperar mi cuenta mediante un correo de recuperación

  Scenario Outline: Solicitud de recuperación
    Given el usuario con correo "<correo>" olvidó su contraseña
    When ingresa su correo registrado y solicita recuperación
    Then el resultado debe ser "enlace/código enviado"
    And el sistema envía un enlace o código de restablecimiento

    Examples:
      | correo            |
      | usuario1@mail.com |
      | user2@agrotrack.co |

  Scenario Outline: Restablecimiento exitoso
    Given el usuario recibió un enlace válido para el correo "<correo>"
    When define una nueva contraseña "<nueva_contraseña>"
    Then el resultado debe ser "contraseña actualizada y acceso permitido"
    And el sistema actualiza la contraseña
    And permite iniciar sesión con la nueva clave

    Examples:
      | correo            | nueva_contraseña |
      | usuario3@mail.com | NewPass123!      |
      | user4@agrotrack.co | ResetKey$      |