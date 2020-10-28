import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PetsFormComponent } from './pets-form/pets-form.component'
import { PetsListaComponent } from './pets-lista/pets-lista.component';

const routes: Routes = [
  { path: 'pets-form' , component: PetsFormComponent },
  { path: 'pets-form/:id' , component: PetsFormComponent },
  { path: 'pets-lista', component: PetsListaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PetsRoutingModule { }
