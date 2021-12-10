export class Facture {
  id!: number;
  active!: boolean;
  codeQrAuthentif!: String;
  dateFacture!: Date;
  description!: String;
  shipping!: number;
  subtotal!: number;
  tax!: number;
  total!: number;
}
