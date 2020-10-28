import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { PetsRoutingModule } from './pets-routing.module';
import { PetsFormComponent } from './pets-form/pets-form.component';
import { PetsListaComponent } from './pets-lista/pets-lista.component';
import { RouterModule } from '@angular/router';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  declarations: [PetsFormComponent, PetsListaComponent],
  imports: [
    CommonModule,
    PetsRoutingModule,
    FormsModule,
    RouterModule,
    Ng2SearchPipeModule,
    TextMaskModule
  ], exports: [
    PetsFormComponent,
    PetsListaComponent
  ]
})
export class PetsModule {

}
