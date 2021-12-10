import {DetailProduit} from "./detailProduit";

export class Product {
  idProduit !: number;
  code !:string;
  libelle !:string;
  picture !:string;
  prixUnitaire !:number;
  detailProduit !:DetailProduit ;
  fournisseur !:any;
  //prod!:any;
}
