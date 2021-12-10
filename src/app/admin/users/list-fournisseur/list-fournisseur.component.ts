import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/userService/user.service';
import { FournisseurService } from '../service/fournisseur.service';

@Component({
  selector: 'app-list-fournisseur',
  templateUrl: './list-fournisseur.component.html',
  styleUrls: ['./list-fournisseur.component.css']
})
export class ListFournisseurComponent implements OnInit {

  constructor(private fournisseurService: FournisseurService ,private userService:UserService) { }

  fournisseurs: any;
  
  changeStatusButton="";

  ngOnInit(): void {
    

    let resp = this.fournisseurService.geFournisseurs();
    resp.subscribe((data) => this.fournisseurs = data);   

     }


    

     verifUserOrNot(email:any){
      const formData = new FormData();
      formData.append('email',email);
      this.userService.verifUserOrNot(formData).subscribe(data=>{

        let resp = this.fournisseurService.geFournisseurs();
    resp.subscribe((data) => this.fournisseurs = data);          

      })


       
      
     }

     


}
