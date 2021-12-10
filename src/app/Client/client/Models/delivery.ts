import { Client } from './Client';

export class Delivery {
  id!: number;
  address!: String;
  cell_phone!: String;
  estimatedTime!: Date;
  clientName!: String;
  clientDel!: Client;
  weight!: String;
  status!: String;
  livreur: any;
}
