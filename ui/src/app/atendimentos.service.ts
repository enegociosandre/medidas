import { Injectable } from '@angular/core';
import { Atendimento } from './atendimentos/atendimento';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtendimentosService {
  baseUrl = environment.baseUrl;

  constructor( private http: HttpClient ) {

  }

  salvar( atendimento: Atendimento ) : Observable<Atendimento>{
    return this.http.post<Atendimento>(this.baseUrl + '/atendimentos', atendimento);
  }

  atualizar( atendimento: Atendimento ) : Observable<any>{
    return this.http.put<Atendimento>(this.baseUrl + `/atendimentos/${atendimento.id}`, atendimento);
  }

  getAtendimentos() : Observable<Atendimento[]> {
    return this.http.get<Atendimento[]>(this.baseUrl + '/atendimentos');
  }

  getAtendimentoById(id: number){
    return this.http.get<any>(this.baseUrl + `/atendimentos/${id}`);
  }

  deletar(atendimento: Atendimento) : Observable<any> {
    return this.http.delete<any>(this.baseUrl + `/atendimentos/${atendimento.id}`);
  }

}
