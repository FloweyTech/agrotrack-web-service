Feature: Iniciar sesión
  Para entrar de forma segura a la plataforma
  Como usuario registrado
  Quiero iniciar sesión con mis credenciales

  Scenario Outline: Inicio exitoso
    Given el usuario con correo "<correo>" tiene cuenta activa
    When ingresa correo y contraseña correctos
    Then el resultado debe ser "acceso concedido"
    And el sistema le da acceso a la plataforma

    Examples:
      | correo            | contraseña     |
      | activo@mail.com   | SecurePass123! |
      | test@agrotrack.co | StrongKey$     |

  Scenario Outline: Error de inicio
    Given el usuario ingresa datos incorrectos o la cuenta con correo "<correo>" no está activa
    When intenta iniciar sesión con la contraseña "<contraseña>"
    Then el resultado debe ser "inicio rechazado"
    And el sistema rechaza la acción
    And muestra un mensaje con el motivo

    Examples:
      | correo            | contraseña     |
      | inactivo@mail.com | Pass123!       |
      | invalido@mail.com | WrongPass      |