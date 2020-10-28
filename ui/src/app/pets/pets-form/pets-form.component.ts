import { Component, OnInit } from '@angular/core';
import { Pet } from '../pet'
import { PetsService } from '../../pets.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-pets-form',
  templateUrl: './pets-form.component.html',
  styleUrls: ['./pets-form.component.css']
})
export class PetsFormComponent implements OnInit {
  public myModel = '';
  public maskMobile = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public maskPhone = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  pet: Pet;
  success: boolean = false;
  errors: String[];
  id: number;
  constructor(
      private service : PetsService,
      private router : Router,
      private activatedRoute: ActivatedRoute
      ) {
    this.pet = new Pet();
   }

  ngOnInit(): void {
    let params : Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id']
      if(this.id){
        this.service
              .getPetById(this.id)
              .subscribe(
                response => this.pet = response ,
                errorResponse => this.pet = new Pet()
              )
      }
    })
  }

  voltarParaListagem(){
    this.router.navigate(['/pets-lista'])
  }

  onSubmit(){
    if( this.id ) {

      this.service
        .atualizar(this.pet)
        .subscribe(response => {
          this.success =true;
          this.errors = null;
        }, errorResponse => {
          this.errors = ['erro ao atualizar o pet.']
        })

    } else {
      this.service
      .salvar(this.pet)
      .subscribe(
        response => {
          this.success = true;
          this.errors = null;
          this.pet = response;
        } , errorResponse => {
            this.errors = errorResponse.error.errors;
        }
      )
    }
  }



}
