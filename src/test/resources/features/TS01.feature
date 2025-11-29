Feature: Registro de usuarios mediante la API
  Para permitir que nuevos usuarios creen una cuenta
  Como developer
  Quiero implementar el endpoint POST "/api/auth/register"

  Scenario Outline: Registro exitoso de usuario
    Given que el email "<email>" no está registrado
    And el cuerpo contiene nombre "<nombre>", email "<email>" y password "<password>" válidos
    When se envía una solicitud POST a "/api/auth/register"
    Then se responde con estado 201 Created
    And se retorna el userId del nuevo usuario

    Examples:
      | nombre     | email               | password      |
      | Juan Perez | juan@example.com    | Password123!  |
      | Ana Torres | ana@mailtest.com    | StrongPass#45 |

  Scenario Outline: Error por email duplicado
    Given que ya existe un usuario registrado con el email "<email>"
    When se intenta registrar un usuario con el mismo email
    Then se responde con estado 409 Conflict
    And el mensaje es "correo ya registrado"

    Examples:
      | email             |
      | juan@example.com  |
      | repetido@mail.com |

  Scenario Outline: Error por password débil
    Given que el password "<password>" no cumple la política de seguridad
    When se intenta registrar un usuario con ese password
    Then se responde con estado 400 Bad Request
    And el mensaje es "contraseña no cumple política"

    Examples:
      | password   |
      | 12345      |
      | abcdefg    |
      | pass       |

  Scenario Outline: Error por formato de email inválido
    Given que se ingresa un email inválido "<email>"
    When se envía la solicitud POST a "/api/auth/register"
    Then se responde con estado 400 Bad Request
    And el mensaje es "email inválido"

    Examples:
      | email          |
      | juan@correo    |
      | test@          |
      | correo@mal@com |
