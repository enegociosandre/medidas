import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Cliente } from '../../clientes/cliente';
import { Pet } from '../../pets/pet';
import { Atendimento } from '../atendimento';
import { AtendimentosService } from '../../atendimentos.service';
import { ClientesService } from '../../clientes.service';
import { PetsService } from '../../pets.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-atendimentos-print',
  templateUrl: './atendimentos-print.component.html',
  styleUrls: ['./atendimentos-print.component.css']
})
export class AtendimentosPrintComponent implements OnInit {
  atendimento: Atendimento;
  clientes: Cliente[] = [];
  pets: Pet[] = [];
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
}
