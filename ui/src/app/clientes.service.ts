import { Injectable } from '@angular/core';
import { Cliente } from './clientes/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  baseUrl = environment.baseUrl;

  constructor( private http: HttpClient ) {

  }

  salvar( cliente: Cliente ) : Observable<Cliente>{
    return this.http.post<Cliente>(this.baseUrl + '/clientes', cliente);
  }

  atualizar( cliente: Cliente ) : Observable<any>{
    return this.http.put<Cliente>(this.baseUrl + `/clientes/${cliente.id}`, cliente);
  }

  getClientes() : Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.baseUrl + '/clientes');
  }

  getClienteById(id: number){
    return this.http.get<any>(this.baseUrl + `/clientes/${id}`);
  }

  deletar(cliente: Cliente) : Observable<any> {
    return this.http.delete<any>(this.baseUrl + `/clientes/${cliente.id}`);
  }

}
