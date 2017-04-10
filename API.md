# DOCUMENTACION API REST

## Peticiones GET

### Códigos de estado presenciados:

+ 200 OK (Si la petición ha ido bien)
+ 404 Not found (Se solicitó un recurso no existente)
+ 401 Unauthorized (Se solicitó un recurso que requiere logIn)
+ 403 Forbidden (Si se accede a un recuro con insuficientes permisos)

### URL´S disponibles:

#### Públicas:
- https://localhost:8443/api/logIn : se inicia sesión con las credenciales requeridas y hacer uso de Basic Authorization con dichas credenciales, generando la siguiente cabecera:

                 - Authorization   Basic (String generado con las credenciales de un usuario registrado)
                 
+ Datos de salida (objeto JSON):{

                    "id": (Long),
                    
                    "nombre": "(string)",
                    
                    "contraseñaHash": "(string con contraseña encriptada)",
                    
                    "descripcion": "(string)",
                    
                    "correo": "(string)",
                    
                    "facebook": "(string)",
                    
                    "twitter": "(string)",
                    
                    "foto": "(string)",
                    
                    "roles": [
                      "ROLE_USER",
                      "ROLE_ADMIN"
                    ]
                    
  } 
- https://localhost:8443/api/logOut: cierre de sesión en la aplicación. Devuelve true si se ha cerrado correctamente. Para su correcto uso, se necesita un usuario logueado: 

                 - Authorization   Basic (String generado con las credenciales de un usuario registrado)
                  
+ Datos de salida: (boolean)
- https://localhost:8443/api/anuncios/ : devuelve todos los recursos de tipo Anuncio disponibles
+ Datos de salida (array de objetos JSON):[ {

                    "id": (long),

                    "price": (double),

                    "comment": "(string)",

                    "user": {(objeto de tipo Comic transformado a JSON)},

                    "comic": {(objeto de tipo Comic transformado a JSON)},

                    "tipo": (boolean)
    
  }]
- https://localhost:8443/api/anuncios/{id} : retorna un objeto JSON con el anuncio solicitado por id
+ Datos de salida (objeto JSON): {

                    "id": (long),

                    "price": (double),

                    "comment": "(string)",

                    "user": {(objeto de tipo Comic transformado a JSON)},

                    "comic": {(objeto de tipo Comic transformado a JSON)},

                    "tipo": (boolean)
    
  }
- https://localhost:8443/api/comics/ : retorna un objeto JSON con el listado de comics paginado
+ Datos de entrada: {

                    "page": (string),
    
  }
+ Datos de salida (objeto JSON): {

                    "number": (integer),

                    "numberOfElements": (integer),

                    "totalElements": "(integer)",
                    
                    "totalPages": "(integer)",
                    
                    "size": "(integer)",
                    
                    "content": [

                      "id": "(Long)",

                      "titulo": "(string)",

                      "foto": "(string)"
                    ]
    
  }
- https://localhost:8443/api/comics/{id} : retorna un objeto JSON con el comic solicitado
+ Datos de salida (objeto JSON): {

                    "id": (Long),
                    
                    "titulo": "(string)",
                    
                    "autor": "(string)",
                    
                    "dibujante": "(string)",
                    
                    "argumento": "(string)",
                    
                    "foto": "(string)"
    
  }
- https://localhost:8443/api/valoraciones/ : retorna un objeto JSON con listado de valoraciones
+ Datos de salida (objeto JSON): {

                    "id": (long),

                    "price": (double),

                    "comment": "(string)",

                    "user_give": {(objeto de tipo Usuario transformado a JSON)},

                    "comic": {(objeto de tipo Comic transformado a JSON)},

                    "comentario": "(string)",
                    
                    "numEstrellas": "(integer)",
                    
                    "user_receive": {(objeto de tipo Usuario transformado a JSON)},
    
  }
- https://localhost:8443/api/valoracion/{id} : retorna un objeto JSON con la valoración indicada mediante id
+ Datos de salida (objeto JSON): {

                    "id": (long),

                    "price": (double),

                    "comment": "(string)",

                    "user_give": {(objeto de tipo Usuario transformado a JSON)},

                    "comic": {(objeto de tipo Comic transformado a JSON)},

                    "comentario": "(string)",
                    
                    "numEstrellas": "(integer)",
                    
                    "user_receive": {(objeto de tipo Usuario transformado a JSON)},
    
  }
#### Privadas (requiere loguearse en la web):
##### Cabeceras necesarias:
                - Authorization   Basic (String generado con las credenciales de un usuario registrado)
- https://localhost:8443/api/conversaciones/ : devuelve todos los resursos de tipo Conversacion almacenados en la BD
+ Datos de salida (array de objetos JSON): [{

                    "id": (long),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformado a JSON)}
                    
                    ]

  }]
