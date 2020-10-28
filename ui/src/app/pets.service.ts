import { Injectable } from '@angular/core';
import { Pet } from './pets/pet';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PetsService {
  baseUrl = environment.baseUrl;

  constructor( private http: HttpClient ) {

  }

  salvar( pet: Pet ) : Observable<Pet>{
    return this.http.post<Pet>(this.baseUrl + '/pets', pet);
  }

  atualizar( pet: Pet ) : Observable<any>{
    return this.http.put<Pet>(this.baseUrl + `/pets/${pet.id}`, pet);
  }

  getPets() : Observable<Pet[]> {
    return this.http.get<Pet[]>(this.baseUrl + '/pets');
  }

  getPetById(id: number){
    return this.http.get<any>(this.baseUrl + `/pets/${id}`);
  }

  deletar(pet: Pet) : Observable<any> {
    return this.http.delete<any>(this.baseUrl + `/pets/${pet.id}`);
  }

}
