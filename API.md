# DOCUMENTACION API REST

## Peticiones GET

### Códigos de estado presenciados:

+ 200 OK (Si la petición ha ido bien)
+ 404 Not found (Se solicitó un recurso no existente)
+ 401 Unauthorized (Se solicitó un recurso que requiere logIn)
+ 403 Forbidden (Si se accede a un recuro con insuficientes permisos)

### URL´S disponibles:

#### Públicas:
- https://localhost:8443/api/anuncios/ : devuelve todos los recursos de tipo Anuncio disponibles
- https://localhost:8443/api/anuncios/{id} : retorna un objeto JSON con el anuncio solicitado por id
#### Privadas (requiere loguearse en la web):
##### Cabeceras necesarias:
                - Authorization   Basic (String generado con las credenciales de un usuario registrado)
- https://localhost:8443/api/conversaciones/ : devuelve todos los resursos de tipo Conversacion almacenados en la BD
- https://localhost:8443/api/conversaciones/{id} : devuelve un recurso Conversacion dado en la url su identificador
- https://localhost:8443/api/conversaciones/miUsuario : retorna las conversaciones del usuario logueado
- https://localhost:8443/api/conversaciones/usuario/{id} : devuelve un objeto JSON con la información de una conversacion entre el usuario logueado y un usuario dado su id

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
    "comentarios": [(Array con objetos de tipo Mensaje transformados a JSON)]
  }
+ Datos de salida (objeto JSON enviado): {
    "id": (identificador del objeto Conversacion creado),
    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},
    "userSeller": {(objeto de tipo Usuario transformado a JSON)},
    "comentarios": [(Array con objetos de tipo Mensaje transformados a JSON)]
  }

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
    "comentarios": [(Array con objetos de tipo Mensaje transformados a JSON)]
  }
+ Datos de salida (objeto JSON enviado): {
    "id": (identificador del objeto Conversacion),
    "userBuyer": {(objeto de tipo Usuario transformado a JSON)},
    "userSeller": {(objeto de tipo Usuario transformado a JSON)},
    "comentarios": [(Array con objetos de tipo Mensaje transformados a JSON)]
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

