import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesFormComponent } from './clientes-form/clientes-form.component';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';
import { RouterModule } from '@angular/router';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  declarations: [ClientesFormComponent, ClientesListaComponent],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    FormsModule,
    RouterModule,
    Ng2SearchPipeModule,
    TextMaskModule
  ], exports: [
    ClientesFormComponent,
    ClientesListaComponent
  ]
})
export class ClientesModule {

}
