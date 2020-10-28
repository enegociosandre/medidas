import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { AtendimentosRoutingModule } from './atendimentos-routing.module';
import { AtendimentosFormComponent } from './atendimentos-form/atendimentos-form.component';
import { AtendimentosListaComponent } from './atendimentos-lista/atendimentos-lista.component';
import { RouterModule } from '@angular/router';
import { TextMaskModule } from 'angular2-text-mask';
import { AtendimentosPrintComponent } from './atendimentos-print/atendimentos-print.component';

@NgModule({
  declarations: [AtendimentosFormComponent, AtendimentosListaComponent, AtendimentosPrintComponent],
  imports: [
    CommonModule,
    AtendimentosRoutingModule,
    FormsModule,
    RouterModule,
    Ng2SearchPipeModule,
    TextMaskModule
  ], exports: [
    AtendimentosPrintComponent,
    AtendimentosFormComponent,
    AtendimentosListaComponent
  ]
})
export class AtendimentosModule {

}
