import { Client } from './Client';

export class Comments {
  id!: number;
  comments!: String;
  like!: number;
  date!: Date;
  c!: Client;
  likesPersons!:Array<number>;
}
