# JOBarcelona '22 Hackathon by Nuwe

Backend: el proyecto se basa en un servicio API REST hecho en Java con el ecosistema Spring Boot; haciendo uso de sus modulos de Spring Web, Data, Security y Validation.

Este servicio REST se ha implementado con sistemas de Autenticacion y Autorizacion por medio de tokens JWT.

> El equipo de JOBX necesita implementar un sistema de autenticación (Auth) utilizando Jason Web Token para seguir de forma segura.

#

![](https://img.shields.io/github/last-commit/Bob-Lennon/Nuwe-JOBarcelona22) ![](https://img.shields.io/github/followers/Bob-Lennon?style=social)

## Technology Stack

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)

### Tools

![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![Spotify](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white) ![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)

Tambien se usó la libreria [Lombok](https://projectlombok.org/) para agilizar el proceso de desarrollo.

## Tasks

✅ Task 1 → Que se conecte al puerto: 3030

✅ Task 2 → Las variables de entorno no se deben subir a github, pero tiene que haber un template que permita conocer cuales son las necesarias.

✅ Task 3 → Las rutas de auth tiene que permitir registrar y logear usuarios: Una ruta para el registro /signup, Una ruta para el login /login

✅ Task 4 → Tanto la ruta de login como la de registro deben enviar el token para poder entrar en las demás rutas.

✅ Task 5 → Además, se necesita una ruta que devuela toda la lista de users que haya para que desde el backoffice de JOBX puedan controlar el crecimiento. Esta ruta tiene que estar protegida y solo se dará acceso a un usuario con rol de admin y con los credenciales que se encuentran encriptados en el documento: ruta: /users

✅ Task 6 → Un user tiene como parámetros obligatorios: username (tiene que ser único), email (tiene que seguir el patrón de correo y ser único), password (tiene que tener mínimo de 8 carácteres, incluir una mayúscula y un número).

## Configuration

Para poder lanzar la aplicación primero se deben configurar las siguentes variables de entorno en el fichero [application.properties](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/src/main/resources/application.properties).

    spring.data.mongodb.uri= ${MongoDB_URL}         // mongodb://localhost:27017/bcn22

En el caso de que su base de datos MongoDB requiera de usuario y contraseña debe añadir las siguentes propiedades:

    spring.data.mongodb.username= ${MongoDB_USER}   // admin
    spring.data.mongodb.password= ${MongoDB_PASS}   // 1234

## Documentation

Una vez se inicie la aplicación, la URL con la documentacion de la API en formato Open API sera:

Pagina con formato Open API: http://localhost:3030/swagger-ui/index.html </br> Pagina con formato texto-plano: http://localhost:3030/api-docs

Tambien puedes ver la documentacion en formato YAML en el archivo [BCN22_API](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/shared/bcn22_api.yaml) y visualizarla en la pagina oficial de Swagger: https://editor.swagger.io



#### Resources

PDF encriptado con la definicion de las tareas: [PDF](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/shared/jobx_back.pdf)

JSON con los usuarios de muestra: [JSON](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/shared/jobx-users.json)

YAML con la documentacion de la API: [YAML](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/shared/bcn22_api.yaml)

## License

[MIT](https://github.com/Bob-Lennon/Nuwe-JOBarcelona22/blob/master/LICENSE.md)
