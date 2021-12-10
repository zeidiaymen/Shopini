import { Client } from '../client/Models/Client';

export class Delivery {
  id!: number;
  address!: String;
  cellPhone!: String;
  estimatedTime!: Date;
  name!: String;
  clientDel!: Client;
  weight!: String;
  status!: String;
  livreur: any;
}
