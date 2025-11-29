Feature: Inicio de sesión mediante la API
  Para permitir que los usuarios puedan autenticarse
  Como developer
  Quiero implementar el endpoint POST "/api/auth/login"

  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    Given usuario existente con email "<email>" y password "<password>"
    When envío email "<email>" y password "<password>"
    Then se responde con estado 200 OK
    And se retorna un token JWT

    Examples:
      | email              | password      |
      | juan@example.com   | Password123!  |
      | ana@mailtest.com   | StrongPass#45 |

  Scenario Outline: Error al iniciar sesión con credenciales inválidas
    Given usuario existente con email "<email>"
    When envío el password incorrecto "<password>"
    Then se responde con estado 401 Unauthorized
    And el mensaje es "error de autenticación"

    Examples:
      | email              | password    |
      | juan@example.com   | wrong123    |
      | ana@mailtest.com   | claveMala   |
