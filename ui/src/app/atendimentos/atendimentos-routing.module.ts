import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AtendimentosFormComponent } from './atendimentos-form/atendimentos-form.component'
import { AtendimentosListaComponent } from './atendimentos-lista/atendimentos-lista.component';
import { AtendimentosPrintComponent } from './atendimentos-print/atendimentos-print.component';

const routes: Routes = [
  { path: 'atendimentos-form' , component: AtendimentosFormComponent },
  { path: 'atendimentos-form/:id' , component: AtendimentosFormComponent },
  { path: 'atendimentos-lista', component: AtendimentosListaComponent },
  { path: 'atendimentos-print/:id', component: AtendimentosPrintComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtendimentosRoutingModule { }
