export interface Usuario {
    id?: number,
    nombre?: string,
    contraseñaHash: string,
    descripcion: string,
    correo: string,
    facebook: string,
    twitter: string,
    foto: string,
    roles: string[]
}
