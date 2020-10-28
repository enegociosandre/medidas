import { Component, OnInit } from '@angular/core';
import { Atendimento } from '../atendimento'
import { AtendimentosService } from '../../atendimentos.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { Cliente } from '../../clientes/cliente';
import { Pet } from '../../pets/pet';
import { ClientesService } from '../../clientes.service';
import { PetsService } from '../../pets.service';


@Component({
  selector: 'app-atendimentos-form',
  styleUrls: ['./atendimentos-form.component.css'],
  templateUrl: './atendimentos-form.component.html'
})
export class AtendimentosFormComponent implements OnInit {
  public myModel = '';
  public forma = '';
  public mask = [ /\d/,',', /\d/, /\d/, /\d/, /\d/];
  atendimento: Atendimento;
  clientes: Cliente[] = [];
  pets: Pet[] = [];
  success: boolean = false;
  errors: String[];
  id: number;
  constructor(
      private service : AtendimentosService,
      private router : Router,
      private clientesService: ClientesService,
      private petsService: PetsService,
      private activatedRoute: ActivatedRoute
      ) {
          this.atendimento = new Atendimento();
        }

  ngOnInit(): void {
    this.clientesService
    .getClientes()
    .subscribe(
      response => this.clientes = response );
      this.petsService
      .getPets()
      .subscribe(
        response => this.pets = response );
    let params : Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id']
      if(this.id){
        this.service
              .getAtendimentoById(this.id)
              .subscribe(
                response => this.atendimento = response ,
                errorResponse => this.atendimento = new Atendimento()
              )
      }
    })
  }

  voltarParaListagem(){
    this.router.navigate(['/atendimentos-lista'])
  }

  irParaImpressao(){
    this.router.navigate(['/atendimentos-print'])
  }

  onSubmit(){

    if( this.id ) {

      this.service
        .atualizar(this.atendimento)
        .subscribe(response => {
          this.success =true;
          this.errors = null;
        }, errorResponse => {
          this.errors = ['erro ao atualizar o atendimento.']
        })

    } else {
      this.service
      .salvar(this.atendimento)
      .subscribe(
        response => {
          this.success = true;
          this.errors = null;
          this.atendimento = response;
        } , errorResponse => {
            this.errors = errorResponse.error.errors;
        }
      )
    }
  }
}
