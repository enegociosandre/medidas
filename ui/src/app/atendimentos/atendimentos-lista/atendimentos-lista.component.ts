import { Component, OnInit } from '@angular/core';
import { AtendimentosService } from 'src/app/atendimentos.service';
import { Atendimento } from '../atendimento';
import { Router } from '@angular/router'

@Component({
  selector: 'app-atendimentos-lista',
  templateUrl: './atendimentos-lista.component.html',
  styleUrls: ['./atendimentos-lista.component.css']
})
export class AtendimentosListaComponent implements OnInit {

  atendimentos: Atendimento[] = [];
  atendimentoSelecionado: Atendimento;
  mensagemSucesso: string;
  mensagemErro: string;
  filter:string;
  constructor(
    private service: AtendimentosService,
     private router: Router) { }

  ngOnInit(): void {
     this.service
     .getAtendimentos()
     .subscribe( resposta =>
      this.atendimentos = resposta );
  }

  novoCadastro(){
    this.router.navigate(['/atendimentos-form'])
  }

  preparaDelecao(atendimento : Atendimento){
      this.atendimentoSelecionado = atendimento;
  }

  deletarAtendimento(){
    this.service
    .deletar(this.atendimentoSelecionado)
    .subscribe(
      response => {
        this.mensagemSucesso = 'Atendimento deletado com sucesso!'
        this.ngOnInit();
      },
      erro => this.mensagemErro = 'Ocorreu um erro ao deletar o atendimento.'
    )
  }
}