- https://localhost:8443/api/conversaciones/{id} : devuelve un recurso Conversacion dado en la url su identificador
+ Datos de salida (objeto JSON): {

                    "id": (long),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformado a JSON)}
                    
                    ]

  }
- https://localhost:8443/api/conversaciones/miUsuario : retorna las conversaciones del usuario logueado
+ Datos de salida (array de objetos JSON): [{

                    "id": (long),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformado a JSON)}
                    
                    ]

  }]
- https://localhost:8443/api/conversaciones/usuario/{id} : devuelve un objeto JSON con la información de una conversacion entre el usuario logueado y un usuario dado su id
+ Datos de salida (objeto JSON): {

                    "id": (long),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformado a JSON)}
                    
                    ]

  }

## Peticiones POST

### Códigos de estado presenciados:

+ 201 Created (Si se creó y almacenó el recurso con éxito)
+ 401 Unauthorized (Se intenta crear un recurso que requiere logIn)
+ 403 Forbidden (Permisos insuficientes para crear el recurso)
+ 500 Internal Server Error (Ocurrió un error en el servidor(mal implementado))

### URL´S disponibles:

#### Públicas:

#### Privadas (requiere loguearse en la web):
##### Cabeceras necesarias:
                - Content-Type    application/json
                - Authorization   Basic (String generado con las credenciales de un usuario registrado)
- https://localhost:8443/api/anuncios/ : crea un recurso de tipo Anuncio
+ Datos de entrada: {

                    "type": (boolean),
    
                    "price": (double),
    
                    "comment": "(string)",
    
                    "user": {(objeto de tipo Usuario transformado a JSON)},
    
                    "comic": {(objeto de tipo Usuario transformado a JSON)}
  }
  
+ Datos de salida (objeto JSON enviado): {

                    "id": (long),

                    "type": (boolean),

                    "price": (double),

                    "comment": "(string)",

                    "user": {(objeto de tipo Usuario transformado a JSON)},

                    "comic": {(objeto de tipo Usuario transformado a JSON)}
    
  }
- https://localhost:8443/api/conversaciones/ : crea un recurso de tipo Conversacion
+ Datos de entrada: {

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": []
    
  }
+ Datos de salida (objeto JSON enviado): {

                    "id": (identificador del objeto Conversacion creado),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": []
    
  }
- https://localhost:8443/api/comics/ : crea un recurso de tipo Comic
+ Datos de entrada: {

                    "titulo": "(string)",
                    
                    "autor": "(string)",
                    
                    "dibujante": "(string)",
                    
                    "argumento": "(string)",
                    
                    "foto": "(string)"
    
  }
+ Datos de salida (objeto JSON enviado): {

                    "id": (identificador generado, tipo Long),
                    
                    "titulo": "(string)",
                    
                    "autor": "(string)",
                    
                    "dibujante": "(string)",
                    
                    "argumento": "(string)",
                    
                    "foto": "(string)"
    
  }
- https://localhost:8443/api/guardarValoracion/ : crea un recurso de tipo Valoracion
+ Datos de entrada: {

                    "user_give": {(objeto de tipo Usuario transformado a JSON)},

                    "user_receive": {(objeto de tipo Usuario transformado a JSON)},

                    "comentario": (string),
                    
                    "numEstrellas": (integer),
    
  }
+ Datos de salida (objeto JSON enviado): true

## Peticiones PUT

### Códigos de estado presenciados:

+ 200 OK (Se modificó el recurso con éxito)
+ 401 Unauthorized (Se intenta modificar un recurso que requiere logIn)
+ 403 Forbidden (Permisos insuficientes para modificar el recurso)

#### Públicas:

#### Privadas (requiere loguearse en la web):
##### Cabeceras necesarias:
                - Content-Type    application/json
                - Authorization   Basic (String generado con las credenciales de un usuario registrado)
- https://localhost:8443/api/conversaciones/{id} :
+ Datos de entrada: {

                    "id": (identificador del objeto Conversacion a modificar),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformados a JSON)}
                    
                    ]
    
  }
+ Datos de salida (objeto JSON enviado): {

                    "id": (identificador del objeto Conversacion),

                    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},

                    "userSeller": {(objeto de tipo Usuario transformado a JSON)},

                    "comentarios": [
                    
                          {(objetos de tipo Mensaje transformados a JSON)}
                    
                    ]
    
  }

## Peticiones DELETE

### Códigos de estado presenciados:

+ 200 OK (Si se borró con éxito)
+ 404 Not found (Se intentó borrar un recurso que no existe)

### URL´S disponibles:

#### Públicas:

#### Privadas (requiere loguearse en la web):
##### Cabeceras necesarias:
                - Authorization   Basic (String generado con las credenciales de un usuario registrado)
- https://localhost:8443/api/anuncios/{id} : borra el recurso Anuncio con el id solicitado
+ Datos de salida: null (no existe el objeto por lo que se borró con éxito)
