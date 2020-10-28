import { Component, OnInit } from '@angular/core';
import { PetsService } from 'src/app/pets.service';
import { Pet } from '../pet';
import { Router } from '@angular/router'

@Component({
  selector: 'app-pets-lista',
  templateUrl: './pets-lista.component.html',
  styleUrls: ['./pets-lista.component.css']
})
export class PetsListaComponent implements OnInit {

  pets: Pet[] = [];
  petSelecionado: Pet;
  mensagemSucesso: string;
  mensagemErro: string;
  filter:string;
  constructor(
    private service: PetsService,
     private router: Router) { }

  ngOnInit(): void {
     this.service
     .getPets()
     .subscribe( resposta =>
      this.pets = resposta );
  }

  novoCadastro(){
    this.router.navigate(['/pets-form'])
  }

  preparaDelecao(pet : Pet){
      this.petSelecionado = pet;
  }

  deletarPet(){
    this.service
    .deletar(this.petSelecionado)
    .subscribe(
      response => {
        this.mensagemSucesso = 'Pet deletado com sucesso!'
        this.ngOnInit();
      },
      erro => this.mensagemErro = 'Ocorreu um erro ao deletar o pet.'
    )
  }
}
