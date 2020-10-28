import { Endereco } from '../shared/endereco';
import { Cliente } from '../clientes/cliente';
import { Pet } from '../pets/pet';

export class Atendimento {
  id: number;
  titulo: string;
  descricao: string;
  preco:string;
  comissao:string;
  condominio:string;
  dormitorios:string;
  banheiros:string;
  suites:string;
  quintal:string;
  frente:string;
  tamanho:string;
  extra:string;
  dataCadastro: string;
  endereco = new Endereco();
  cliente = new Cliente();
  pet = new Pet();
}
