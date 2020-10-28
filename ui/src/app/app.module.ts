import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { AtendimentosModule } from './atendimentos/atendimentos.module';
import { AtendimentosService } from './atendimentos.service';
import { ClientesModule } from './clientes/clientes.module';
import { ClientesService } from './clientes.service';
import { PetsModule } from './pets/pets.module';
import { PetsService } from './pets.service';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TemplateModule,
    ClientesModule,
    PetsModule,
    AtendimentosModule,
    ClientesModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    TextMaskModule
  ],
  providers: [
    ClientesService,
    PetsService,
    AtendimentosService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
